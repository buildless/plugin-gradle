//[buildless-plugin-gradle](../../index.md)/[build.less.plugin.gradle.err](index.md)

# Package-level declarations

[JVM (Gradle)]\
Exceptions which are reliably thrown by the plugin, and which the developer may want to catch.

## Types

| Name | Summary |
|---|---|
| [BuildlessPluginException](-buildless-plugin-exception/index.md) | [JVM (Gradle)]<br>sealed class [BuildlessPluginException](-buildless-plugin-exception/index.md) : [RuntimeException](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/RuntimeException.html)<br>Describes an abstract base exception from which all Buildless plugin exceptions inherit; this type may be caught to handle all plugin exceptions. |
| [InvalidConfiguration](-invalid-configuration/index.md) | [JVM (Gradle)]<br>abstract class [InvalidConfiguration](-invalid-configuration/index.md)(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, cause: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)? = null) : [BuildlessPluginException](-buildless-plugin-exception/index.md)<br>Describes an abstract exception base for errors which originate from invalid configuration, or an illegal state expressed by the user when configuring the plugin. These errors are terminal in the sense that they must be remedied by a user in order to be fixed. |
