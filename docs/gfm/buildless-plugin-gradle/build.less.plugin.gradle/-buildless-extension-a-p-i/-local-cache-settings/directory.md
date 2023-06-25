//[buildless-plugin-gradle](../../../../index.md)/[build.less.plugin.gradle](../../index.md)/[BuildlessExtensionAPI](../index.md)/[LocalCacheSettings](index.md)/[directory](directory.md)

# directory

[JVM (Gradle)]\
abstract val [directory](directory.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?

Directory where local cached objects are stored; if unset, Buildless sets a sensible default. If this value is set before the Buildless plugin is invoked, the user's value takes precedence and this value is rendered inert.
