//[buildless-plugin-gradle](../../../index.md)/[build.less.plugin.gradle](../index.md)/[LocalCacheSettings](index.md)

# LocalCacheSettings

interface [LocalCacheSettings](index.md) : [BuildlessExtensionAPI.CacheSettings](../-buildless-extension-a-p-i/-cache-settings/index.md)

###  Local Cache Settings

Specifies settings which configure Gradle's local build cache; these may be provided in lieu of settings specified directly with Gradle. Local build cache settings specified directly to Gradle take precedence over these. By default, the local build cache is enabled.

#### Inheritors

| |
|---|
| [MutableLocalCacheSettings](../-mutable-local-cache-settings/index.md) |

## Properties

| Name | Summary |
|---|---|
| [directory](directory.md) | [JVM (Gradle)]<br>abstract val [directory](directory.md): DirectoryProperty<br>Directory where local cached objects are stored; if unset, Buildless sets a sensible default. If this value is set before the Buildless plugin is invoked, the user's value takes precedence and this value is rendered inert. |
| [enabled](../-buildless-extension-a-p-i/-cache-settings/enabled.md) | [JVM (Gradle)]<br>abstract val [enabled](../-buildless-extension-a-p-i/-cache-settings/enabled.md): Property&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt;<br>Indicates whether a given cache sub-system should be enabled or disabled (either a local cache or remote cache). If a caching sub-system is &quot;disabled,&quot; it is not used *at all* for the current build. |

## Functions

| Name | Summary |
|---|---|
| [disable](../-buildless-extension-a-p-i/-cache-settings/disable.md) | [JVM (Gradle)]<br>open fun [disable](../-buildless-extension-a-p-i/-cache-settings/disable.md)()<br>Shorthand function to disable this cache configuration. |
| [enable](../-buildless-extension-a-p-i/-cache-settings/enable.md) | [JVM (Gradle)]<br>open fun [enable](../-buildless-extension-a-p-i/-cache-settings/enable.md)()<br>Shorthand function to enable this cache configuration. |
