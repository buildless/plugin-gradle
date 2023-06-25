//[buildless-plugin-gradle](../../../../index.md)/[build.less.plugin.gradle](../../index.md)/[BuildlessExtensionAPI](../index.md)/[ConfigAPI](index.md)/[localCache](local-cache.md)

# localCache

[JVM (Gradle)]\
abstract val [localCache](local-cache.md): [BuildlessExtensionAPI.LocalCacheSettings](../-local-cache-settings/index.md)

Local cache settings for this Gradle project. Local caching keeps various cached objects in a directory on-disk, and objects never leave the developer's machine.

It is generally recommended to leave local caching active. Buildless sets well-informed defaults, like moving the default directory for local caching to a location within the project directory.
