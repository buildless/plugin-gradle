//[buildless-plugin-gradle](../../index.md)/[build.less.plugin.gradle.service](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [BuildlessCache](-buildless-cache/index.md) | [JVM (Gradle)]<br>open class [BuildlessCache](-buildless-cache/index.md) : HttpBuildCache<br>TBD. |
| [BuildlessCacheService](-buildless-cache-service/index.md) | [JVM (Gradle)]<br>class [BuildlessCacheService](-buildless-cache-service/index.md)(endpoint: [URI](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/net/URI.html), httpClientHelper: HttpClientHelper, http2ClientHelper: Http2ClientHelper, requestCustomizer: HttpBuildCacheRequestCustomizer, useExpectContinue: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) : HttpBuildCacheService, BuildCacheService<br>Build cache service implementing optimized transport with Buildless; see [BuildlessCache](-buildless-cache/index.md). |
| [BuildlessConfiguration](-buildless-configuration/index.md) | [JVM (Gradle)]<br>interface [BuildlessConfiguration](-buildless-configuration/index.md) |
| [BuildlessFactory](-buildless-factory/index.md) | [JVM (Gradle)]<br>interface [BuildlessFactory](-buildless-factory/index.md)<br>Specifies extended method support for the Buildless Service factory. |
| [BuildlessServiceApi](-buildless-service-api/index.md) | [JVM (Gradle)]<br>interface [BuildlessServiceApi](-buildless-service-api/index.md) : [BuildlessFactory](-buildless-factory/index.md)<br>Specifies extended method support for the Buildless service which resides within a Gradle build; these methods extend the concept of a BuildCacheService. |
