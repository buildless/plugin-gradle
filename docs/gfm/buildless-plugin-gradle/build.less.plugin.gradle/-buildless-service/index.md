//[buildless-plugin-gradle](../../../index.md)/[build.less.plugin.gradle](../index.md)/[BuildlessService](index.md)

# BuildlessService

[JVM (Gradle)]\
class [BuildlessService](index.md) : BuildService&lt;[BuildlessService.Params](-params/index.md)&gt; , [AutoCloseable](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/AutoCloseable.html)

# Buildless for Gradle: Service

This service implementation provides a dependency-injected way to access API clients for the Buildless service, from within a Gradle build.

## Constructors

| | |
|---|---|
| [BuildlessService](-buildless-service.md) | [JVM (Gradle)]<br>constructor() |

## Types

| Name | Summary |
|---|---|
| [Params](-params/index.md) | [JVM (Gradle)]<br>interface [Params](-params/index.md) : BuildServiceParameters |

## Functions

| Name | Summary |
|---|---|
| [close](close.md) | [JVM (Gradle)]<br>open override fun [close](close.md)()<br>Close active use of the service, including underlying resources. |
| [getParameters](get-parameters.md) | [JVM (Gradle)]<br>open override fun [getParameters](get-parameters.md)(): [BuildlessService.Params](-params/index.md)<br>Retrieve the active set of parameters for the current service instance. |
