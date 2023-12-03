//[buildless-plugin-gradle](../../../index.md)/[build.less.plugin.gradle](../index.md)/[ConfigAPI](index.md)

# ConfigAPI

interface [ConfigAPI](index.md)&lt;[Target](index.md)&gt;

##  Buildless Extension API: Configuration

Describes the base interface from which all plugin settings interfaces inherit. Since the Buildless plugin can be configured in both the context of a Gradle Settings (initialization) file, and also a Project, the API needs to be extended into both circumstances; this interface bears the common fields between both.

#### See also

| | |
|---|---|
| [BuildlessSettings](../-buildless-settings/index.md) | for settings-time configuration of the plugin, which is shared across all projects. |
| [BuildlessProjectSettings](../-buildless-project-settings/index.md) | for settings which apply at the level of a single Gradle project. |

#### Inheritors

| |
|---|
| [BuildlessSettings](../-buildless-settings/index.md) |
| [BuildlessProjectSettings](../-buildless-project-settings/index.md) |

## Properties

| Name | Summary |
|---|---|
| [debug](debug.md) | [JVM (Gradle)]<br>abstract val [debug](debug.md): Property&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt;<br>Whether the Buildless plugin is operating in debug mode; debug mode enables verbose logging, and applies trace values to API calls, so that they may be diagnosed from the dashboard. |
