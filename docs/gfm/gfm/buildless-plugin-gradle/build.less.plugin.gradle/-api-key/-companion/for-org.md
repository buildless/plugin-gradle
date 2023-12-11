//[buildless-plugin-gradle](../../../../index.md)/[build.less.plugin.gradle](../../index.md)/[ApiKey](../index.md)/[Companion](index.md)/[forOrg](for-org.md)

# forOrg

[JVM (Gradle)]\

@[JvmStatic](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-static/index.html)

fun [forOrg](for-org.md)(value: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [ApiKey](../index.md)

Create a checked org tenant API key from the provided [value](for-org.md).

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
