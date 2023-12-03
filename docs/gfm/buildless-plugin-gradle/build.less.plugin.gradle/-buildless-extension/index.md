//[buildless-plugin-gradle](../../../index.md)/[build.less.plugin.gradle](../index.md)/[BuildlessExtension](index.md)

# BuildlessExtension

abstract class [BuildlessExtension](index.md)@Injectconstructor(factory: ObjectFactory) : [BuildlessExtensionAPI](../-buildless-extension-a-p-i/index.md)&lt;Settings, [BuildlessSettings](../-buildless-settings/index.md)&gt; 

# Buildless for Gradle: Extension

The extension for the Buildless plugin is used from the `settings.gradle.kts` script to configure the plugin's behavior and features.

#### Parameters

JVM (Gradle)

| | |
|---|---|
| factory | Object factory for Gradle-provided injection. |

#### See also

| | |
|---|---|
| [BuildlessExtensionAPI](../-buildless-extension-a-p-i/index.md) | For the public API suite supported by this plugin for configuration of build caching. |
| [BuildlessService](../-buildless-service/index.md) | For a service which may be consumed from other plugins to interact with Buildless. |

## Constructors

| | |
|---|---|
| [BuildlessExtension](-buildless-extension.md) | [JVM (Gradle)]<br>@Inject<br>constructor(factory: ObjectFactory) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [JVM (Gradle)]<br>object [Companion](-companion/index.md) |
| [LocalAgentSetup](-local-agent-setup/index.md) | [JVM (Gradle)]<br>inner class [LocalAgentSetup](-local-agent-setup/index.md) : [MutableAgentCacheSettings](../-mutable-agent-cache-settings/index.md)<br>Mutable settings class for local agent cache settings. |
| [LocalCacheSetup](-local-cache-setup/index.md) | [JVM (Gradle)]<br>inner class [LocalCacheSetup](-local-cache-setup/index.md) : [MutableLocalCacheSettings](../-mutable-local-cache-settings/index.md)<br>Mutable settings class for local build cache settings. |
| [RemoteCacheSetup](-remote-cache-setup/index.md) | [JVM (Gradle)]<br>inner class [RemoteCacheSetup](-remote-cache-setup/index.md) : [MutableRemoteCacheSettings](../-mutable-remote-cache-settings/index.md)<br>Mutable settings class for remote build cache settings. |

## Properties

| Name | Summary |
|---|---|
| [agent](agent.md) | [JVM (Gradle)]<br>open override val [agent](agent.md): [MutableAgentCacheSettings](../-mutable-agent-cache-settings/index.md)<br>Specifies agent cache settings for this Gradle project. Agent caching keeps built outputs within a local cache service, like Buildless Agent. Agent caching is enabled by default if an API key is present in the local environment, using the main Gradle cache endpoint. |
| [apiKey](api-key.md) | [JVM (Gradle)]<br>open override val [apiKey](api-key.md): Property&lt;[ApiKey](../-api-key/index.md)?&gt;<br>API key to install. |
| [cloud](cloud.md) | [JVM (Gradle)]<br>open override val [cloud](cloud.md): [MutableRemoteCacheSettings](../-mutable-remote-cache-settings/index.md)<br>Specifies remote cache settings for this Gradle project. Remote caching keeps built outputs within a remote cache service, like Buildless. Remote caching is enabled by default if an API key is present in the local environment, using the main Gradle cache endpoint. |
| [debug](debug.md) | [JVM (Gradle)]<br>open override val [debug](debug.md): Property&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt;<br>Whether to enable debug mode. |
| [local](local.md) | [JVM (Gradle)]<br>open override val [local](local.md): [MutableLocalCacheSettings](../-mutable-local-cache-settings/index.md)<br>Specifies local cache settings for this Gradle project. Local caching keeps various cached objects in a directory on-disk, and objects never leave the developer's machine. Local caching is enabled by default, and generally it is recommended to keep it active. |
| [localCache](../-buildless-settings/local-cache.md) | [JVM (Gradle)]<br>open val [localCache](../-buildless-settings/local-cache.md): [MutableLocalCacheSettings](../-mutable-local-cache-settings/index.md)<br>Alias for [local](../-buildless-settings/local.md) cache settings. |
| [remoteCache](../-buildless-settings/remote-cache.md) | [JVM (Gradle)]<br>open val [remoteCache](../-buildless-settings/remote-cache.md): [MutableRemoteCacheSettings](../-mutable-remote-cache-settings/index.md)<br>Alias for [cloud](../-buildless-settings/cloud.md) remote cache settings. |
| [reportErrors](report-errors.md) | [JVM (Gradle)]<br>open override val [reportErrors](report-errors.md): Property&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt;<br>Whether to enable error reporting. |
| [telemetry](telemetry.md) | [JVM (Gradle)]<br>open override val [telemetry](telemetry.md): Property&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt;<br>Whether to enable telemetry integration. |
| [transport](transport.md) | [JVM (Gradle)]<br>open override val [transport](transport.md): Property&lt;[CacheTransport](../-cache-transport/index.md)&gt;<br>Cache transport to use. |

## Functions

| Name | Summary |
|---|---|
| [agent](agent.md) | [JVM (Gradle)]<br>open override fun [agent](agent.md)(config: [MutableAgentCacheSettings](../-mutable-agent-cache-settings/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))<br>Configure agent caching settings with the provided [config](agent.md) function. |
| [apiKey](../-buildless-settings/api-key.md) | [JVM (Gradle)]<br>open fun [apiKey](../-buildless-settings/api-key.md)(key: [ApiKey](../-api-key/index.md))<br>Set the API [key](../-buildless-settings/api-key.md) to use when interacting with the Buildless service.<br>[JVM (Gradle)]<br>open fun [apiKey](../-buildless-settings/api-key.md)(value: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>Set the API key from the provided string [value](../-buildless-settings/api-key.md). |
| [config](config.md) | [JVM (Gradle)]<br>open override fun [config](config.md)(context: Settings, configure: [BuildlessSettings](../-buildless-settings/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))<br>Main configuration entrypoint for the extension implementing this interface; not meant to be used by end-user code. |
| [localCache](local-cache.md) | [JVM (Gradle)]<br>open override fun [localCache](local-cache.md)(config: [MutableLocalCacheSettings](../-mutable-local-cache-settings/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))<br>Configure local caching settings with the provided [config](local-cache.md) function. |
| [remoteCache](remote-cache.md) | [JVM (Gradle)]<br>open override fun [remoteCache](remote-cache.md)(config: [MutableRemoteCacheSettings](../-mutable-remote-cache-settings/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))<br>Configure remote caching settings with the provided [config](remote-cache.md) function. |
