//[buildless-plugin-gradle](../../../index.md)/[build.less.plugin.gradle](../index.md)/[MutableAgentCacheSettings](index.md)

# MutableAgentCacheSettings

interface [MutableAgentCacheSettings](index.md) : [MutableCacheSettings](../-mutable-cache-settings/index.md), [AgentCacheSettings](../-agent-cache-settings/index.md)

###  Mutable Settings: Agent Cache

Settings which relate to the Buildless Agent, in mutable form for use in Gradle's `settings.gradle.kts` file. The plugin configures sensible defaults if not otherwise specified.

#### Inheritors

| |
|---|
| [LocalAgentSetup](../-buildless-extension/-local-agent-setup/index.md) |

## Properties

| Name | Summary |
|---|---|
| [enabled](../-mutable-cache-settings/enabled.md) | [JVM (Gradle)]<br>abstract override val [enabled](../-mutable-cache-settings/enabled.md): Property&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt;<br>Indicates whether a given cache sub-system should be enabled or disabled (either a local cache or remote cache). If a caching sub-system is &quot;disabled,&quot; it is not used *at all* for the current build. |

## Functions

| Name | Summary |
|---|---|
| [disable](../-buildless-extension-a-p-i/-cache-settings/disable.md) | [JVM (Gradle)]<br>open fun [disable](../-buildless-extension-a-p-i/-cache-settings/disable.md)()<br>Shorthand function to disable this cache configuration. |
| [enable](../-buildless-extension-a-p-i/-cache-settings/enable.md) | [JVM (Gradle)]<br>open fun [enable](../-buildless-extension-a-p-i/-cache-settings/enable.md)()<br>Shorthand function to enable this cache configuration. |
