//[buildless-plugin-gradle](../../../../index.md)/[build.less.plugin.gradle](../../index.md)/[ApiKey](../index.md)/[Companion](index.md)/[of](of.md)

# of

[JVM (Gradle)]\

@[JvmStatic](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-static/index.html)

fun [of](of.md)(value: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), type: [ApiKey.SubjectType](../-subject-type/index.md)? = null): [ApiKey](../index.md)

Create an API key from the provided [value](of.md), and optionally a known [type](of.md).

#### Return

API key object.

#### Parameters

JVM (Gradle)

| | |
|---|---|
| value | API key value to use. |
| type | Subject type for the key, if known. If unspecified, defaults to [SubjectType.USER](../-subject-type/-u-s-e-r/index.md). |

#### Throws

| | |
|---|---|
| [ApiKey.InvalidKey](../-invalid-key/index.md) | if the key fails to pass static checks. |
