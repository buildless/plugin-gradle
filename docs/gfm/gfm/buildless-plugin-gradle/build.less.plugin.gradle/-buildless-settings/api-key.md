//[buildless-plugin-gradle](../../../index.md)/[build.less.plugin.gradle](../index.md)/[BuildlessSettings](index.md)/[apiKey](api-key.md)

# apiKey

[JVM (Gradle)]\
open fun [apiKey](api-key.md)(value: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))

Set the API key from the provided string [value](api-key.md).

#### Return

Checked API key value.

#### Parameters

JVM (Gradle)

| | |
|---|---|
| value | API key value to use. |

#### Throws

| | |
|---|---|
| [ApiKey.InvalidKey](../-api-key/-invalid-key/index.md) | if the provided key [value](api-key.md) is found to be malformed. |

[JVM (Gradle)]\
open fun [apiKey](api-key.md)(key: [ApiKey](../-api-key/index.md))

Set the API [key](api-key.md) to use when interacting with the Buildless service.

This interface accepts a key which has already been checked for use. For a full description of how API keys are resolved and loaded, see the [apiKey](api-key.md) field.

#### Parameters

JVM (Gradle)

| | |
|---|---|
| key | API key to use (pre-checked). |

[JVM (Gradle)]\
abstract val [apiKey](api-key.md): Property&lt;[ApiKey](../-api-key/index.md)?&gt;

###  API Key

API key that should be used to identify this project to the Buildless cache service.

API keys can be obtained from the Buildless Dashboard (see [Buildless](https://less.build) for more information). Every tenant and user is issued two API keys: their &quot;events&quot; key (also referred to as the `PUBLISHABLE` key), and their own regular user access key.

The access key is the value which should be specified in this spot. Additionally, the key may be provided via any of the following mechanisms:

- 
   Environment variables (`BUILDLESS_API_KEY`, `GRADLE_CACHE_PASSWORD`)
- 
   System properties (`buildless.apiKey`, `cachePassword`)
- 
   Local file (`.buildless.toml`, `.github/buildless.toml`, `.buildless/config.toml`, `package.json:buildcache`)
- 
   User-level configuration file (`~/.config/buildless.toml`, `~/.buildless/config.toml`)
