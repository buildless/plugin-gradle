/*
 * Copyright (c) 2023 Elide Ventures, LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under the License.
 */

@file:Suppress("UnstableApiUsage")

package build.less.plugin.gradle

import build.less.plugin.gradle.CacheTransport.BUILTIN
import build.less.plugin.gradle.core.API
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.initialization.Settings
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import org.gradle.api.services.ServiceReference
import java.util.concurrent.atomic.AtomicReference
import javax.inject.Inject

/**
 * # Buildless for Gradle: Extension
 *
 * The extension for the Buildless plugin is used from the `settings.gradle.kts` script to configure the plugin's
 * behavior and features.
 *
 * @param factory Object factory for Gradle-provided injection.
 * @see BuildlessExtensionAPI For the public API suite supported by this plugin for configuration of build caching.
 * @see BuildlessService For a service which may be consumed from other plugins to interact with Buildless.
 */
@API public abstract class BuildlessExtension @Inject constructor(private val factory: ObjectFactory) :
  BuildlessExtensionAPI<Settings, BuildlessSettings> {
  /** Constants associated with the Buildless extension. */
  public companion object {
    /** Name of the Buildless extension (`buildless`). */
    public const val NAME: String = "buildless"
  }

  // Native OS tools.
  private val nativeTools: BuildlessNativeTools by lazy {
    BuildlessNativeToolsFactory.obtain()
  }

  // Active agent configuration, if any.
  internal val activeAgent: AgentConfig? by lazy {
    nativeTools.loadAgentConfig()
  }

  // Effective built settings.
  private val effectiveSettings: AtomicReference<BuildlessPluginConfig> = AtomicReference(null)

  /** API key to install. */
  override val apiKey: Property<ApiKey?> = factory.property(ApiKey::class.java)

  /** Cache transport to use. */
  override val transport: Property<CacheTransport> = factory.property(CacheTransport::class.java).value(BUILTIN)

  /** Whether to enable telemetry integration. */
  override val telemetry: Property<Boolean> = factory.property(Boolean::class.java).value(true)

  /** Whether to enable debug mode. */
  override val debug: Property<Boolean> = factory.property(Boolean::class.java).value(false)

  /** Whether to enable error reporting. */
  override val reportErrors: Property<Boolean> = factory.property(Boolean::class.java).value(true)

  /** Main Buildless service. */
  @get:ServiceReference("buildless")
  internal abstract val service: Property<BuildlessService>

  // Settings for local build caching.
  private val localCacheSettings: LocalCacheSetup = LocalCacheSetup()

  // Settings for build cache agent.
  private val agentCacheSettings: LocalAgentSetup = LocalAgentSetup()

  // Settings for remote build caching.
  private val remoteCacheSettings: RemoteCacheSetup = RemoteCacheSetup()

  override val local: MutableLocalCacheSettings
    get() = this@BuildlessExtension.localCacheSettings

  override val agent: MutableAgentCacheSettings
    get() = this@BuildlessExtension.agentCacheSettings

  override val cloud: MutableRemoteCacheSettings
    get() = this@BuildlessExtension.remoteCacheSettings

  override fun localCache(config: MutableLocalCacheSettings.() -> Unit) {
    config.invoke(local)
  }

  override fun agent(config: MutableAgentCacheSettings.() -> Unit) {
    config.invoke(agent)
  }

  override fun remoteCache(config: MutableRemoteCacheSettings.() -> Unit) {
    config.invoke(cloud)
  }

  /** Mutable settings class for local build cache settings. */
  public inner class LocalCacheSetup : MutableLocalCacheSettings {
    override var enabled: Property<Boolean> = factory.property(Boolean::class.java)
      .value(false)  // local cache is always opt-in with buildless

    override var directory: DirectoryProperty = factory.directoryProperty()
  }

  /** Mutable settings class for local agent cache settings. */
  public inner class LocalAgentSetup : MutableAgentCacheSettings {
    override var enabled: Property<Boolean> = factory.property(Boolean::class.java)
      .value(true)  // local cache is always opt-out with buildless (to facilitate automatic activation)
  }

  /** Mutable settings class for remote build cache settings. */
  public inner class RemoteCacheSetup : MutableRemoteCacheSettings {
    override var enabled: Property<Boolean> = factory.property(Boolean::class.java)
      .value(true)  // always enabled by default with the plugin

    override var push: Property<Boolean> = factory.property(Boolean::class.java)
      .value(true)  // push is always enabled by default with buildless (opt-out)
  }

  // Cached detected API key from environment.
  private val detectedApiKey: ApiKey? by lazy {
    detectBuildlessKey()
  }

  // Detect a Buildless API key from the local environment.
  private fun detectBuildlessKey(): ApiKey? = (
    System.getenv(Constants.APIKEY_ENV_VAR)
      ?: System.getenv(Constants.APIKEY_ENV_VAR_ALT)
      ?: System.getProperty(Constants.APIKEY_PROPERTY)
      ?: System.getenv("GRADLE_CACHE_PASSWORD")
  )?.ifEmpty { null }?.ifBlank { null }?.let {
    ApiKey.of(it)
  }

  /** @return Built configuration from this builder. */
  internal fun build(): BuildlessPluginConfig = (apiKey.orNull ?: detectedApiKey).let { effectiveKey ->
    BuildlessPluginConfig.create(
      effectiveKey,
      if (this.agent.enabled.get()) activeAgent else null,
      transport,
      debug,
      telemetry,
      reportErrors,
      agent,
      local,
      cloud,
    )
  }

  // Return current configuration settings, or build defaults.
  internal fun pluginSettings(): BuildlessPluginConfig {
    return effectiveSettings.get() ?: build().also { config ->
      effectiveSettings.set(config)
    }
  }

  // Indicate whether the plugin has been configured.
  internal fun isConfigured(): Boolean = effectiveSettings.get() != null

  // Activate plugin settings.
  private fun activateSettings(config: BuildlessPluginConfig): BuildlessPluginConfig = synchronized(this) {
    require(!isConfigured()) {
      "Buildless plugin has already been configured."
    }
    effectiveSettings.compareAndSet(null, config)

    // notify buildless service of effective configuration
    service.get().notifySettings(config)

    config
  }

  // Whether the plugin should be enabled by default (blind to agent status).
  internal fun shouldEnableByDefault(settings: Settings): Boolean {
    return (
      (apiKey.orNull ?: detectedApiKey) != null
    )
  }

  // Activate via the Groovy DSL when eligible.
  internal fun activateByDefaults(context: Settings) = build().let { config ->
    activateSettings(config)

    if (shouldEnableByDefault(context)) {
      // we do not have to defer activation from the groovy DSL, because this is _only_ ever called after settings have
      // already finished evaluating. by this stage, we need to configure the build cache immediately.
      config.applyTo(context, config, context.buildCache, defer = false)
    }
  }

  // Activate via the Groovy DSL when eligible.
  internal fun maybeActivateWithAgent(context: Settings, config: AgentConfig) {
    // if it's not configured by now, and we have an agent, generate default configuration
    build().copy(agentConfig = config).let { pluginConfig ->
      if (pluginConfig.enableAgent) {
        activateSettings(pluginConfig)
        pluginConfig.applyTo(
          context,
          pluginConfig,
          context.buildCache,
          defer = false,
        )
      }
    }
  }

  // Called explicitly by the Kotlin DSL entrypoint to configure Buildless.
  override fun config(context: Settings, configure: BuildlessSettings.() -> Unit): Unit = this.apply {
    configure.invoke(this)
  }.build().let { config ->
    // activate these settings
    activateSettings(config)

    // initialize build cache settings. this must be deferred because we may not have fully evaluated the project
    // settings yet by this stage (this entrypoint is provided to the Kotlin DSL, so it is called explicitly, sometimes
    // before evaluation has completed).
    config.applyTo(context, config, context.buildCache, defer = true)
  }
}
