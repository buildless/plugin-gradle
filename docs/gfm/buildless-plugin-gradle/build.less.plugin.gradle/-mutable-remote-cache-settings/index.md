//[buildless-plugin-gradle](../../../index.md)/[build.less.plugin.gradle](../index.md)/[MutableRemoteCacheSettings](index.md)

# MutableRemoteCacheSettings

interface [MutableRemoteCacheSettings](index.md) : [MutableCacheSettings](../-mutable-cache-settings/index.md), [RemoteCacheSettings](../-remote-cache-settings/index.md)

###  Mutable Settings: Remote Cache

Remote cache settings for the Buildless Gradle plugin, in mutable form for use in Gradle's `settings.gradle.kts` file. The plugin configures sensible defaults if not otherwise specified. Remote caching is typically not possible unless an API key is resolvable.

#### Inheritors

| |
|---|
| [RemoteCacheSetup](../-buildless-extension/-remote-cache-setup/index.md) |

## Properties

| Name | Summary |
|---|---|
| [enabled](../-mutable-cache-settings/enabled.md) | [JVM (Gradle)]<br>abstract override val [enabled](../-mutable-cache-settings/enabled.md): Property&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt;<br>Indicates whether a given cache sub-system should be enabled or disabled (either a local cache or remote cache). If a caching sub-system is &quot;disabled,&quot; it is not used *at all* for the current build. |
| [push](push.md) | [JVM (Gradle)]<br>abstract override var [push](push.md): Property&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt;<br>Whether build outputs will be uploaded to the cache as needed; enabled by default. |

## Functions

| Name | Summary |
|---|---|
| [disable](../-buildless-extension-a-p-i/-cache-settings/disable.md) | [JVM (Gradle)]<br>open fun [disable](../-buildless-extension-a-p-i/-cache-settings/disable.md)()<br>Shorthand function to disable this cache configuration. |
| [enable](../-buildless-extension-a-p-i/-cache-settings/enable.md) | [JVM (Gradle)]<br>open fun [enable](../-buildless-extension-a-p-i/-cache-settings/enable.md)()<br>Shorthand function to enable this cache configuration. |
