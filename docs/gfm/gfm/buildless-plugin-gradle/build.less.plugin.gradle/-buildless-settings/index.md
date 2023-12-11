//[buildless-plugin-gradle](../../../index.md)/[build.less.plugin.gradle](../index.md)/[BuildlessSettings](index.md)

# BuildlessSettings

interface [BuildlessSettings](index.md) : [ConfigAPI](../-config-a-p-i/index.md)&lt;Settings&gt; 

##  Buildless Extension API: Settings

Describes top-level settings which are available within `settings.gradle.kts` for a Buildless-enabled Gradle project. These settings apply within the scope of file in question (usually, all projects within a multi-project Gradle build, or a single Gradle project).

These settings implement the [ConfigAPI](../-config-a-p-i/index.md) interface for a target of Settings, but with mutable fields so that they may be set from a builder context.

#### See also

| | |
|---|---|
| [ConfigAPI](../-config-a-p-i/index.md) | for read-only fields shared by all settings payloads provided by the Buildless plugin for Gradle. |

#### Inheritors

| |
|---|
| [BuildlessExtensionAPI](../-buildless-extension-a-p-i/index.md) |

## Properties

| Name | Summary |
|---|---|
| [agent](agent.md) | [JVM (Gradle)]<br>abstract val [agent](agent.md): [MutableAgentCacheSettings](../-mutable-agent-cache-settings/index.md)<br>Specifies agent cache settings for this Gradle project. Agent caching keeps built outputs within a local cache service, like Buildless Agent. Agent caching is enabled by default if an API key is present in the local environment, using the main Gradle cache endpoint. |
| [apiKey](api-key.md) | [JVM (Gradle)]<br>abstract val [apiKey](api-key.md): Property&lt;[ApiKey](../-api-key/index.md)?&gt;<br>API key that should be used to identify this project to the Buildless cache service. |
| [cloud](cloud.md) | [JVM (Gradle)]<br>abstract val [cloud](cloud.md): [MutableRemoteCacheSettings](../-mutable-remote-cache-settings/index.md)<br>Specifies remote cache settings for this Gradle project. Remote caching keeps built outputs within a remote cache service, like Buildless. Remote caching is enabled by default if an API key is present in the local environment, using the main Gradle cache endpoint. |
| [debug](debug.md) | [JVM (Gradle)]<br>abstract override val [debug](debug.md): Property&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt;<br>Specifies whether &quot;debug mode&quot; is active within the Buildless plugin. When debug mode is active, verbose logging is emitted to the terminal, and various trace values are sent to the Buildless API, which can be used to diagnose issues with the cache from the web dashboard. |
| [local](local.md) | [JVM (Gradle)]<br>abstract val [local](local.md): [MutableLocalCacheSettings](../-mutable-local-cache-settings/index.md)<br>Specifies local cache settings for this Gradle project. Local caching keeps various cached objects in a directory on-disk, and objects never leave the developer's machine. Local caching is enabled by default, and generally it is recommended to keep it active. |
| [localCache](local-cache.md) | [JVM (Gradle)]<br>open val [localCache](local-cache.md): [MutableLocalCacheSettings](../-mutable-local-cache-settings/index.md)<br>Alias for [local](local.md) cache settings. |
| [remoteCache](remote-cache.md) | [JVM (Gradle)]<br>open val [remoteCache](remote-cache.md): [MutableRemoteCacheSettings](../-mutable-remote-cache-settings/index.md)<br>Alias for [cloud](cloud.md) remote cache settings. |
| [reportErrors](report-errors.md) | [JVM (Gradle)]<br>abstract val [reportErrors](report-errors.md): Property&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt;<br>Specifies whether errors should be reported to the Buildless API. When error reporting is active, errors which are encountered when interacting with the cache are reported to a central service. These logs include only the authorization material provided by the developer, along with light context from the Gradle build. |
| [telemetry](telemetry.md) | [JVM (Gradle)]<br>abstract val [telemetry](telemetry.md): Property&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt;<br>Whether to enable telemetry for this build. |
| [transport](transport.md) | [JVM (Gradle)]<br>abstract val [transport](transport.md): Property&lt;[CacheTransport](../-cache-transport/index.md)&gt;<br>Transport mechanism to use when interacting with the Buildless cache. |

## Functions

| Name | Summary |
|---|---|
| [agent](agent.md) | [JVM (Gradle)]<br>abstract fun [agent](agent.md)(config: [MutableAgentCacheSettings](../-mutable-agent-cache-settings/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))<br>Configure agent caching settings with the provided [config](agent.md) function. |
| [apiKey](api-key.md) | [JVM (Gradle)]<br>open fun [apiKey](api-key.md)(key: [ApiKey](../-api-key/index.md))<br>Set the API [key](api-key.md) to use when interacting with the Buildless service.<br>[JVM (Gradle)]<br>open fun [apiKey](api-key.md)(value: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>Set the API key from the provided string [value](api-key.md). |
| [localCache](local-cache.md) | [JVM (Gradle)]<br>abstract fun [localCache](local-cache.md)(config: [MutableLocalCacheSettings](../-mutable-local-cache-settings/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))<br>Configure local caching settings with the provided [config](local-cache.md) function. |
| [remoteCache](remote-cache.md) | [JVM (Gradle)]<br>abstract fun [remoteCache](remote-cache.md)(config: [MutableRemoteCacheSettings](../-mutable-remote-cache-settings/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))<br>Configure remote caching settings with the provided [config](remote-cache.md) function. |
