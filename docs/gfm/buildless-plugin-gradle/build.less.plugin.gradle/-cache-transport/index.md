//[buildless-plugin-gradle](../../../index.md)/[build.less.plugin.gradle](../index.md)/[CacheTransport](index.md)

# CacheTransport

[JVM (Gradle)]\
enum [CacheTransport](index.md) : [Enum](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)&lt;[CacheTransport](index.md)&gt; 

# Buildless for Gradle: Cache Transport

Enumerates the types of transport engines which are available for use when interacting with Buildless. By default, the [BUILTIN](-b-u-i-l-t-i-n/index.md) transport is used, which leverages Gradle's built-in remote build caching over HTTP.

## Entries

| | |
|---|---|
| [BUILTIN](-b-u-i-l-t-i-n/index.md) | [JVM (Gradle)]<br>[BUILTIN](-b-u-i-l-t-i-n/index.md)<br>Use the built-in cache transport mechanism from Gradle. |
| [NEXTGEN](-n-e-x-t-g-e-n/index.md) | [JVM (Gradle)]<br>[NEXTGEN](-n-e-x-t-g-e-n/index.md)<br>Use optimized transport mechanisms from Buildless. |

## Properties

| Name | Summary |
|---|---|
| [entries](entries.md) | [JVM (Gradle)]<br>val [entries](entries.md): [EnumEntries](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.enums/-enum-entries/index.html)&lt;[CacheTransport](index.md)&gt;<br>Returns a representation of an immutable list of all enum entries, in the order they're declared. |
| [name](-n-e-x-t-g-e-n/index.md#-372974862%2FProperties%2F73423754) | [JVM (Gradle)]<br>val [name](-n-e-x-t-g-e-n/index.md#-372974862%2FProperties%2F73423754): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [ordinal](-n-e-x-t-g-e-n/index.md#-739389684%2FProperties%2F73423754) | [JVM (Gradle)]<br>val [ordinal](-n-e-x-t-g-e-n/index.md#-739389684%2FProperties%2F73423754): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |

## Functions

| Name | Summary |
|---|---|
| [valueOf](value-of.md) | [JVM (Gradle)]<br>fun [valueOf](value-of.md)(value: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [CacheTransport](index.md)<br>Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.) |
| [values](values.md) | [JVM (Gradle)]<br>fun [values](values.md)(): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[CacheTransport](index.md)&gt;<br>Returns an array containing the constants of this enum type, in the order they're declared. |
