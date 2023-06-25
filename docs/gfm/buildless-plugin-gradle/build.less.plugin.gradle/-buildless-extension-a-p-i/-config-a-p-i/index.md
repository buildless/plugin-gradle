//[buildless-plugin-gradle](../../../../index.md)/[build.less.plugin.gradle](../../index.md)/[BuildlessExtensionAPI](../index.md)/[ConfigAPI](index.md)

# ConfigAPI

interface [ConfigAPI](index.md)&lt;[Target](index.md)&gt;

##  Buildless Extension API: Configuration

Describes the base interface from which all plugin settings interfaces inherit. Since the Buildless plugin can be configured in both the context of a Gradle [Settings](../index.md) (initialization) file, and also a Project, the API needs to be extended into both circumstances; this interface bears the common fields between both.

#### See also

| | |
|---|---|
| [BuildlessExtensionAPI.BuildlessSettings](../-buildless-settings/index.md) | for settings-time configuration of the plugin, which is shared across all projects. |
| [BuildlessExtensionAPI.BuildlessProjectSettings](../-buildless-project-settings/index.md) | for settings which apply at the level of a single Gradle project. |

#### Inheritors

| |
|---|
| [BuildlessSettings](../-buildless-settings/index.md) |
| [BuildlessProjectSettings](../-buildless-project-settings/index.md) |

## Properties

| Name | Summary |
|---|---|
| [debug](debug.md) | [JVM (Gradle)]<br>abstract val [debug](debug.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Whether the Buildless plugin is operating in debug mode; debug mode enables verbose logging, and applies trace values to API calls, so that they may be diagnosed from the dashboard. |
| [localCache](local-cache.md) | [JVM (Gradle)]<br>abstract val [localCache](local-cache.md): [BuildlessExtensionAPI.LocalCacheSettings](../-local-cache-settings/index.md)<br>Local cache settings for this Gradle project. Local caching keeps various cached objects in a directory on-disk, and objects never leave the developer's machine. |
| [remoteCache](remote-cache.md) | [JVM (Gradle)]<br>abstract val [remoteCache](remote-cache.md): [BuildlessExtensionAPI.RemoteCacheSettings](../-remote-cache-settings/index.md)<br>Remote cache settings for this Gradle project. Remote caching keeps built outputs within a remote service, like, say, Buildless (although any remote caching can be configured via these settings). |
