//[buildless-plugin-gradle](../../../index.md)/[build.less.plugin.gradle.service](../index.md)/[BuildlessCacheService](index.md)/[load](load.md)

# load

[JVM (Gradle)]\
open override fun [load](load.md)(key: BuildCacheKey, reader: BuildCacheEntryReader): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

##  Cache: Load

Load a cache entry from the Buildless-powered cache; if Buildless-specific protocols are not available or the Buildless Agent is not running, this will fall back to the default Gradle cache behavior.

#### Return

`true` if the cache entry was loaded, `false` otherwise.

#### Parameters

JVM (Gradle)

| | |
|---|---|
| key | Cache key to retrieve a cache entry for. |
| reader | Cache entry reader. |
