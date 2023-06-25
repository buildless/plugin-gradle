//[buildless-plugin-gradle](../../../../index.md)/[build.less.plugin.gradle](../../index.md)/[BuildlessExtensionAPI](../index.md)/[RemoteCacheSettings](index.md)/[push](push.md)

# push

[JVM (Gradle)]\
abstract val [push](push.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

Whether build outputs will be uploaded to the cache as needed; enabled by default.

It is recommended to keep cache push enabled, as the Buildless plugin will intelligently disable cache push in circumstances where upload speed is suboptimal.
