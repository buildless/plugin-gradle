//[buildless-plugin-gradle](../../../../index.md)/[build.less.plugin.gradle](../../index.md)/[BuildlessExtensionAPI](../index.md)/[BuildlessSettings](index.md)/[transport](transport.md)

# transport

[JVM (Gradle)]\
abstract var [transport](transport.md): [CacheTransport](../../-cache-transport/index.md)

###  Transport

Transport mechanism to use when interacting with the Buildless cache.

The transport mechanism implements protocol support, transport security (TLS), and authorization. The default transport is [CacheTransport.STANDARD](../../-cache-transport/-s-t-a-n-d-a-r-d/index.md) which uses Gradle's built-in HTTP build cache engine.
