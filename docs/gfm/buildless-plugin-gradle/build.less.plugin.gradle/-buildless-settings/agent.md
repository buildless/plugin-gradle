//[buildless-plugin-gradle](../../../index.md)/[build.less.plugin.gradle](../index.md)/[BuildlessSettings](index.md)/[agent](agent.md)

# agent

[JVM (Gradle)]\
abstract fun [agent](agent.md)(config: [MutableAgentCacheSettings](../-mutable-agent-cache-settings/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))

Configure agent caching settings with the provided [config](agent.md) function.

#### Parameters

JVM (Gradle)

| | |
|---|---|
| config | Function which configures agent caching settings. |

[JVM (Gradle)]\
abstract val [agent](agent.md): [MutableAgentCacheSettings](../-mutable-agent-cache-settings/index.md)

###  Agent Cache Settings

Specifies agent cache settings for this Gradle project. Agent caching keeps built outputs within a local cache service, like Buildless Agent. Agent caching is enabled by default if an API key is present in the local environment, using the main Gradle cache endpoint.
