//[buildless-plugin-gradle](../../../../index.md)/[build.less.plugin.gradle](../../index.md)/[BuildlessExtensionAPI](../index.md)/[BuildlessSettings](index.md)

# BuildlessSettings

interface [BuildlessSettings](index.md) : [BuildlessExtensionAPI.ConfigAPI](../-config-a-p-i/index.md)&lt;Settings&gt; 

##  Buildless Extension API: Settings

Describes top-level settings which are available within `settings.gradle.kts` for a Buildless-enabled Gradle project. These settings apply within the scope of file in question (usually, all projects within a multi-project Gradle build, or a single Gradle project).

These settings implement the [ConfigAPI](../-config-a-p-i/index.md) interface for a target of [Settings](../index.md), but with mutable fields so that they may be set from a builder context.

#### See also

| | |
|---|---|
| [BuildlessExtensionAPI.ConfigAPI](../-config-a-p-i/index.md) | for read-only fields shared by all settings payloads provided by the Buildless plugin for Gradle. |

## Properties

| Name | Summary |
|---|---|
| [apiKey](api-key.md) | [JVM (Gradle)]<br>abstract var [apiKey](api-key.md): [ApiKey](../../-api-key/index.md)?<br>API key that should be used to identify this project to the Buildless cache service. |
| [debug](debug.md) | [JVM (Gradle)]<br>abstract override var [debug](debug.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Specifies whether &quot;debug mode&quot; is active within the Buildless plugin. When debug mode is active, verbose logging is emitted to the terminal, and various trace values are sent to the Buildless API, which can be used to diagnose issues with the cache from the web dashboard. |
| [localCache](local-cache.md) | [JVM (Gradle)]<br>abstract override val [localCache](local-cache.md): [BuildlessExtensionAPI.MutableLocalCacheSettings](../-mutable-local-cache-settings/index.md)<br>Specifies local cache settings for this Gradle project. Local caching keeps various cached objects in a directory on-disk, and objects never leave the developer's machine. Local caching is enabled by default, and generally it is recommended to keep it active. |
| [remoteCache](remote-cache.md) | [JVM (Gradle)]<br>abstract override val [remoteCache](remote-cache.md): [BuildlessExtensionAPI.MutableRemoteCacheSettings](../-mutable-remote-cache-settings/index.md)<br>Specifies remote cache settings for this Gradle project. Remote caching keeps built outputs within a remote cache service, like Buildless. Remote caching is enabled by default if an API key is present in the local environment, using the main Gradle cache endpoint. |
| [reportErrors](report-errors.md) | [JVM (Gradle)]<br>abstract var [reportErrors](report-errors.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Specifies whether errors should be reported to the Buildless API. When error reporting is active, errors which are encountered when interacting with the cache are reported to a central service. These logs include only the authorization material provided by the developer, along with light context from the Gradle build. |
| [telemetry](telemetry.md) | [JVM (Gradle)]<br>abstract var [telemetry](telemetry.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Whether to enable telemetry for this build. |
| [transport](transport.md) | [JVM (Gradle)]<br>abstract var [transport](transport.md): [CacheTransport](../../-cache-transport/index.md)<br>Transport mechanism to use when interacting with the Buildless cache. |

## Functions

| Name | Summary |
|---|---|
| [apiKey](api-key.md) | [JVM (Gradle)]<br>open fun [apiKey](api-key.md)(key: [ApiKey](../../-api-key/index.md))<br>Set the API [key](api-key.md) to use when interacting with the Buildless service.<br>[JVM (Gradle)]<br>open fun [apiKey](api-key.md)(value: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>Set the API key from the provided string [value](api-key.md). |
| [localCache](local-cache.md) | [JVM (Gradle)]<br>abstract fun [localCache](local-cache.md)(config: [BuildlessExtensionAPI.MutableLocalCacheSettings](../-mutable-local-cache-settings/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))<br>Configure local caching settings with the provided [config](local-cache.md) function. |
| [remoteCache](remote-cache.md) | [JVM (Gradle)]<br>abstract fun [remoteCache](remote-cache.md)(config: [BuildlessExtensionAPI.MutableRemoteCacheSettings](../-mutable-remote-cache-settings/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))<br>Configure remote caching settings with the provided [config](remote-cache.md) function. |
