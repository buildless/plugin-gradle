//[buildless-plugin-gradle](../../../index.md)/[build.less.plugin.gradle.service](../index.md)/[BuildlessCacheService](index.md)

# BuildlessCacheService

class [BuildlessCacheService](index.md)(endpoint: [URI](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/net/URI.html), httpClientHelper: HttpClientHelper, http2ClientHelper: Http2ClientHelper, requestCustomizer: HttpBuildCacheRequestCustomizer, useExpectContinue: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) : HttpBuildCacheService, BuildCacheService

# Buildless: Cache Service

Build cache service implementing optimized transport with Buildless; see [BuildlessCache](../-buildless-cache/index.md) for more information. This service is shared throughout the Gradle build process, and is responsible for implementing optimized cache protocols with the Buildless Agent.

 

If the Buildless agent is not running, or for any other reason Buildless protocols are not available, this service falls back to default Gradle cache behavior.

#### Parameters

JVM (Gradle)

| | |
|---|---|
| endpoint | Configured cache endpoint. |
| httpClientHelper | HTTP client helper. |
| http2ClientHelper | HTTP/2 client helper. |
| requestCustomizer | HTTP request customizer. |
| useExpectContinue | Whether to use the HTTP/1.1 `Expect: 100-continue` header. |

#### See also

| | |
|---|---|
| [BuildlessCache](../-buildless-cache/index.md) | for more information. |

## Constructors

| | |
|---|---|
| [BuildlessCacheService](-buildless-cache-service.md) | [JVM (Gradle)]<br>constructor(endpoint: [URI](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/net/URI.html), httpClientHelper: HttpClientHelper, http2ClientHelper: Http2ClientHelper, requestCustomizer: HttpBuildCacheRequestCustomizer, useExpectContinue: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) |

## Functions

| Name | Summary |
|---|---|
| [close](index.md#908827488%2FFunctions%2F73423754) | [JVM (Gradle)]<br>abstract override fun [close](index.md#908827488%2FFunctions%2F73423754)() |
| [load](load.md) | [JVM (Gradle)]<br>open override fun [load](load.md)(key: BuildCacheKey, reader: BuildCacheEntryReader): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Load a cache entry from the Buildless-powered cache; if Buildless-specific protocols are not available or the Buildless Agent is not running, this will fall back to the default Gradle cache behavior. |
| [store](store.md) | [JVM (Gradle)]<br>open override fun [store](store.md)(key: BuildCacheKey, writer: BuildCacheEntryWriter)<br>Store an entry within the Buildless-powered cache; if Buildless-specific protocols are not available, or the Buildless Agent is not running, this will fall back to the default Gradle cache behavior. |
