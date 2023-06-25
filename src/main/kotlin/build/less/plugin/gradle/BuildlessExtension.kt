/* Copyright (c) 2023 Elide Ventures LLC
 *
 * This is private computer source code. This code is part of an application which is licensed privately, as part of
 * intellectual property owned by Elide Ventures, LLC. All rights are reserved. Viewing and editing this code implies
 * agreement with the Elide Non-Disclosure Agreement and Elide Inventions Assignment Agreement.
 *
 * Code bearing this header may not be shared outside of authorized circumstances without prior written consent from
 * authorized corporate officers of Elide Ventures, LLC.
 */

@file:Suppress("UnstableApiUsage")

package build.less.plugin.gradle

import build.less.plugin.gradle.BuildlessExtensionAPI.BuildlessSettings
import build.less.plugin.gradle.BuildlessExtensionAPI.MutableLocalCacheSettings
import build.less.plugin.gradle.BuildlessExtensionAPI.MutableRemoteCacheSettings
import build.less.plugin.gradle.core.API
import build.less.plugin.gradle.project.BuildlessProjectPlugin
import org.gradle.api.initialization.Settings
import org.gradle.api.provider.Property
import org.gradle.api.services.ServiceReference
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicReference

/**
 * # Buildless for Gradle: Extension
 *
 * The extension for the Buildless plugin is used from the `settings.gradle.kts` script to configure the plugin's
 * behavior and features.
 *
 * @see BuildlessExtensionAPI For the public API suite supported by this plugin for configuration of build caching.
 * @see BuildlessService For a service which may be consumed from other plugins to interact with Buildless.
 */
@API internal abstract class BuildlessExtension : BuildlessExtensionAPI<Settings, BuildlessSettings> {
  /** API key to install. */
  internal val apiKey: AtomicReference<ApiKey?> = AtomicReference(null)

  /** Cache transport to use. */
  internal val transport: AtomicReference<CacheTransport> = AtomicReference(CacheTransport.STANDARD)

  /** Whether to enable telemetry integration. */
  internal val telemetry: AtomicBoolean = AtomicBoolean(true)

  /** Whether to enable debug mode. */
  internal val debug: AtomicBoolean = AtomicBoolean(false)

  /** Whether to enable error reporting. */
  internal val reportErrors: AtomicBoolean = AtomicBoolean(true)

  /** Main Buildless service. */
  @get:ServiceReference("buildless")
  internal abstract val service: Property<BuildlessService>

  // Settings for local build caching.
  private val localCacheSettings: LocalCacheSetup = LocalCacheSetup()

  // Settings for remote build caching.
  private val remoteCacheSettings: RemoteCacheSetup = RemoteCacheSetup()

  /** Mutable settings class for local build cache settings. */
  class LocalCacheSetup : MutableLocalCacheSettings {
    override var enabled: Boolean = true
    override var directory: String? = null
  }

  /** Mutable settings class for remote build cache settings. */
  class RemoteCacheSetup : MutableRemoteCacheSettings {
    override var enabled: Boolean = true
    override var push: Boolean = true
  }

  /** Default configuration builder for the Buildless plugin. */
  internal inner class DefaultConfigurator : BuildlessSettings {
    override var apiKey: ApiKey?
      get() = this@BuildlessExtension.apiKey.get()

      set(value) {
        this@BuildlessExtension.apiKey.set(requireNotNull(value) {
          "Cannot set Buildless API key to `null` value"
        })
      }

    override var telemetry: Boolean
      get() = this@BuildlessExtension.telemetry.get()
      set(value) {
        this@BuildlessExtension.telemetry.set(value)
      }

    override var debug: Boolean
      get() = this@BuildlessExtension.debug.get()
      set(value) {
        this@BuildlessExtension.debug.set(value)
      }

    override var transport: CacheTransport
      get() = this@BuildlessExtension.transport.get()
      set(value) {
        this@BuildlessExtension.transport.set(value)
      }

    override var reportErrors: Boolean
      get() = this@BuildlessExtension.reportErrors.get()
      set(value) {
        this@BuildlessExtension.reportErrors.set(value)
      }

    override val localCache: MutableLocalCacheSettings
      get() = this@BuildlessExtension.localCacheSettings

    override val remoteCache: MutableRemoteCacheSettings
      get() = this@BuildlessExtension.remoteCacheSettings

    override fun localCache(config: MutableLocalCacheSettings.() -> Unit) {
      config.invoke(localCache)
    }

    override fun remoteCache(config: MutableRemoteCacheSettings.() -> Unit) {
      config.invoke(remoteCache)
    }

    // Detect a Buildless API key from the local environment.
    private fun detectBuildlessKey(): ApiKey? = (
      System.getenv("BUILDLESS_APIKEY")
        ?: System.getenv("BUILDLESS_API_KEY")
          ?: System.getenv("GRADLE_CACHE_PASSWORD")
    )?.let {
      ApiKey.of(it)
    }

    /** @return Built configuration from this builder. */
    internal fun build(): BuildlessPluginConfig? = when (val apiKey = this.apiKey ?: detectBuildlessKey()) {
      null -> null
      else -> BuildlessPluginConfig(
        transport = this.transport,
        debug = this.debug,
        telemetry = this.telemetry,
        reportErrors = this.reportErrors,
        localCache = BuildlessPluginConfig.ImmutableLocalCacheSettings.from(localCache),
        remoteCache = BuildlessPluginConfig.ImmutableRemoteCacheSettings.from(remoteCache) { apiKey },
        apiKey = apiKey,
      )
    }
  }

  // Initialize Gradle's build cache settings from Buildless.
  private fun Settings.initializeBuildCaching(config: BuildlessPluginConfig) {
    config.applyTo(this, buildCache)
  }

  override fun config(context: Settings, configure: BuildlessSettings.() -> Unit): Unit = DefaultConfigurator().apply {
    configure.invoke(this)
  }.build()?.let { config ->
    // notify buildless service of configuration
    service.get().notifySettings(config)

    // initialize build cache settings
    context.initializeBuildCaching(config)

    // install buildless plugin on all projects
    context.gradle.allprojects {
      it.pluginManager.apply(BuildlessProjectPlugin::class.java)
    }
  } ?: Unit
}
