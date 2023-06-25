//[buildless-plugin-gradle](../../index.md)/[build.less.plugin.settings](index.md)/[buildless](buildless.md)

# buildless

[JVM (Gradle)]\
fun Settings.[buildless](buildless.md)(configure: [BuildlessExtensionAPI.BuildlessSettings](../build.less.plugin.gradle/-buildless-extension-a-p-i/-buildless-settings/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))

# Buildless: Plugin Configuration

This function is the main entrypoint for configuring the Buildless Plugin for Gradle. All user-facing helpers which are usable at Settings evaluation time are exposed in this package/file.

The [buildless](buildless.md) function may be run within the Settings context once the plugin has loaded. This can be done via the following snippet:

```kotlin
import build.less.plugin.settings.*;

plugins {
  id("build.less") version "..."
}
```

##  Configuration Context

The [configure](buildless.md) lambda is executed within the [BuildlessSettings](../build.less.plugin.gradle/-buildless-extension-a-p-i/-buildless-settings/index.md) context, which allows for building configuration which is then shared within the context of a (potentially multi-project) Gradle build. Settings can be configured at the top-level, via this function, or within a subproject context using the project-level plugin.

The [BuildlessSettings](../build.less.plugin.gradle/-buildless-extension-a-p-i/-buildless-settings/index.md) interface should be consulted for an exhaustive list of configuration options.

#### See also

| | |
|---|---|
| [BuildlessExtensionAPI.BuildlessSettings](../build.less.plugin.gradle/-buildless-extension-a-p-i/-buildless-settings/index.md) | for an exhaustive list of configuration options available at settings evaluation time. |
