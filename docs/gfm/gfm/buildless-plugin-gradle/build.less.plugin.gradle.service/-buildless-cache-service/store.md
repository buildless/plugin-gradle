//[buildless-plugin-gradle](../../../index.md)/[build.less.plugin.gradle.service](../index.md)/[BuildlessCacheService](index.md)/[store](store.md)

# store

[JVM (Gradle)]\
open override fun [store](store.md)(key: BuildCacheKey, writer: BuildCacheEntryWriter)

##  Cache: Store

Store an entry within the Buildless-powered cache; if Buildless-specific protocols are not available, or the Buildless Agent is not running, this will fall back to the default Gradle cache behavior.

#### Parameters

JVM (Gradle)

| | |
|---|---|
| key | Cache key to store a cache entry for. |
| writer | Cache entry writer. |
