//[buildless-plugin-gradle](../../../index.md)/[build.less.plugin.gradle](../index.md)/[BuildlessExtensionAPI](index.md)

# BuildlessExtensionAPI

interface [BuildlessExtensionAPI](index.md)&lt;[Target](index.md), [Settings](index.md) : [BuildlessExtensionAPI.ConfigAPI](-config-a-p-i/index.md)&lt;[Target](index.md)&gt;&gt;

This interface, along with nested types, defines the public API surface for the Buildless plugin for Gradle. Developers may customize their remote cache use via methods and fields defined herein.

While Buildless may be used directly as a drop-in Gradle remote cache, the plugin surfaces additional configuration and optimizations which are out-of-reach without custom code.

##  Using the plugin

To use Buildless from a Gradle build, the plugin must be applied at settings time, from `settings.gradle.kts` (or the Groovy equivalent):

```kotlin
import build.less.plugin.settings.buildless

plugins {
  id("build.less")
}

buildless {
  // settings go here
}
```

The above is all that is needed in many cases to install and use Buildless as a remote cache. This loads the user's API key from any of the following sources:

- 
   Environment variables (`BUILDLESS_API_KEY`, `GRADLE_CACHE_PASSWORD`)
- 
   System properties (`buildless.apiKey`, `cachePassword`)
- 
   Local file (`.buildless.toml`, `.github/buildless.toml`, `.buildless/config.toml`, `package.json:buildcache`)
- 
   User-level configuration file (`~/.config/buildless.toml`, `~/.buildless/config.toml`)

So long as an API key is present, the above `settings.gradle.kts` code sample is sufficient to configure remote build caching within a Gradle build.

##  Applying Gradle cache settings

If the `settings.gradle.kts` file where this plug-in is applied also performs its own configuration of Gradle's build cache, it will prevail over this plug-in and no action is taken.

If the developer wishes to configure Gradle's build caching features all in one go, they may do so via the local and remote cache settings provided by this plugin (i.e, no further configuration is required).

#### Parameters

JVM (Gradle)

| | |
|---|---|
| Target | Target type context within which this extension is used. |
| Settings | Settings context, within which plug-in configuration runs. |

## Types

| Name | Summary |
|---|---|
| [BuildlessProjectSettings](-buildless-project-settings/index.md) | [JVM (Gradle)]<br>interface [BuildlessProjectSettings](-buildless-project-settings/index.md) : [BuildlessExtensionAPI.ConfigAPI](-config-a-p-i/index.md)&lt;Project&gt; <br>Defines the interface made available when configuring project-level settings w.r.t. the Buildless plugin. Project level settings mostly deal with logging and ignoring tasks for the cache. |
| [BuildlessSettings](-buildless-settings/index.md) | [JVM (Gradle)]<br>interface [BuildlessSettings](-buildless-settings/index.md) : [BuildlessExtensionAPI.ConfigAPI](-config-a-p-i/index.md)&lt;Settings&gt; <br>Describes top-level settings which are available within `settings.gradle.kts` for a Buildless-enabled Gradle project. These settings apply within the scope of file in question (usually, all projects within a multi-project Gradle build, or a single Gradle project). |
| [CacheSettings](-cache-settings/index.md) | [JVM (Gradle)]<br>interface [CacheSettings](-cache-settings/index.md)<br>Describes generic cache settings which apply to all types of caches; see [LocalCacheSettings](-local-cache-settings/index.md) and [RemoteCacheSettings](-remote-cache-settings/index.md) for further information. |
| [ConfigAPI](-config-a-p-i/index.md) | [JVM (Gradle)]<br>interface [ConfigAPI](-config-a-p-i/index.md)&lt;[Target](-config-a-p-i/index.md)&gt;<br>Describes the base interface from which all plugin settings interfaces inherit. Since the Buildless plugin can be configured in both the context of a Gradle [Settings](index.md) (initialization) file, and also a Project, the API needs to be extended into both circumstances; this interface bears the common fields between both. |
| [LocalCacheSettings](-local-cache-settings/index.md) | [JVM (Gradle)]<br>interface [LocalCacheSettings](-local-cache-settings/index.md) : [BuildlessExtensionAPI.CacheSettings](-cache-settings/index.md)<br>Specifies settings which configure Gradle's local build cache; these may be provided in lieu of settings specified directly with Gradle. Local build cache settings specified directly to Gradle take precedence over these. By default, the local build cache is enabled. |
| [MutableCacheSettings](-mutable-cache-settings/index.md) | [JVM (Gradle)]<br>interface [MutableCacheSettings](-mutable-cache-settings/index.md) : [BuildlessExtensionAPI.CacheSettings](-cache-settings/index.md)<br>Top-level abstract cache settings for the Buildless Gradle plugin, in mutable form for use in Gradle's `settings.gradle.kts` file. This is the base type for [MutableLocalCacheSettings](-mutable-local-cache-settings/index.md) and [MutableRemoteCacheSettings](-mutable-remote-cache-settings/index.md). |
| [MutableLocalCacheSettings](-mutable-local-cache-settings/index.md) | [JVM (Gradle)]<br>interface [MutableLocalCacheSettings](-mutable-local-cache-settings/index.md) : [BuildlessExtensionAPI.MutableCacheSettings](-mutable-cache-settings/index.md), [BuildlessExtensionAPI.LocalCacheSettings](-local-cache-settings/index.md)<br>Local cache settings for the Buildless Gradle plugin, in mutable form for use in Gradle's `settings.gradle.kts` file. The plugin configures sensible defaults if not otherwise specified. |
| [MutableRemoteCacheSettings](-mutable-remote-cache-settings/index.md) | [JVM (Gradle)]<br>interface [MutableRemoteCacheSettings](-mutable-remote-cache-settings/index.md) : [BuildlessExtensionAPI.MutableCacheSettings](-mutable-cache-settings/index.md), [BuildlessExtensionAPI.RemoteCacheSettings](-remote-cache-settings/index.md)<br>Remote cache settings for the Buildless Gradle plugin, in mutable form for use in Gradle's `settings.gradle.kts` file. The plugin configures sensible defaults if not otherwise specified. Remote caching is typically not possible unless an API key is resolvable. |
| [RemoteCacheSettings](-remote-cache-settings/index.md) | [JVM (Gradle)]<br>interface [RemoteCacheSettings](-remote-cache-settings/index.md) : [BuildlessExtensionAPI.CacheSettings](-cache-settings/index.md)<br>Specifies settings which configure Gradle's remote build cache; these may be provided in lieu of settings specified directly with Gradle, and are preferred over directly specifying settings. By default, the remote build cache is enabled in all circumstances. |

## Functions

| Name | Summary |
|---|---|
| [config](config.md) | [JVM (Gradle)]<br>abstract fun [config](config.md)(context: [Target](index.md), configure: [Settings](index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))<br>Main configuration entrypoint for the extension implementing this interface; not meant to be used by end-user code. |
