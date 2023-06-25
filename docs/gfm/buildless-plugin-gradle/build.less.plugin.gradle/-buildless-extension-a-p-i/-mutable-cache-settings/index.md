//[buildless-plugin-gradle](../../../../index.md)/[build.less.plugin.gradle](../../index.md)/[BuildlessExtensionAPI](../index.md)/[MutableCacheSettings](index.md)

# MutableCacheSettings

interface [MutableCacheSettings](index.md) : [BuildlessExtensionAPI.CacheSettings](../-cache-settings/index.md)

###  Mutable Settings: Top-level

Top-level abstract cache settings for the Buildless Gradle plugin, in mutable form for use in Gradle's `settings.gradle.kts` file. This is the base type for [MutableLocalCacheSettings](../-mutable-local-cache-settings/index.md) and [MutableRemoteCacheSettings](../-mutable-remote-cache-settings/index.md).

#### Inheritors

| |
|---|
| [MutableLocalCacheSettings](../-mutable-local-cache-settings/index.md) |
| [MutableRemoteCacheSettings](../-mutable-remote-cache-settings/index.md) |

## Properties

| Name | Summary |
|---|---|
| [enabled](enabled.md) | [JVM (Gradle)]<br>abstract override var [enabled](enabled.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Indicates whether a given cache sub-system should be enabled or disabled (either a local cache or remote cache). If a caching sub-system is &quot;disabled,&quot; it is not used *at all* for the current build. |
