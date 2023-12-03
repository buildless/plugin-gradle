//[buildless-plugin-gradle](../../../index.md)/[build.less.plugin.gradle](../index.md)/[AgentConfig](index.md)

# AgentConfig

[JVM (Gradle)]\
@Serializable

data class [AgentConfig](index.md)(val pid: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val port: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val socket: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, val control: [AgentConfig.AgentEndpoint](-agent-endpoint/index.md)? = null)

# Agent Configuration

Describes the structure of a local agent configuration rendezvous file.

## Constructors

| | |
|---|---|
| [AgentConfig](-agent-config.md) | [JVM (Gradle)]<br>constructor(pid: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), port: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), socket: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, control: [AgentConfig.AgentEndpoint](-agent-endpoint/index.md)? = null) |

## Types

| Name | Summary |
|---|---|
| [AgentEndpoint](-agent-endpoint/index.md) | [JVM (Gradle)]<br>@Serializable<br>data class [AgentEndpoint](-agent-endpoint/index.md)(val port: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val socket: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null)<br>Describes a single agent endpoint. |

## Properties

| Name | Summary |
|---|---|
| [control](control.md) | [JVM (Gradle)]<br>val [control](control.md): [AgentConfig.AgentEndpoint](-agent-endpoint/index.md)? = null |
| [pid](pid.md) | [JVM (Gradle)]<br>val [pid](pid.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [port](port.md) | [JVM (Gradle)]<br>val [port](port.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [socket](socket.md) | [JVM (Gradle)]<br>val [socket](socket.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null |
