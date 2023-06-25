//[buildless-plugin-gradle](../../../../index.md)/[build.less.plugin.gradle](../../index.md)/[BuildlessExtensionAPI](../index.md)/[ConfigAPI](index.md)/[remoteCache](remote-cache.md)

# remoteCache

[JVM (Gradle)]\
abstract val [remoteCache](remote-cache.md): [BuildlessExtensionAPI.RemoteCacheSettings](../-remote-cache-settings/index.md)

Remote cache settings for this Gradle project. Remote caching keeps built outputs within a remote service, like, say, Buildless (although any remote caching can be configured via these settings).

Remote caching is on by default if an API key is present in the local environment; it is recommended to leave remote caching active at all times. Remote caching is designed to fail-fast and not to ever break the build.
