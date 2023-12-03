//[buildless-plugin-gradle](../../../index.md)/[build.less.plugin.gradle](../index.md)/[AgentCacheSettings](index.md)

# AgentCacheSettings

interface [AgentCacheSettings](index.md) : [BuildlessExtensionAPI.CacheSettings](../-buildless-extension-a-p-i/-cache-settings/index.md)

###  Agent Cache Settings

Specifies settings which relate specifically to the Buildless Agent, and are used to configure the agent's cache services with Gradle.

#### Inheritors

| |
|---|
| [MutableAgentCacheSettings](../-mutable-agent-cache-settings/index.md) |

## Properties

| Name | Summary |
|---|---|
| [enabled](../-buildless-extension-a-p-i/-cache-settings/enabled.md) | [JVM (Gradle)]<br>abstract val [enabled](../-buildless-extension-a-p-i/-cache-settings/enabled.md): Property&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt;<br>Indicates whether a given cache sub-system should be enabled or disabled (either a local cache or remote cache). If a caching sub-system is &quot;disabled,&quot; it is not used *at all* for the current build. |

## Functions

| Name | Summary |
|---|---|
| [disable](../-buildless-extension-a-p-i/-cache-settings/disable.md) | [JVM (Gradle)]<br>open fun [disable](../-buildless-extension-a-p-i/-cache-settings/disable.md)()<br>Shorthand function to disable this cache configuration. |
| [enable](../-buildless-extension-a-p-i/-cache-settings/enable.md) | [JVM (Gradle)]<br>open fun [enable](../-buildless-extension-a-p-i/-cache-settings/enable.md)()<br>Shorthand function to enable this cache configuration. |
