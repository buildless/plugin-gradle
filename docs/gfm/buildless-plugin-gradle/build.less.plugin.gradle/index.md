//[buildless-plugin-gradle](../../index.md)/[build.less.plugin.gradle](index.md)

# Package-level declarations

[JVM (Gradle)]\
Main package for the Buildless plugin for Gradle. Includes all user-facing interfaces and classes.

## Types

| Name | Summary |
|---|---|
| [ApiKey](-api-key/index.md) | [JVM (Gradle)]<br>@[JvmInline](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-inline/index.html)<br>value class [ApiKey](-api-key/index.md) : [Serializable](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/Serializable.html), [Comparable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-comparable/index.html)&lt;[ApiKey](-api-key/index.md)&gt; <br>Describes a checked Buildless API key, which also carries metadata with it about the key. The key itself can be accessed via the [key](-api-key/key.md) getter. |
| [BuildlessExtensionAPI](-buildless-extension-a-p-i/index.md) | [JVM (Gradle)]<br>interface [BuildlessExtensionAPI](-buildless-extension-a-p-i/index.md)&lt;[Target](-buildless-extension-a-p-i/index.md), [Settings](-buildless-extension-a-p-i/index.md) : [BuildlessExtensionAPI.ConfigAPI](-buildless-extension-a-p-i/-config-a-p-i/index.md)&lt;[Target](-buildless-extension-a-p-i/index.md)&gt;&gt;<br>This interface, along with nested types, defines the public API surface for the Buildless plugin for Gradle. Developers may customize their remote cache use via methods and fields defined herein. |
| [BuildlessService](-buildless-service/index.md) | [JVM (Gradle)]<br>class [BuildlessService](-buildless-service/index.md) : BuildService&lt;[BuildlessService.Params](-buildless-service/-params/index.md)&gt; , [AutoCloseable](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/AutoCloseable.html)<br>This service implementation provides a dependency-injected way to access API clients for the Buildless service, from within a Gradle build. |
| [CacheTransport](-cache-transport/index.md) | [JVM (Gradle)]<br>enum [CacheTransport](-cache-transport/index.md) : [Enum](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)&lt;[CacheTransport](-cache-transport/index.md)&gt; <br>Enumerates the types of transport engines which are available for use when interacting with Buildless. By default, the [STANDARD](-cache-transport/-s-t-a-n-d-a-r-d/index.md) transport is used, which leverages Gradle's built-in remote build caching over HTTP. |
