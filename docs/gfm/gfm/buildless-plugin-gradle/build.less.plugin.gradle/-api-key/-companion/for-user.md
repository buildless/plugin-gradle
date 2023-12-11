//[buildless-plugin-gradle](../../../../index.md)/[build.less.plugin.gradle](../../index.md)/[ApiKey](../index.md)/[Companion](index.md)/[forUser](for-user.md)

# forUser

[JVM (Gradle)]\

@[JvmStatic](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-static/index.html)

fun [forUser](for-user.md)(value: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [ApiKey](../index.md)

Create a checked user API key from the provided [value](for-user.md).

#### Return

API key object.

#### Parameters

JVM (Gradle)

| | |
|---|---|
| value | API key value to use. |

#### Throws

| | |
|---|---|
| [ApiKey.InvalidKey](../-invalid-key/index.md) | if the key fails to pass static checks. |
