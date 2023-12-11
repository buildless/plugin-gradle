//[buildless-plugin-gradle](../../../../index.md)/[build.less.plugin.gradle](../../index.md)/[AgentConfig](../index.md)/[AgentEndpoint](index.md)

# AgentEndpoint

@Serializable

data class [AgentEndpoint](index.md)(val port: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val socket: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null)

Describes a single agent endpoint.

#### Parameters

JVM (Gradle)

| | |
|---|---|
| port | Port where a service is listening. |
| socket | Unix domain socket where a service is listening (if enabled). |

## Constructors

| | |
|---|---|
| [AgentEndpoint](-agent-endpoint.md) | [JVM (Gradle)]<br>constructor(port: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), socket: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null) |

## Properties

| Name | Summary |
|---|---|
| [port](port.md) | [JVM (Gradle)]<br>val [port](port.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [socket](socket.md) | [JVM (Gradle)]<br>val [socket](socket.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null |
