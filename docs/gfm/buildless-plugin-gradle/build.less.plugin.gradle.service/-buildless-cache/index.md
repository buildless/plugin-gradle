//[buildless-plugin-gradle](../../../index.md)/[build.less.plugin.gradle.service](../index.md)/[BuildlessCache](index.md)

# BuildlessCache

[JVM (Gradle)]\
open class [BuildlessCache](index.md) : HttpBuildCache

# Buildless Cache

TBD.

## Constructors

| | |
|---|---|
| [BuildlessCache](-buildless-cache.md) | [JVM (Gradle)]<br>constructor() |

## Properties

| Name | Summary |
|---|---|
| [allowInsecureProtocol](index.md#-249351144%2FProperties%2F73423754) | [JVM (Gradle)]<br>var [allowInsecureProtocol](index.md#-249351144%2FProperties%2F73423754): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [allowUntrustedServer](index.md#-772418963%2FProperties%2F73423754) | [JVM (Gradle)]<br>var [allowUntrustedServer](index.md#-772418963%2FProperties%2F73423754): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [credentials](index.md#662685945%2FProperties%2F73423754) | [JVM (Gradle)]<br>val [credentials](index.md#662685945%2FProperties%2F73423754): HttpBuildCacheCredentials |
| [enabled](index.md#34377424%2FProperties%2F73423754) | [JVM (Gradle)]<br>var [enabled](index.md#34377424%2FProperties%2F73423754): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [push](index.md#-484100305%2FProperties%2F73423754) | [JVM (Gradle)]<br>var [push](index.md#-484100305%2FProperties%2F73423754): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [useExpectContinue](index.md#-1140853042%2FProperties%2F73423754) | [JVM (Gradle)]<br>var [useExpectContinue](index.md#-1140853042%2FProperties%2F73423754): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

## Functions

| Name | Summary |
|---|---|
| [credentials](index.md#886717550%2FFunctions%2F73423754) | [JVM (Gradle)]<br>open fun [credentials](index.md#886717550%2FFunctions%2F73423754)(configuration: Action&lt;in HttpBuildCacheCredentials&gt;) |
| [getUrl](index.md#-544593940%2FFunctions%2F73423754) | [JVM (Gradle)]<br>@Nullable<br>open fun [getUrl](index.md#-544593940%2FFunctions%2F73423754)(): [URI](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/net/URI.html)? |
| [setUrl](index.md#52731418%2FFunctions%2F73423754) | [JVM (Gradle)]<br>open fun [setUrl](index.md#52731418%2FFunctions%2F73423754)(@Nullableurl: [URI](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/net/URI.html)?)<br>open fun [setUrl](index.md#-1479548282%2FFunctions%2F73423754)(url: [URL](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/net/URL.html))<br>open fun [setUrl](index.md#-317449972%2FFunctions%2F73423754)(url: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |
