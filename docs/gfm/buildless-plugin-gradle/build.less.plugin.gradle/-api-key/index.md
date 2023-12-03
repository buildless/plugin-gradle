//[buildless-plugin-gradle](../../../index.md)/[build.less.plugin.gradle](../index.md)/[ApiKey](index.md)

# ApiKey

[JVM (Gradle)]\
@[JvmInline](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-inline/index.html)

@Serializable

value class [ApiKey](index.md) : [Serializable](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/Serializable.html), [Comparable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-comparable/index.html)&lt;[ApiKey](index.md)&gt; 

Describes a checked Buildless API key, which also carries metadata with it about the key. The key itself can be accessed via the [key](key.md) getter.

##  Usage examples

Use this value class when constructing a well-typed API key during plugin configuration. For example:

```kotlin
import build.less.plugin.gradle.*
import build.less.plugin.gradle.settings.*

plugin {
  id("build.less") version "..."
}

buildless {
  apiKey(ApiKey.of("..."))
}

// or, to enforce only `ORG`-type API keys:
buildless {
  apiKey(ApiKey.of("...", type = SubjectType.ORG))
}
```

##  About API keys in Buildless

API keys are issued at the **org** and **user** levels. When a user is added to a Buildless org, they gain permission to access that org tenant's data via their own personal keys.

When you use an org key, no specific user is attached to the request. This is useful for systems like CI which don't run as any specific developer.

###  Key types

There are two **key types** in Buildless: the **main** API key, and the **events** API key. Users and org tenants both get each kind of key. When configuring the Buildless plugin for Gradle, you should always use your **main** key.

###  API key checks

The API key is checked for basic structural validity, and enforced against the type provided by the user, if any. If the key fails these checks, an error is thrown which halts the build.

If it is preferable to only perform cursory checks at runtime and allow the build to proceed in most cases, use any of the environment variable, system/Gradle properties, or configuration file methods to specify your keys. In this case, merely withholding the keys will gracefully skip enabling the cache.

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [JVM (Gradle)]<br>object [Companion](-companion/index.md)<br>Provides factory methods for easily creating [ApiKey](index.md) objects. |
| [InvalidKey](-invalid-key/index.md) | [JVM (Gradle)]<br>class [InvalidKey](-invalid-key/index.md)(cause: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)) : [InvalidConfiguration](../../build.less.plugin.gradle.err/-invalid-configuration/index.md)<br>Exception which is thrown when an API key (detected or provided) is found to be invalid. This is a configuration error which must be remedied by the user. |
| [SubjectType](-subject-type/index.md) | [JVM (Gradle)]<br>enum [SubjectType](-subject-type/index.md) : [Enum](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)&lt;[ApiKey.SubjectType](-subject-type/index.md)&gt; <br>Enumerates the types of &quot;subjects&quot; (security principals) for which API keys are made available by the Buildless service. |

## Properties

| Name | Summary |
|---|---|
| [key](key.md) | [JVM (Gradle)]<br>val [key](key.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>API key value held by this object; this value is considered sensitive and should not be logged. |
| [type](type.md) | [JVM (Gradle)]<br>val [type](type.md): [ApiKey.SubjectType](-subject-type/index.md)<br>API key value held by this object; this value is considered sensitive and should not be logged. |
| [version](version.md) | [JVM (Gradle)]<br>val [version](version.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Return the format version of the underlying API key (always `1` at present). |

## Functions

| Name | Summary |
|---|---|
| [compareTo](compare-to.md) | [JVM (Gradle)]<br>open operator override fun [compareTo](compare-to.md)(other: [ApiKey](index.md)): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Compare this API key to the [other](compare-to.md) API key. |
| [toString](to-string.md) | [JVM (Gradle)]<br>open override fun [toString](to-string.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Generate a reasonable string representation which does not show the API key value. |
