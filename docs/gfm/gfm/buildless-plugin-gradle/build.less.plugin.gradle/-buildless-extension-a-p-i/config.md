//[buildless-plugin-gradle](../../../index.md)/[build.less.plugin.gradle](../index.md)/[BuildlessExtensionAPI](index.md)/[config](config.md)

# config

[JVM (Gradle)]\
abstract fun [config](config.md)(context: [Target](index.md), configure: [Settings](index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))

Main configuration entrypoint for the extension implementing this interface; not meant to be used by end-user code.

#### Parameters

JVM (Gradle)

| | |
|---|---|
| context | Context instance for the target of this configuration. |
| configure | Lambda to execute which configures the [context](config.md) within the receivership of [Settings](index.md). |
