//[buildless-plugin-gradle](../../../../index.md)/[build.less.plugin.gradle](../../index.md)/[ApiKey](../index.md)/[Companion](index.md)

# Companion

[JVM (Gradle)]\
object [Companion](index.md)

Provides factory methods for easily creating [ApiKey](../index.md) objects.

## Functions

| Name | Summary |
|---|---|
| [forOrg](for-org.md) | [JVM (Gradle)]<br>@[JvmStatic](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-static/index.html)<br>fun [forOrg](for-org.md)(value: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [ApiKey](../index.md)<br>Create a checked org tenant API key from the provided [value](for-org.md). |
| [forUser](for-user.md) | [JVM (Gradle)]<br>@[JvmStatic](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-static/index.html)<br>fun [forUser](for-user.md)(value: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [ApiKey](../index.md)<br>Create a checked user API key from the provided [value](for-user.md). |
| [of](of.md) | [JVM (Gradle)]<br>@[JvmStatic](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-static/index.html)<br>fun [of](of.md)(value: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), type: [ApiKey.SubjectType](../-subject-type/index.md)? = null): [ApiKey](../index.md)<br>Create an API key from the provided [value](of.md), and optionally a known [type](of.md). |
