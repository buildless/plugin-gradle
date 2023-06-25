//[buildless-plugin-gradle](../../../../index.md)/[build.less.plugin.gradle](../../index.md)/[BuildlessExtensionAPI](../index.md)/[BuildlessSettings](index.md)/[remoteCache](remote-cache.md)

# remoteCache

[JVM (Gradle)]\
abstract fun [remoteCache](remote-cache.md)(config: [BuildlessExtensionAPI.MutableRemoteCacheSettings](../-mutable-remote-cache-settings/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))

Configure remote caching settings with the provided [config](remote-cache.md) function.

#### Parameters

JVM (Gradle)

| | |
|---|---|
| config | Function which configures remote caching settings. |

[JVM (Gradle)]\
abstract override val [remoteCache](remote-cache.md): [BuildlessExtensionAPI.MutableRemoteCacheSettings](../-mutable-remote-cache-settings/index.md)

###  Remote Cache Settings

Specifies remote cache settings for this Gradle project. Remote caching keeps built outputs within a remote cache service, like Buildless. Remote caching is enabled by default if an API key is present in the local environment, using the main Gradle cache endpoint.
