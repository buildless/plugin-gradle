//[buildless-plugin-gradle](../../../index.md)/[build.less.plugin.gradle.err](../index.md)/[InvalidConfiguration](index.md)

# InvalidConfiguration

abstract class [InvalidConfiguration](index.md)(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, cause: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)? = null) : [BuildlessPluginException](../-buildless-plugin-exception/index.md)

# Plugin Exception: Invalid Configuration

Describes an abstract exception base for errors which originate from invalid configuration, or an illegal state expressed by the user when configuring the plugin. These errors are terminal in the sense that they must be remedied by a user in order to be fixed.

#### Parameters

JVM (Gradle)

| | |
|---|---|
| message | Error message to specify (optional). |
| cause | Underlying error cause (optional). |

#### Inheritors

| |
|---|
| [InvalidKey](../../build.less.plugin.gradle/-api-key/-invalid-key/index.md) |

## Constructors

| | |
|---|---|
| [InvalidConfiguration](-invalid-configuration.md) | [JVM (Gradle)]<br>constructor(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, cause: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)? = null) |

## Properties

| Name | Summary |
|---|---|
| [cause](index.md#-654012527%2FProperties%2F73423754) | [JVM (Gradle)]<br>open val [cause](index.md#-654012527%2FProperties%2F73423754): [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)? |
| [code](code.md) | [JVM (Gradle)]<br>abstract val [code](code.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Unique error code for this error case. |
| [message](index.md#1824300659%2FProperties%2F73423754) | [JVM (Gradle)]<br>open val [message](index.md#1824300659%2FProperties%2F73423754): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |

## Functions

| Name | Summary |
|---|---|
| [addSuppressed](index.md#282858770%2FFunctions%2F73423754) | [JVM (Gradle)]<br>fun [addSuppressed](index.md#282858770%2FFunctions%2F73423754)(p0: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)) |
| [fillInStackTrace](index.md#-1102069925%2FFunctions%2F73423754) | [JVM (Gradle)]<br>open fun [fillInStackTrace](index.md#-1102069925%2FFunctions%2F73423754)(): [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html) |
| [getLocalizedMessage](index.md#1043865560%2FFunctions%2F73423754) | [JVM (Gradle)]<br>open fun [getLocalizedMessage](index.md#1043865560%2FFunctions%2F73423754)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [getStackTrace](index.md#2050903719%2FFunctions%2F73423754) | [JVM (Gradle)]<br>open fun [getStackTrace](index.md#2050903719%2FFunctions%2F73423754)(): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[StackTraceElement](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/StackTraceElement.html)&gt; |
| [getSuppressed](index.md#672492560%2FFunctions%2F73423754) | [JVM (Gradle)]<br>fun [getSuppressed](index.md#672492560%2FFunctions%2F73423754)(): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)&gt; |
| [initCause](index.md#-418225042%2FFunctions%2F73423754) | [JVM (Gradle)]<br>open fun [initCause](index.md#-418225042%2FFunctions%2F73423754)(p0: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)): [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html) |
| [printStackTrace](index.md#-1769529168%2FFunctions%2F73423754) | [JVM (Gradle)]<br>open fun [printStackTrace](index.md#-1769529168%2FFunctions%2F73423754)()<br>open fun [printStackTrace](index.md#1841853697%2FFunctions%2F73423754)(p0: [PrintStream](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/PrintStream.html))<br>open fun [printStackTrace](index.md#1175535278%2FFunctions%2F73423754)(p0: [PrintWriter](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/PrintWriter.html)) |
| [setStackTrace](index.md#2135801318%2FFunctions%2F73423754) | [JVM (Gradle)]<br>open fun [setStackTrace](index.md#2135801318%2FFunctions%2F73423754)(p0: [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[StackTraceElement](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/StackTraceElement.html)&gt;) |
