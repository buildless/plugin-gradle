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

@file:Suppress("DataClassPrivateConstructor")

package build.less.plugin.gradle

import build.less.plugin.gradle.service.BuildlessCache
import org.gradle.api.initialization.Settings
import org.gradle.api.provider.Property
import org.gradle.caching.configuration.BuildCacheConfiguration
import org.gradle.caching.http.HttpBuildCache
import org.gradle.caching.local.DirectoryBuildCache
import java.net.URI
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

/**
 * # Buildless for Gradle: Plugin Configuration
 *
 * @param apiKey API key to use for interactions with the remote build cache service.
 * @param enableAgent Whether to enable local agent functionality.
 * @param agentConfig Active Agent configuration, if any, or `null`; always `null` when [enableAgent] is false.
 * @param transport Transport mechanism to use when interacting with the build cache.
 * @param debug Whether to enable debug logging.
 * @param telemetry Whether to enable build telemetry integration.
 * @param reportErrors Whether to allow error reporting back to Buildless.
 * @param localCache Local build cache settings to apply.
 * @param remoteCache Remote build cache settings to apply.
 */
@Serializable internal data class BuildlessPluginConfig private constructor (
  val apiKey: ApiKey?,
  val enableAgent: Boolean,
  val agentConfig: AgentConfig?,
  val transport: CacheTransport,
  val debug: Boolean,
  val telemetry: Boolean,
  val reportErrors: Boolean,
  val localCache: ImmutableLocalCacheSettings,
  val remoteCache: ImmutableRemoteCacheSettings,
) : java.io.Serializable {
  companion object {
    /**
     * Internal factory method for creating a sealed plugin configuration.
     *
     * @param apikey API key to use for interactions with the remote build cache service.
     * @param agentConfig Active Agent configuration, if any, or `null`; always `null` when [enableAgent] is false.
     * @param transport Transport mechanism to use when interacting with the build cache.
     * @param debug Whether to enable debug logging.
     * @param telemetry Whether to enable build telemetry integration.
     * @param reportErrors Whether to allow error reporting back to Buildless.
     * @param agentCache Agent cache settings to apply.
     * @param localCache Local build cache settings to apply.
     * @param remoteCache Remote build cache settings to apply.
     * @return Sealed plugin configuration.
     */
    @JvmStatic fun create(
      apikey: ApiKey?,
      agentConfig: AgentConfig?,
      transport: Property<CacheTransport>,
      debug: Property<Boolean>,
      telemetry: Property<Boolean>,
      reportErrors: Property<Boolean>,
      agentCache: AgentCacheSettings,
      localCache: LocalCacheSettings,
      remoteCache: RemoteCacheSettings,
    ): BuildlessPluginConfig = BuildlessPluginConfig(
      transport = transport.get(),
      enableAgent = agentCache.enabled.get(),
      agentConfig = agentConfig,
      debug = debug.get(),
      telemetry = telemetry.get(),
      reportErrors = reportErrors.get(),
      localCache = ImmutableLocalCacheSettings.from(localCache),
      remoteCache = ImmutableRemoteCacheSettings.from(remoteCache) { apikey },
      apiKey = apikey,
    )
  }

  /** Immutable settings for local build caching. */
  @Serializable internal class ImmutableLocalCacheSettings(
    val enabled: Boolean,
    val directory: String?,
  ) {
    // Apply these settings to the local Gradle build cache settings.
    internal fun applyTo(target: DirectoryBuildCache, agent: Boolean = false) {
      // enable local cache if instructed, and if the agent is not present
      target.isEnabled = enabled && !agent

      if (!directory.isNullOrBlank()) {
        target.directory = directory
      }
    }

    internal companion object {
      /** @return Built immutable local cache settings copied from the [other] settings. */
      @JvmStatic internal fun from(other: LocalCacheSettings): ImmutableLocalCacheSettings =
        ImmutableLocalCacheSettings(
          enabled = other.enabled.getOrElse(false),
          directory = other.directory.orNull?.asFile?.absolutePath,
        )
    }
  }

  /** Immutable settings for remote build caching. */
  @Serializable internal data class ImmutableRemoteCacheSettings(
    val enabled: Boolean,
    val push: Boolean,
    @Transient private val apiKeyProvider: () -> ApiKey? = { null },
  ) {
    // Resolve the endpoint to use for remote caching.
    private fun resolveRemoteEndpoint(): URI = when (val override = (
      System.getenv(Constants.ENDPOINT_OVERRIDE_VAR) ?:
      System.getProperty(Constants.ENDPOINT_OVERRIDE_PROPERTY)
    )) {
      // with no override present, use the default cloud cache endpoint
      null -> URI.create(Constants.DEFAULT_CACHE_ENDPOINT)
      else -> try {
        URI.create(override)
      } catch (e: Exception) {
        throw IllegalArgumentException("Invalid remote cache endpoint (not a URI): $override", e)
      }
    }

    // Apply these settings to the local Gradle build cache settings.
    internal fun applyTo(target: HttpBuildCache, agent: Boolean = false) = when (val apiKey = apiKeyProvider.invoke()) {
      null -> {
        // nothing to apply; no credentials available
      }
      else -> target.credentials {
        it.username = "apikey"
        it.password = apiKey.key
      }
    }.also {
      target.isPush = push
      if (!agent) {
        target.isAllowInsecureProtocol = false
        target.isAllowUntrustedServer = false
        target.isUseExpectContinue = true
      }

      // don't override endpoint if already set
      if (target.url == null) {
        target.url = resolveRemoteEndpoint()
      }
    }

    internal companion object {
      /** @return Built immutable local cache settings copied from the [other] settings. */
      @JvmStatic internal fun from(
        other: RemoteCacheSettings,
        keyProvider: () -> ApiKey?,
      ): ImmutableRemoteCacheSettings = ImmutableRemoteCacheSettings(
        apiKeyProvider = keyProvider,
        enabled = other.enabled.getOrElse(true),
        push = other.push.getOrElse(true),
      )
    }
  }

  // Apply and activate the Buildless Agent.
  private fun applyAgent(context: Settings, config: BuildlessPluginConfig, target: URI) {
    // called when the agent is active and enabled, and should replace current configuration. when a remote config is
    // already active, the cloud endpoint setup is skipped.
    context.buildCache.apply {
      remote(HttpBuildCache::class.java) {
        it.isEnabled = true
        it.isPush = true
        it.url = target

        it.isAllowInsecureProtocol = target.scheme == "http"
        it.isAllowUntrustedServer = false
        it.isUseExpectContinue = true

        // enclose api key if we have it
        when (val apiKey = config.apiKey) {
          null -> {}
          else -> it.credentials { httpBasic ->
            httpBasic.username = Constants.APIKEY_USERNAME
            httpBasic.password = apiKey.key
          }
        }
      }
    }
  }

  // Determine the server target to use for the local agent.
  private fun agentTarget(config: AgentConfig): URI {
    val port = if (config.port in 1..65533 && config.port != Constants.LOCAL_AGENT_CONTROL_DEFAULT) {
      config.port
    } else Constants.LOCAL_AGENT_SERVICE_DEFAULT

    return URI.create("http://${Constants.LOCAL_AGENT_DOMAIN}:$port/cache/generic")
  }

  // Actually apply configuration to the Gradle settings (potentially after waiting).
  private fun applyToSettings(
    context: Settings,
    config: BuildlessPluginConfig,
    settings: BuildCacheConfiguration,
  ) {
    context.buildCache.apply {
      // apply local caching configuration
      when {
        config.localCache.enabled -> local {
          it.isEnabled = true
          localCache.applyTo(it)
        }

        else -> local {
          it.isEnabled = false
        }
      }

      // apply remote caching configuration
      when (settings.remote) {
        // if we don't have any settings, apply our own remote settings
        null -> remote(when (transport) {
          // if we are told to use "standard" protocols, configure an HTTP cache endpoint, as would normally be done
          // using gradle's regular configuration.
          CacheTransport.BUILTIN -> HttpBuildCache::class.java

          // otherwise, if we are able to use optimized/custom transports, initialize a build cache using our custom
          // implementation, which implements its own transport facilities.
          CacheTransport.NEXTGEN -> BuildlessCache::class.java
        }) {
          // apply settings to remote caching implementation
          it.isEnabled = config.remoteCache.enabled
          remoteCache.applyTo(it)

          // if the agent is active (and enabled), we should use that instead of the remote cache.
          if (enableAgent) config.agentConfig?.let { agent ->
            applyAgent(context, config, agentTarget(agent))
          }
        }

        // otherwise, let the user's settings prevail
        else -> {}
      }
    }
  }

  // Internal function to apply current settings to the target Gradle build cache configuration.
  internal fun applyTo(
    context: Settings,
    config: BuildlessPluginConfig,
    settings: BuildCacheConfiguration,
    defer: Boolean,
  ) {
    if (defer) context.gradle.settingsEvaluated {
      applyToSettings(context, config, settings)
    } else applyToSettings(context, config, settings)
  }
}
