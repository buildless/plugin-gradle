//[buildless-plugin-gradle](../../../index.md)/[org.gradle.kotlin.dsl](../index.md)/[PkgstEcosystem](index.md)

# PkgstEcosystem

[JVM (Gradle)]\
enum [PkgstEcosystem](index.md) : [Enum](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)&lt;[PkgstEcosystem](index.md)&gt; , PkgstEndpoint

# Pkgst: Ecosystem

Enumerates supported packaging ecosystems

## Entries

| | |
|---|---|
| [GRADLE](-g-r-a-d-l-e/index.md) | [JVM (Gradle)]<br>[GRADLE](-g-r-a-d-l-e/index.md)<br>Gradle Plugins. |
| [MAVEN](-m-a-v-e-n/index.md) | [JVM (Gradle)]<br>[MAVEN](-m-a-v-e-n/index.md)<br>Maven Central. |
| [GOOGLE](-g-o-o-g-l-e/index.md) | [JVM (Gradle)]<br>[GOOGLE](-g-o-o-g-l-e/index.md)<br>Google Maven. |
| [JITPACK](-j-i-t-p-a-c-k/index.md) | [JVM (Gradle)]<br>[JITPACK](-j-i-t-p-a-c-k/index.md)<br>Jitpack. |
| [NPM](-n-p-m/index.md) | [JVM (Gradle)]<br>[NPM](-n-p-m/index.md)<br>NPM. |

## Properties

| Name | Summary |
|---|---|
| [content](-n-p-m/index.md#-1708690541%2FProperties%2F73423754) | [JVM (Gradle)]<br>open val [content](-n-p-m/index.md#-1708690541%2FProperties%2F73423754): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;ContentGroup&gt;<br>List of content groups to direct to this repository. |
| [domain](domain.md) | [JVM (Gradle)]<br>open override val [domain](domain.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Domain target for this endpoint. |
| [entries](entries.md) | [JVM (Gradle)]<br>val [entries](entries.md): [EnumEntries](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.enums/-enum-entries/index.html)&lt;[PkgstEcosystem](index.md)&gt;<br>Returns a representation of an immutable list of all enum entries, in the order they're declared. |
| [name](-n-p-m/index.md#-372974862%2FProperties%2F73423754) | [JVM (Gradle)]<br>val [name](-n-p-m/index.md#-372974862%2FProperties%2F73423754): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [ordinal](-n-p-m/index.md#-739389684%2FProperties%2F73423754) | [JVM (Gradle)]<br>val [ordinal](-n-p-m/index.md#-739389684%2FProperties%2F73423754): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [uri](-n-p-m/index.md#100874624%2FProperties%2F73423754) | [JVM (Gradle)]<br>open val [uri](-n-p-m/index.md#100874624%2FProperties%2F73423754): [URI](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/net/URI.html)<br>URI for this endpoint. |

## Functions

| Name | Summary |
|---|---|
| [valueOf](value-of.md) | [JVM (Gradle)]<br>fun [valueOf](value-of.md)(value: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [PkgstEcosystem](index.md)<br>Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.) |
| [values](values.md) | [JVM (Gradle)]<br>fun [values](values.md)(): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[PkgstEcosystem](index.md)&gt;<br>Returns an array containing the constants of this enum type, in the order they're declared. |
