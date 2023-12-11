//[buildless-plugin-gradle](../../../index.md)/[build.less.plugin.gradle.service](../index.md)/[BuildlessCacheService](index.md)/[BuildlessCacheService](-buildless-cache-service.md)

# BuildlessCacheService

[JVM (Gradle)]\
constructor(endpoint: [URI](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/net/URI.html), httpClientHelper: HttpClientHelper, http2ClientHelper: Http2ClientHelper, requestCustomizer: HttpBuildCacheRequestCustomizer, useExpectContinue: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))

#### Parameters

JVM (Gradle)

| | |
|---|---|
| endpoint | Configured cache endpoint. |
| httpClientHelper | HTTP client helper. |
| http2ClientHelper | HTTP/2 client helper. |
| requestCustomizer | HTTP request customizer. |
| useExpectContinue | Whether to use the HTTP/1.1 `Expect: 100-continue` header. |
