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

package build.less.plugin.gradle

import build.less.plugin.gradle.BuildlessExtensionAPI.CacheSettings
import org.gradle.api.Project
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.initialization.Settings
import org.gradle.api.provider.Property


/**
 * ### Local Cache Settings
 *
 * Specifies settings which configure Gradle's local build cache; these may be provided in lieu of settings specified
 * directly with Gradle. Local build cache settings specified directly to Gradle take precedence over these. By
 * default, the local build cache is enabled.
 */
public interface LocalCacheSettings : CacheSettings {
  /**
   * Directory where local cached objects are stored; if unset, Buildless sets a sensible default. If this value is
   * set before the Buildless plugin is invoked, the user's value takes precedence and this value is rendered inert.
   */
  public val directory: DirectoryProperty
}

/**
 * ### Agent Cache Settings
 *
 * Specifies settings which relate specifically to the Buildless Agent, and are used to configure the agent's cache
 * services with Gradle.
 */
public interface AgentCacheSettings : CacheSettings {
  // Nothing at this time.
}

/**
 * ### Remote Cache Settings
 *
 * Specifies settings which configure Gradle's remote build cache; these may be provided in lieu of settings specified
 * directly with Gradle, and are preferred over directly specifying settings. By default, the remote build cache is
 * enabled in all circumstances.
 *
 * Push of objects is enabled by default. Object push (uploading to the cache) can be disabled by setting [push] to
 * `false`, or by using any of the condition builders specified by the [MutableCacheSettings] or
 * [MutableRemoteCacheSettings] types.
 */
public interface RemoteCacheSettings : CacheSettings {
  /**
   * Whether build outputs will be uploaded to the cache as needed; enabled by default.
   *
   * It is recommended to keep cache push enabled, as the Buildless plugin will intelligently disable cache push in
   * circumstances where upload speed is suboptimal.
   */
  public val push: Property<Boolean>
}

/**
 * ### Mutable Settings: Top-level
 *
 * Top-level abstract cache settings for the Buildless Gradle plugin, in mutable form for use in Gradle's
 * `settings.gradle.kts` file. This is the base type for [MutableLocalCacheSettings] and [MutableRemoteCacheSettings].
 */
public sealed interface MutableCacheSettings : CacheSettings {
  public override val enabled: Property<Boolean>
}

/**
 * ### Mutable Settings: Local Cache
 *
 * Local cache settings for the Buildless Gradle plugin, in mutable form for use in Gradle's `settings.gradle.kts`
 * file. The plugin configures sensible defaults if not otherwise specified.
 */
public interface MutableLocalCacheSettings : MutableCacheSettings, LocalCacheSettings {
  public override var directory: DirectoryProperty
}

/**
 * ### Mutable Settings: Agent Cache
 *
 * Settings which relate to the Buildless Agent, in mutable form for use in Gradle's `settings.gradle.kts` file. The
 * plugin configures sensible defaults if not otherwise specified.
 */
public interface MutableAgentCacheSettings : MutableCacheSettings, AgentCacheSettings {
  // Nothing at this time.
}

/**
 * ### Mutable Settings: Remote Cache
 *
 * Remote cache settings for the Buildless Gradle plugin, in mutable form for use in Gradle's `settings.gradle.kts`
 * file. The plugin configures sensible defaults if not otherwise specified. Remote caching is typically not possible
 * unless an API key is resolvable.
 */
public interface MutableRemoteCacheSettings : MutableCacheSettings, RemoteCacheSettings {
  public override var push: Property<Boolean>
}

/**
 * ## Buildless Extension API: Configuration
 *
 * Describes the base interface from which all plugin settings interfaces inherit. Since the Buildless plugin can be
 * configured in both the context of a Gradle [Settings] (initialization) file, and also a [Project], the API needs to
 * be extended into both circumstances; this interface bears the common fields between both.
 *
 * @see BuildlessSettings for settings-time configuration of the plugin, which is shared across all projects.
 * @see BuildlessProjectSettings for settings which apply at the level of a single Gradle project.
 */
public sealed interface ConfigAPI<Target> {
  /**
   * Whether the Buildless plugin is operating in debug mode; debug mode enables verbose logging, and applies trace
   * values to API calls, so that they may be diagnosed from the dashboard.
   */
  public val debug: Property<Boolean>
}

/**
 * ## Buildless Extension API: Settings
 *
 * Describes top-level settings which are available within `settings.gradle.kts` for a Buildless-enabled Gradle
 * project. These settings apply within the scope of file in question (usually, all projects within a multi-project
 * Gradle build, or a single Gradle project).
 *
 * These settings implement the [ConfigAPI] interface for a target of [Settings], but with mutable fields so that they
 * may be set from a builder context.
 *
 * @see ConfigAPI for read-only fields shared by all settings payloads provided by the Buildless plugin for Gradle.
 */
public interface BuildlessSettings : ConfigAPI<Settings> {
  /**
   * ### API Key
   *
   * API key that should be used to identify this project to the Buildless cache service.
   *
   * API keys can be obtained from the Buildless Dashboard (see [Buildless](https://less.build) for more information).
   * Every tenant and user is issued two API keys: their "events" key (also referred to as the `PUBLISHABLE` key), and
   * their own regular user access key.
   *
   * The access key is the value which should be specified in this spot. Additionally, the key may be provided via any
   * of the following mechanisms:
   *
   * - Environment variables (`BUILDLESS_API_KEY`, `GRADLE_CACHE_PASSWORD`)
   * - System properties (`buildless.apiKey`, `cachePassword`)
   * - Local file (`.buildless.toml`, `.github/buildless.toml`, `.buildless/config.toml`, `package.json:buildcache`)
   * - User-level configuration file (`~/.config/buildless.toml`, `~/.buildless/config.toml`)
   */
  public val apiKey: Property<ApiKey?>

  /**
   * ### Transport
   *
   * Transport mechanism to use when interacting with the Buildless cache.
   *
   * The transport mechanism implements protocol support, transport security (TLS), and authorization. The default
   * transport is [CacheTransport.BUILTIN] which uses Gradle's built-in HTTP build cache engine.
   */
  public val transport: Property<CacheTransport>

  /**
   * ### Telemetry
   *
   * Whether to enable telemetry for this build.
   *
   * Telemetry services send various events to the Buildless API, and additionally annotate builds with a unique ID
   * which the service uses to correlate cache traffic. This is useful for debugging and diagnostics, and additionally
   * powers several types of reporting provided by Buildless.
   *
   * This data is inherently sensitive, and so it is never sold or shared with third parties (it's not that kind of
   * analytics/telemetry). This feature may be disabled at any time, but it will result in missing report data.
   */
  public val telemetry: Property<Boolean>

  /**
   * ### Debug
   *
   * Specifies whether "debug mode" is active within the Buildless plugin. When debug mode is active, verbose logging
   * is emitted to the terminal, and various trace values are sent to the Buildless API, which can be used to diagnose
   * issues with the cache from the web dashboard.
   */
  public override val debug: Property<Boolean>

  /**
   * ### Error Reporting
   *
   * Specifies whether errors should be reported to the Buildless API. When error reporting is active, errors which
   * are encountered when interacting with the cache are reported to a central service. These logs include only the
   * authorization material provided by the developer, along with light context from the Gradle build.
   *
   * For an exhaustive list of telemetry sent during error reporting, please contact support. It isn't anything weird,
   * we just haven't documented it fully yet.
   */
  public val reportErrors: Property<Boolean>

  /**
   * ### Local Cache Settings
   *
   * Specifies local cache settings for this Gradle project. Local caching keeps various cached objects in a directory
   * on-disk, and objects never leave the developer's machine. Local caching is enabled by default, and generally it
   * is recommended to keep it active.
   */
  public val local: MutableLocalCacheSettings

  /**
   * Alias for [local] cache settings.
   */
  public val localCache: MutableLocalCacheSettings get() = local

  /**
   * ### Agent Cache Settings
   *
   * Specifies agent cache settings for this Gradle project. Agent caching keeps built outputs within a local cache
   * service, like Buildless Agent. Agent caching is enabled by default if an API key is present in the local
   * environment, using the main Gradle cache endpoint.
   */
  public val agent: MutableAgentCacheSettings

  /**
   * ### Remote Cache Settings
   *
   * Specifies remote cache settings for this Gradle project. Remote caching keeps built outputs within a remote cache
   * service, like Buildless. Remote caching is enabled by default if an API key is present in the local environment,
   * using the main Gradle cache endpoint.
   */
  public val cloud: MutableRemoteCacheSettings

  /**
   * Alias for [cloud] remote cache settings.
   */
  public val remoteCache: MutableRemoteCacheSettings get() = cloud

  /**
   * Set the API key from the provided string [value].
   *
   * @param value API key value to use.
   * @return Checked API key value.
   * @throws ApiKey.InvalidKey if the provided key [value] is found to be malformed.
   */
  @Throws(ApiKey.InvalidKey::class)
  public fun apiKey(value: String): Unit = apiKey(ApiKey.of(value))

  /**
   * Set the API [key] to use when interacting with the Buildless service.
   *
   * This interface accepts a key which has already been checked for use. For a full description of how API keys are
   * resolved and loaded, see the [apiKey] field.
   *
   * @param key API key to use (pre-checked).
   */
  public fun apiKey(key: ApiKey) {
    apiKey.set(key)
  }

  /**
   * Configure agent caching settings with the provided [config] function.
   *
   * @param config Function which configures agent caching settings.
   */
  public fun agent(config: MutableAgentCacheSettings.() -> Unit)

  /**
   * Configure local caching settings with the provided [config] function.
   *
   * @param config Function which configures local caching settings.
   */
  public fun localCache(config: MutableLocalCacheSettings.() -> Unit)

  /**
   * Configure remote caching settings with the provided [config] function.
   *
   * @param config Function which configures remote caching settings.
   */
  public fun remoteCache(config: MutableRemoteCacheSettings.() -> Unit)
}

/**
 * ## Buildless Extension API: Project-level Settings
 *
 * Defines the interface made available when configuring project-level settings w.r.t. the Buildless plugin. Project
 * level settings mostly deal with logging and ignoring tasks for the cache.
 */
public interface BuildlessProjectSettings : ConfigAPI<Project> {
  // Nothing at this time.
}

/**
 * This interface, along with nested types, defines the public API surface for the Buildless plugin for Gradle.
 * Developers may customize their remote cache use via methods and fields defined herein.
 *
 * While Buildless may be used directly as a drop-in Gradle remote cache, the plugin surfaces additional configuration
 * and optimizations which are out-of-reach without custom code.
 *
 * ## Using the plugin
 *
 * To use Buildless from a Gradle build, the plugin must be applied at settings time, from `settings.gradle.kts` (or the
 * Groovy equivalent):
 *
 * ```kotlin
 * plugins {
 *   id("build.less")
 * }
 *
 * buildless {
 *   // settings go here
 * }
 * ```
 *
 * The above is all that is needed in many cases to install and use Buildless as a remote cache. This loads the user's
 * API key from any of the following sources:
 *
 * - Environment variables (`BUILDLESS_API_KEY`, `GRADLE_CACHE_PASSWORD`)
 * - System properties (`buildless.apiKey`, `cachePassword`)
 * - Local file (`.buildless.toml`, `.github/buildless.toml`, `.buildless/config.toml`, `package.json:buildcache`)
 * - User-level configuration file (`~/.config/buildless.toml`, `~/.buildless/config.toml`)
 *
 * So long as an API key is present, the above `settings.gradle.kts` code sample is sufficient to configure remote build
 * caching within a Gradle build.
 *
 * ## Applying Gradle cache settings
 *
 * If the `settings.gradle.kts` file where this plug-in is applied also performs its own configuration of Gradle's build
 * cache, it will prevail over this plug-in and no action is taken.
 *
 * If the developer wishes to configure Gradle's build caching features all in one go, they may do so via the local and
 * remote cache settings provided by this plugin (i.e, no further configuration is required).
 *
 * @param Target Target type context within which this extension is used.
 * @param Settings Settings context, within which plug-in configuration runs.
 */
public interface BuildlessExtensionAPI<Target, Settings> : BuildlessSettings where Settings : ConfigAPI<Target> {
  /**
   * ### Base Cache Settings
   *
   * Describes generic cache settings which apply to all types of caches; see [LocalCacheSettings] and
   * [RemoteCacheSettings] for further information.
   *
   * @see LocalCacheSettings for the interface provided for local build cache options.
   * @see RemoteCacheSettings for the interface provided for remote build cache options.
   */
  public sealed interface CacheSettings {
    /**
     * Indicates whether a given cache sub-system should be enabled or disabled (either a local cache or remote cache).
     * If a caching sub-system is "disabled," it is not used *at all* for the current build.
     *
     * Enabling a cache sub-system only enables *checks* for cached objects (i.e. fetches). Pushes to the cache (i.e.
     * uploads) are governed by other properties (and are enabled by default).
     */
    public val enabled: Property<Boolean>

    /**
     * Shorthand function to disable this cache configuration.
     */
    public fun disable() {
      enabled.set(false)
    }

    /**
     * Shorthand function to enable this cache configuration.
     */
    public fun enable() {
      enabled.set(true)
    }
  }

  /**
   * Main configuration entrypoint for the extension implementing this interface; not meant to be used by end-user code.
   *
   * @param context Context instance for the target of this configuration.
   * @param configure Lambda to execute which configures the [context] within the receivership of [Settings].
   */
  public fun config(context: Target, configure: Settings.() -> Unit)
}
