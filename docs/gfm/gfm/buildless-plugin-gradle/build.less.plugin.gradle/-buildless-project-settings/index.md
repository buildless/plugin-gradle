//[buildless-plugin-gradle](../../../index.md)/[build.less.plugin.gradle](../index.md)/[BuildlessProjectSettings](index.md)

# BuildlessProjectSettings

[JVM (Gradle)]\
interface [BuildlessProjectSettings](index.md) : [ConfigAPI](../-config-a-p-i/index.md)&lt;Project&gt; 

##  Buildless Extension API: Project-level Settings

Defines the interface made available when configuring project-level settings w.r.t. the Buildless plugin. Project level settings mostly deal with logging and ignoring tasks for the cache.

## Properties

| Name | Summary |
|---|---|
| [debug](../-config-a-p-i/debug.md) | [JVM (Gradle)]<br>abstract val [debug](../-config-a-p-i/debug.md): Property&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt;<br>Whether the Buildless plugin is operating in debug mode; debug mode enables verbose logging, and applies trace values to API calls, so that they may be diagnosed from the dashboard. |
