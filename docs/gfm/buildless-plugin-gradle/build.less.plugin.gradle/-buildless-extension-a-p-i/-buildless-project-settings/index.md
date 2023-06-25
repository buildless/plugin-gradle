//[buildless-plugin-gradle](../../../../index.md)/[build.less.plugin.gradle](../../index.md)/[BuildlessExtensionAPI](../index.md)/[BuildlessProjectSettings](index.md)

# BuildlessProjectSettings

[JVM (Gradle)]\
interface [BuildlessProjectSettings](index.md) : [BuildlessExtensionAPI.ConfigAPI](../-config-a-p-i/index.md)&lt;Project&gt; 

##  Buildless Extension API: Project-level Settings

Defines the interface made available when configuring project-level settings w.r.t. the Buildless plugin. Project level settings mostly deal with logging and ignoring tasks for the cache.

## Properties

| Name | Summary |
|---|---|
| [debug](../-config-a-p-i/debug.md) | [JVM (Gradle)]<br>abstract val [debug](../-config-a-p-i/debug.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Whether the Buildless plugin is operating in debug mode; debug mode enables verbose logging, and applies trace values to API calls, so that they may be diagnosed from the dashboard. |
| [localCache](../-config-a-p-i/local-cache.md) | [JVM (Gradle)]<br>abstract val [localCache](../-config-a-p-i/local-cache.md): [BuildlessExtensionAPI.LocalCacheSettings](../-local-cache-settings/index.md)<br>Local cache settings for this Gradle project. Local caching keeps various cached objects in a directory on-disk, and objects never leave the developer's machine. |
| [remoteCache](../-config-a-p-i/remote-cache.md) | [JVM (Gradle)]<br>abstract val [remoteCache](../-config-a-p-i/remote-cache.md): [BuildlessExtensionAPI.RemoteCacheSettings](../-remote-cache-settings/index.md)<br>Remote cache settings for this Gradle project. Remote caching keeps built outputs within a remote service, like, say, Buildless (although any remote caching can be configured via these settings). |
