//[buildless-plugin-gradle](../../../index.md)/[build.less.plugin.gradle](../index.md)/[BuildlessSettings](index.md)/[remoteCache](remote-cache.md)

# remoteCache

[JVM (Gradle)]\
abstract fun [remoteCache](remote-cache.md)(config: [MutableRemoteCacheSettings](../-mutable-remote-cache-settings/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))

Configure remote caching settings with the provided [config](remote-cache.md) function.

#### Parameters

JVM (Gradle)

| | |
|---|---|
| config | Function which configures remote caching settings. |

[JVM (Gradle)]\
open val [remoteCache](remote-cache.md): [MutableRemoteCacheSettings](../-mutable-remote-cache-settings/index.md)

Alias for [cloud](cloud.md) remote cache settings.
