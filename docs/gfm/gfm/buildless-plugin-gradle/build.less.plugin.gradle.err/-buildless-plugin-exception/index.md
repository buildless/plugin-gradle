//[buildless-plugin-gradle](../../../index.md)/[build.less.plugin.gradle.err](../index.md)/[BuildlessPluginException](index.md)

# BuildlessPluginException

sealed class [BuildlessPluginException](index.md) : [RuntimeException](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/RuntimeException.html)

# Plugin Exception: Base

Describes an abstract base exception from which all Buildless plugin exceptions inherit; this type may be caught to handle all plugin exceptions.

#### Parameters

JVM (Gradle)

| | |
|---|---|
| message | Error message to specify (optional). |
| cause | Underlying error cause (optional). |

#### Inheritors

| |
|---|
| [InvalidConfiguration](../-invalid-configuration/index.md) |

## Properties

| Name | Summary |
|---|---|
| [cause](../-invalid-configuration/index.md#-654012527%2FProperties%2F73423754) | [JVM (Gradle)]<br>open val [cause](../-invalid-configuration/index.md#-654012527%2FProperties%2F73423754): [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)? |
| [message](../-invalid-configuration/index.md#1824300659%2FProperties%2F73423754) | [JVM (Gradle)]<br>open val [message](../-invalid-configuration/index.md#1824300659%2FProperties%2F73423754): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |

## Functions

| Name | Summary |
|---|---|
| [addSuppressed](../-invalid-configuration/index.md#282858770%2FFunctions%2F73423754) | [JVM (Gradle)]<br>fun [addSuppressed](../-invalid-configuration/index.md#282858770%2FFunctions%2F73423754)(p0: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)) |
| [fillInStackTrace](../-invalid-configuration/index.md#-1102069925%2FFunctions%2F73423754) | [JVM (Gradle)]<br>open fun [fillInStackTrace](../-invalid-configuration/index.md#-1102069925%2FFunctions%2F73423754)(): [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html) |
| [getLocalizedMessage](../-invalid-configuration/index.md#1043865560%2FFunctions%2F73423754) | [JVM (Gradle)]<br>open fun [getLocalizedMessage](../-invalid-configuration/index.md#1043865560%2FFunctions%2F73423754)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [getStackTrace](../-invalid-configuration/index.md#2050903719%2FFunctions%2F73423754) | [JVM (Gradle)]<br>open fun [getStackTrace](../-invalid-configuration/index.md#2050903719%2FFunctions%2F73423754)(): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[StackTraceElement](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/StackTraceElement.html)&gt; |
| [getSuppressed](../-invalid-configuration/index.md#672492560%2FFunctions%2F73423754) | [JVM (Gradle)]<br>fun [getSuppressed](../-invalid-configuration/index.md#672492560%2FFunctions%2F73423754)(): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)&gt; |
| [initCause](../-invalid-configuration/index.md#-418225042%2FFunctions%2F73423754) | [JVM (Gradle)]<br>open fun [initCause](../-invalid-configuration/index.md#-418225042%2FFunctions%2F73423754)(p0: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)): [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html) |
| [printStackTrace](../-invalid-configuration/index.md#-1769529168%2FFunctions%2F73423754) | [JVM (Gradle)]<br>open fun [printStackTrace](../-invalid-configuration/index.md#-1769529168%2FFunctions%2F73423754)()<br>open fun [printStackTrace](../-invalid-configuration/index.md#1841853697%2FFunctions%2F73423754)(p0: [PrintStream](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/PrintStream.html))<br>open fun [printStackTrace](../-invalid-configuration/index.md#1175535278%2FFunctions%2F73423754)(p0: [PrintWriter](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/PrintWriter.html)) |
| [setStackTrace](../-invalid-configuration/index.md#2135801318%2FFunctions%2F73423754) | [JVM (Gradle)]<br>open fun [setStackTrace](../-invalid-configuration/index.md#2135801318%2FFunctions%2F73423754)(p0: [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[StackTraceElement](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/StackTraceElement.html)&gt;) |
