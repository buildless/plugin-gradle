//[buildless-plugin-gradle](../../../../index.md)/[build.less.plugin.gradle](../../index.md)/[BuildlessExtensionAPI](../index.md)/[MutableRemoteCacheSettings](index.md)

# MutableRemoteCacheSettings

[JVM (Gradle)]\
interface [MutableRemoteCacheSettings](index.md) : [BuildlessExtensionAPI.MutableCacheSettings](../-mutable-cache-settings/index.md), [BuildlessExtensionAPI.RemoteCacheSettings](../-remote-cache-settings/index.md)

###  Mutable Settings: Remote Cache

Remote cache settings for the Buildless Gradle plugin, in mutable form for use in Gradle's `settings.gradle.kts` file. The plugin configures sensible defaults if not otherwise specified. Remote caching is typically not possible unless an API key is resolvable.

## Properties

| Name | Summary |
|---|---|
| [enabled](../-mutable-cache-settings/enabled.md) | [JVM (Gradle)]<br>abstract override var [enabled](../-mutable-cache-settings/enabled.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Indicates whether a given cache sub-system should be enabled or disabled (either a local cache or remote cache). If a caching sub-system is &quot;disabled,&quot; it is not used *at all* for the current build. |
| [push](push.md) | [JVM (Gradle)]<br>abstract override var [push](push.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Whether build outputs will be uploaded to the cache as needed; enabled by default. |
