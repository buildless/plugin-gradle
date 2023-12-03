//[buildless-plugin-gradle](../../../../index.md)/[build.less.plugin.gradle](../../index.md)/[BuildlessExtensionAPI](../index.md)/[CacheSettings](index.md)

# CacheSettings

interface [CacheSettings](index.md)

###  Base Cache Settings

Describes generic cache settings which apply to all types of caches; see [LocalCacheSettings](../../-local-cache-settings/index.md) and [RemoteCacheSettings](../../-remote-cache-settings/index.md) for further information.

#### See also

| | |
|---|---|
| [LocalCacheSettings](../../-local-cache-settings/index.md) | for the interface provided for local build cache options. |
| [RemoteCacheSettings](../../-remote-cache-settings/index.md) | for the interface provided for remote build cache options. |

#### Inheritors

| |
|---|
| [LocalCacheSettings](../../-local-cache-settings/index.md) |
| [AgentCacheSettings](../../-agent-cache-settings/index.md) |
| [RemoteCacheSettings](../../-remote-cache-settings/index.md) |
| [MutableCacheSettings](../../-mutable-cache-settings/index.md) |

## Properties

| Name | Summary |
|---|---|
| [enabled](enabled.md) | [JVM (Gradle)]<br>abstract val [enabled](enabled.md): Property&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt;<br>Indicates whether a given cache sub-system should be enabled or disabled (either a local cache or remote cache). If a caching sub-system is &quot;disabled,&quot; it is not used *at all* for the current build. |

## Functions

| Name | Summary |
|---|---|
| [disable](disable.md) | [JVM (Gradle)]<br>open fun [disable](disable.md)()<br>Shorthand function to disable this cache configuration. |
| [enable](enable.md) | [JVM (Gradle)]<br>open fun [enable](enable.md)()<br>Shorthand function to enable this cache configuration. |
