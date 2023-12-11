//[buildless-plugin-gradle](../../../index.md)/[build.less.plugin.gradle](../index.md)/[BuildlessSettings](index.md)/[transport](transport.md)

# transport

[JVM (Gradle)]\
abstract val [transport](transport.md): Property&lt;[CacheTransport](../-cache-transport/index.md)&gt;

###  Transport

Transport mechanism to use when interacting with the Buildless cache.

The transport mechanism implements protocol support, transport security (TLS), and authorization. The default transport is [CacheTransport.BUILTIN](../-cache-transport/-b-u-i-l-t-i-n/index.md) which uses Gradle's built-in HTTP build cache engine.
