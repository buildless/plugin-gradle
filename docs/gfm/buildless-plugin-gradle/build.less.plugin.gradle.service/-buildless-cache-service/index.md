//[buildless-plugin-gradle](../../../index.md)/[build.less.plugin.gradle.service](../index.md)/[BuildlessCacheService](index.md)

# BuildlessCacheService

[JVM (Gradle)]\
class [BuildlessCacheService](index.md)(endpoint: [URI](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/net/URI.html), httpClientHelper: HttpClientHelper, http2ClientHelper: Http2ClientHelper, requestCustomizer: HttpBuildCacheRequestCustomizer, useExpectContinue: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) : HttpBuildCacheService, BuildCacheService

Build cache service implementing optimized transport with Buildless; see [BuildlessCache](../-buildless-cache/index.md).

## Constructors

| | |
|---|---|
| [BuildlessCacheService](-buildless-cache-service.md) | [JVM (Gradle)]<br>constructor(endpoint: [URI](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/net/URI.html), httpClientHelper: HttpClientHelper, http2ClientHelper: Http2ClientHelper, requestCustomizer: HttpBuildCacheRequestCustomizer, useExpectContinue: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) |

## Functions

| Name | Summary |
|---|---|
| [close](index.md#908827488%2FFunctions%2F73423754) | [JVM (Gradle)]<br>abstract override fun [close](index.md#908827488%2FFunctions%2F73423754)() |
| [load](load.md) | [JVM (Gradle)]<br>open override fun [load](load.md)(key: BuildCacheKey, reader: BuildCacheEntryReader): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [store](store.md) | [JVM (Gradle)]<br>open override fun [store](store.md)(key: BuildCacheKey, writer: BuildCacheEntryWriter) |
