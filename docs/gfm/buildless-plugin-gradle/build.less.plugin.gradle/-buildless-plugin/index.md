//[buildless-plugin-gradle](../../../index.md)/[build.less.plugin.gradle](../index.md)/[BuildlessPlugin](index.md)

# BuildlessPlugin

[JVM (Gradle)]\
class [BuildlessPlugin](index.md)@Injectconstructor(docRegistry: DocumentationRegistry, sslContextFactory: SslContextFactory, httpClientHelperFactory: HttpClientHelper.Factory, requestCustomizer: HttpBuildCacheRequestCustomizer) : Plugin&lt;Settings&gt; , BuildCacheServiceFactory&lt;[BuildlessCache](../../build.less.plugin.gradle.service/-buildless-cache/index.md)&gt; 

# Buildless for Gradle

Entrypoint for the official Buildless plug-in for Gradle, which adds (1) optimized transport capabilities for caching and artifact downloads, (2) a lightning fast remote build cache, and (3) helpful configuration for the Kotlin DSL.

## Constructors

| | |
|---|---|
| [BuildlessPlugin](-buildless-plugin.md) | [JVM (Gradle)]<br>@Inject<br>constructor(docRegistry: DocumentationRegistry, sslContextFactory: SslContextFactory, httpClientHelperFactory: HttpClientHelper.Factory, requestCustomizer: HttpBuildCacheRequestCustomizer) |

## Functions

| Name | Summary |
|---|---|
| [apply](apply.md) | [JVM (Gradle)]<br>open override fun [apply](apply.md)(target: Settings) |
| [createBuildCacheService](create-build-cache-service.md) | [JVM (Gradle)]<br>open override fun [createBuildCacheService](create-build-cache-service.md)(configuration: [BuildlessCache](../../build.less.plugin.gradle.service/-buildless-cache/index.md), describer: BuildCacheServiceFactory.Describer): [BuildlessCacheService](../../build.less.plugin.gradle.service/-buildless-cache-service/index.md) |
