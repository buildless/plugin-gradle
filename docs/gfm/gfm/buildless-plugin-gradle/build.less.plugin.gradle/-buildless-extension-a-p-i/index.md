//[buildless-plugin-gradle](../../../index.md)/[build.less.plugin.gradle](../index.md)/[BuildlessExtensionAPI](index.md)

# BuildlessExtensionAPI

interface [BuildlessExtensionAPI](index.md)&lt;[Target](index.md), [Settings](index.md) : [ConfigAPI](../-config-a-p-i/index.md)&lt;[Target](index.md)&gt;&gt; : [BuildlessSettings](../-buildless-settings/index.md)

This interface, along with nested types, defines the public API surface for the Buildless plugin for Gradle. Developers may customize their remote cache use via methods and fields defined herein.

While Buildless may be used directly as a drop-in Gradle remote cache, the plugin surfaces additional configuration and optimizations which are out-of-reach without custom code.

##  Using the plugin

To use Buildless from a Gradle build, the plugin must be applied at settings time, from `settings.gradle.kts` (or the Groovy equivalent):

```kotlin
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

#### Inheritors

| |
|---|
| [BuildlessExtension](../-buildless-extension/index.md) |

## Types

| Name | Summary |
|---|---|
| [CacheSettings](-cache-settings/index.md) | [JVM (Gradle)]<br>interface [CacheSettings](-cache-settings/index.md)<br>Describes generic cache settings which apply to all types of caches; see [LocalCacheSettings](../-local-cache-settings/index.md) and [RemoteCacheSettings](../-remote-cache-settings/index.md) for further information. |

## Properties

| Name | Summary |
|---|---|
| [agent](../-buildless-settings/agent.md) | [JVM (Gradle)]<br>abstract val [agent](../-buildless-settings/agent.md): [MutableAgentCacheSettings](../-mutable-agent-cache-settings/index.md)<br>Specifies agent cache settings for this Gradle project. Agent caching keeps built outputs within a local cache service, like Buildless Agent. Agent caching is enabled by default if an API key is present in the local environment, using the main Gradle cache endpoint. |
| [apiKey](../-buildless-settings/api-key.md) | [JVM (Gradle)]<br>abstract val [apiKey](../-buildless-settings/api-key.md): Property&lt;[ApiKey](../-api-key/index.md)?&gt;<br>API key that should be used to identify this project to the Buildless cache service. |
| [cloud](../-buildless-settings/cloud.md) | [JVM (Gradle)]<br>abstract val [cloud](../-buildless-settings/cloud.md): [MutableRemoteCacheSettings](../-mutable-remote-cache-settings/index.md)<br>Specifies remote cache settings for this Gradle project. Remote caching keeps built outputs within a remote cache service, like Buildless. Remote caching is enabled by default if an API key is present in the local environment, using the main Gradle cache endpoint. |
| [debug](../-buildless-settings/debug.md) | [JVM (Gradle)]<br>abstract override val [debug](../-buildless-settings/debug.md): Property&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt;<br>Specifies whether &quot;debug mode&quot; is active within the Buildless plugin. When debug mode is active, verbose logging is emitted to the terminal, and various trace values are sent to the Buildless API, which can be used to diagnose issues with the cache from the web dashboard. |
| [local](../-buildless-settings/local.md) | [JVM (Gradle)]<br>abstract val [local](../-buildless-settings/local.md): [MutableLocalCacheSettings](../-mutable-local-cache-settings/index.md)<br>Specifies local cache settings for this Gradle project. Local caching keeps various cached objects in a directory on-disk, and objects never leave the developer's machine. Local caching is enabled by default, and generally it is recommended to keep it active. |
| [localCache](../-buildless-settings/local-cache.md) | [JVM (Gradle)]<br>open val [localCache](../-buildless-settings/local-cache.md): [MutableLocalCacheSettings](../-mutable-local-cache-settings/index.md)<br>Alias for [local](../-buildless-settings/local.md) cache settings. |
| [remoteCache](../-buildless-settings/remote-cache.md) | [JVM (Gradle)]<br>open val [remoteCache](../-buildless-settings/remote-cache.md): [MutableRemoteCacheSettings](../-mutable-remote-cache-settings/index.md)<br>Alias for [cloud](../-buildless-settings/cloud.md) remote cache settings. |
| [reportErrors](../-buildless-settings/report-errors.md) | [JVM (Gradle)]<br>abstract val [reportErrors](../-buildless-settings/report-errors.md): Property&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt;<br>Specifies whether errors should be reported to the Buildless API. When error reporting is active, errors which are encountered when interacting with the cache are reported to a central service. These logs include only the authorization material provided by the developer, along with light context from the Gradle build. |
| [telemetry](../-buildless-settings/telemetry.md) | [JVM (Gradle)]<br>abstract val [telemetry](../-buildless-settings/telemetry.md): Property&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt;<br>Whether to enable telemetry for this build. |
| [transport](../-buildless-settings/transport.md) | [JVM (Gradle)]<br>abstract val [transport](../-buildless-settings/transport.md): Property&lt;[CacheTransport](../-cache-transport/index.md)&gt;<br>Transport mechanism to use when interacting with the Buildless cache. |

## Functions

| Name | Summary |
|---|---|
| [agent](../-buildless-settings/agent.md) | [JVM (Gradle)]<br>abstract fun [agent](../-buildless-settings/agent.md)(config: [MutableAgentCacheSettings](../-mutable-agent-cache-settings/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))<br>Configure agent caching settings with the provided [config](../-buildless-settings/agent.md) function. |
| [apiKey](../-buildless-settings/api-key.md) | [JVM (Gradle)]<br>open fun [apiKey](../-buildless-settings/api-key.md)(key: [ApiKey](../-api-key/index.md))<br>Set the API [key](../-buildless-settings/api-key.md) to use when interacting with the Buildless service.<br>[JVM (Gradle)]<br>open fun [apiKey](../-buildless-settings/api-key.md)(value: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>Set the API key from the provided string [value](../-buildless-settings/api-key.md). |
| [config](config.md) | [JVM (Gradle)]<br>abstract fun [config](config.md)(context: [Target](index.md), configure: [Settings](index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))<br>Main configuration entrypoint for the extension implementing this interface; not meant to be used by end-user code. |
| [localCache](../-buildless-settings/local-cache.md) | [JVM (Gradle)]<br>abstract fun [localCache](../-buildless-settings/local-cache.md)(config: [MutableLocalCacheSettings](../-mutable-local-cache-settings/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))<br>Configure local caching settings with the provided [config](../-buildless-settings/local-cache.md) function. |
| [remoteCache](../-buildless-settings/remote-cache.md) | [JVM (Gradle)]<br>abstract fun [remoteCache](../-buildless-settings/remote-cache.md)(config: [MutableRemoteCacheSettings](../-mutable-remote-cache-settings/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))<br>Configure remote caching settings with the provided [config](../-buildless-settings/remote-cache.md) function. |
