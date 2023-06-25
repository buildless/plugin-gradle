//[buildless-plugin-gradle](../../../../index.md)/[build.less.plugin.gradle](../../index.md)/[BuildlessExtensionAPI](../index.md)/[MutableLocalCacheSettings](index.md)

# MutableLocalCacheSettings

[JVM (Gradle)]\
interface [MutableLocalCacheSettings](index.md) : [BuildlessExtensionAPI.MutableCacheSettings](../-mutable-cache-settings/index.md), [BuildlessExtensionAPI.LocalCacheSettings](../-local-cache-settings/index.md)

###  Mutable Settings: Local Cache

Local cache settings for the Buildless Gradle plugin, in mutable form for use in Gradle's `settings.gradle.kts` file. The plugin configures sensible defaults if not otherwise specified.

## Properties

| Name | Summary |
|---|---|
| [directory](directory.md) | [JVM (Gradle)]<br>abstract override var [directory](directory.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?<br>Directory where local cached objects are stored; if unset, Buildless sets a sensible default. If this value is set before the Buildless plugin is invoked, the user's value takes precedence and this value is rendered inert. |
| [enabled](../-mutable-cache-settings/enabled.md) | [JVM (Gradle)]<br>abstract override var [enabled](../-mutable-cache-settings/enabled.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Indicates whether a given cache sub-system should be enabled or disabled (either a local cache or remote cache). If a caching sub-system is &quot;disabled,&quot; it is not used *at all* for the current build. |
