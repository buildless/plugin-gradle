/* Copyright (c) 2023 Elide Ventures LLC
 *
 * This is private computer source code. This code is part of an application which is licensed privately, as part of
 * intellectual property owned by Elide Ventures, LLC. All rights are reserved. Viewing and editing this code implies
 * agreement with the Elide Non-Disclosure Agreement and Elide Inventions Assignment Agreement.
 *
 * Code bearing this header may not be shared outside of authorized circumstances without prior written consent from
 * authorized corporate officers of Elide Ventures, LLC.
 */

package build.less.plugin.gradle

import org.gradle.api.Project
import org.gradle.api.initialization.Settings
import kotlin.jvm.Throws

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
 * import build.less.plugin.settings.buildless
 *
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
public interface BuildlessExtensionAPI<Target, Settings> where Settings : BuildlessExtensionAPI.ConfigAPI<Target> {
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
    public val enabled: Boolean
  }

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
    public val directory: String?
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
    public val push: Boolean
  }

  /**
   * ### Mutable Settings: Top-level
   *
   * Top-level abstract cache settings for the Buildless Gradle plugin, in mutable form for use in Gradle's
   * `settings.gradle.kts` file. This is the base type for [MutableLocalCacheSettings] and [MutableRemoteCacheSettings].
   */
  public sealed interface MutableCacheSettings : CacheSettings {
    public override var enabled: Boolean
  }

  /**
   * ### Mutable Settings: Local Cache
   *
   * Local cache settings for the Buildless Gradle plugin, in mutable form for use in Gradle's `settings.gradle.kts`
   * file. The plugin configures sensible defaults if not otherwise specified.
   */
  public interface MutableLocalCacheSettings : MutableCacheSettings, LocalCacheSettings {
    public override var directory: String?
  }

  /**
   * ### Mutable Settings: Remote Cache
   *
   * Remote cache settings for the Buildless Gradle plugin, in mutable form for use in Gradle's `settings.gradle.kts`
   * file. The plugin configures sensible defaults if not otherwise specified. Remote caching is typically not possible
   * unless an API key is resolvable.
   */
  public interface MutableRemoteCacheSettings : MutableCacheSettings, RemoteCacheSettings {
    public override var push: Boolean
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
    public val debug: Boolean

    /**
     * Local cache settings for this Gradle project. Local caching keeps various cached objects in a directory on-disk,
     * and objects never leave the developer's machine.
     *
     * It is generally recommended to leave local caching active. Buildless sets well-informed defaults, like moving the
     * default directory for local caching to a location within the project directory.
     */
    public val localCache: LocalCacheSettings

    /**
     * Remote cache settings for this Gradle project. Remote caching keeps built outputs within a remote service, like,
     * say, Buildless (although any remote caching can be configured via these settings).
     *
     * Remote caching is on by default if an API key is present in the local environment; it is recommended to leave
     * remote caching active at all times. Remote caching is designed to fail-fast and not to ever break the build.
     */
    public val remoteCache: RemoteCacheSettings
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
    public var apiKey: ApiKey?

    /**
     * ### Transport
     *
     * Transport mechanism to use when interacting with the Buildless cache.
     *
     * The transport mechanism implements protocol support, transport security (TLS), and authorization. The default
     * transport is [CacheTransport.STANDARD] which uses Gradle's built-in HTTP build cache engine.
     */
    public var transport: CacheTransport

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
    public var telemetry: Boolean

    /**
     * ### Debug
     *
     * Specifies whether "debug mode" is active within the Buildless plugin. When debug mode is active, verbose logging
     * is emitted to the terminal, and various trace values are sent to the Buildless API, which can be used to diagnose
     * issues with the cache from the web dashboard.
     */
    public override var debug: Boolean

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
    public var reportErrors: Boolean

    /**
     * ### Local Cache Settings
     *
     * Specifies local cache settings for this Gradle project. Local caching keeps various cached objects in a directory
     * on-disk, and objects never leave the developer's machine. Local caching is enabled by default, and generally it
     * is recommended to keep it active.
     */
    public override val localCache: MutableLocalCacheSettings

    /**
     * ### Remote Cache Settings
     *
     * Specifies remote cache settings for this Gradle project. Remote caching keeps built outputs within a remote cache
     * service, like Buildless. Remote caching is enabled by default if an API key is present in the local environment,
     * using the main Gradle cache endpoint.
     */
    public override val remoteCache: MutableRemoteCacheSettings

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
      apiKey = key
    }

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
   * Main configuration entrypoint for the extension implementing this interface; not meant to be used by end-user code.
   *
   * @param context Context instance for the target of this configuration.
   * @param configure Lambda to execute which configures the [context] within the receivership of [Settings].
   */
  public fun config(context: Target, configure: Settings.() -> Unit)
}
