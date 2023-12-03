//[buildless-plugin-gradle](../../../../index.md)/[build.less.plugin.gradle](../../index.md)/[BuildlessExtension](../index.md)/[RemoteCacheSetup](index.md)

# RemoteCacheSetup

[JVM (Gradle)]\
inner class [RemoteCacheSetup](index.md) : [MutableRemoteCacheSettings](../../-mutable-remote-cache-settings/index.md)

Mutable settings class for remote build cache settings.

## Constructors

| | |
|---|---|
| [RemoteCacheSetup](-remote-cache-setup.md) | [JVM (Gradle)]<br>constructor() |

## Properties

| Name | Summary |
|---|---|
| [enabled](enabled.md) | [JVM (Gradle)]<br>open override var [enabled](enabled.md): Property&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt;<br>Indicates whether a given cache sub-system should be enabled or disabled (either a local cache or remote cache). If a caching sub-system is &quot;disabled,&quot; it is not used *at all* for the current build. |
| [push](push.md) | [JVM (Gradle)]<br>open override var [push](push.md): Property&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt;<br>Whether build outputs will be uploaded to the cache as needed; enabled by default. |

## Functions

| Name | Summary |
|---|---|
| [disable](../../-buildless-extension-a-p-i/-cache-settings/disable.md) | [JVM (Gradle)]<br>open fun [disable](../../-buildless-extension-a-p-i/-cache-settings/disable.md)()<br>Shorthand function to disable this cache configuration. |
| [enable](../../-buildless-extension-a-p-i/-cache-settings/enable.md) | [JVM (Gradle)]<br>open fun [enable](../../-buildless-extension-a-p-i/-cache-settings/enable.md)()<br>Shorthand function to enable this cache configuration. |
