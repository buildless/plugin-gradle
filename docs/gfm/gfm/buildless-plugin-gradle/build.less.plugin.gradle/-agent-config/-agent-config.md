//[buildless-plugin-gradle](../../../index.md)/[build.less.plugin.gradle](../index.md)/[AgentConfig](index.md)/[AgentConfig](-agent-config.md)

# AgentConfig

[JVM (Gradle)]\
constructor(pid: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), port: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), socket: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, control: [AgentConfig.AgentEndpoint](-agent-endpoint/index.md)? = null)

#### Parameters

JVM (Gradle)

| | |
|---|---|
| pid | Process ID where the Buildless Agent is running. |
| port | TCP port where the Agent service is listening. |
| socket | Unix domain socket where the Agent service is listening (if enabled). |
| control | Coordinates for the Agent control service. |
