//[buildless-plugin-gradle](../../../../index.md)/[build.less.plugin.gradle](../../index.md)/[BuildlessExtensionAPI](../index.md)/[BuildlessSettings](index.md)/[localCache](local-cache.md)

# localCache

[JVM (Gradle)]\
abstract fun [localCache](local-cache.md)(config: [BuildlessExtensionAPI.MutableLocalCacheSettings](../-mutable-local-cache-settings/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))

Configure local caching settings with the provided [config](local-cache.md) function.

#### Parameters

JVM (Gradle)

| | |
|---|---|
| config | Function which configures local caching settings. |

[JVM (Gradle)]\
abstract override val [localCache](local-cache.md): [BuildlessExtensionAPI.MutableLocalCacheSettings](../-mutable-local-cache-settings/index.md)

###  Local Cache Settings

Specifies local cache settings for this Gradle project. Local caching keeps various cached objects in a directory on-disk, and objects never leave the developer's machine. Local caching is enabled by default, and generally it is recommended to keep it active.
