//[buildless-plugin-gradle](../../../../index.md)/[build.less.plugin.gradle](../../index.md)/[ApiKey](../index.md)/[InvalidKey](index.md)

# InvalidKey

[JVM (Gradle)]\
class [InvalidKey](index.md)(cause: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)) : [InvalidConfiguration](../../../build.less.plugin.gradle.err/-invalid-configuration/index.md)

##  Configuration Error: Invalid API Key

Exception which is thrown when an API key (detected or provided) is found to be invalid. This is a configuration error which must be remedied by the user.

## Constructors

| | |
|---|---|
| [InvalidKey](-invalid-key.md) | [JVM (Gradle)]<br>constructor(cause: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [cause](../../../build.less.plugin.gradle.err/-invalid-configuration/index.md#-654012527%2FProperties%2F73423754) | [JVM (Gradle)]<br>open val [cause](../../../build.less.plugin.gradle.err/-invalid-configuration/index.md#-654012527%2FProperties%2F73423754): [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)? |
| [code](code.md) | [JVM (Gradle)]<br>open override val [code](code.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Unique error code for this error case. |
| [message](../../../build.less.plugin.gradle.err/-invalid-configuration/index.md#1824300659%2FProperties%2F73423754) | [JVM (Gradle)]<br>open val [message](../../../build.less.plugin.gradle.err/-invalid-configuration/index.md#1824300659%2FProperties%2F73423754): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |

## Functions

| Name | Summary |
|---|---|
| [addSuppressed](../../../build.less.plugin.gradle.err/-invalid-configuration/index.md#282858770%2FFunctions%2F73423754) | [JVM (Gradle)]<br>fun [addSuppressed](../../../build.less.plugin.gradle.err/-invalid-configuration/index.md#282858770%2FFunctions%2F73423754)(p0: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)) |
| [fillInStackTrace](../../../build.less.plugin.gradle.err/-invalid-configuration/index.md#-1102069925%2FFunctions%2F73423754) | [JVM (Gradle)]<br>open fun [fillInStackTrace](../../../build.less.plugin.gradle.err/-invalid-configuration/index.md#-1102069925%2FFunctions%2F73423754)(): [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html) |
| [getLocalizedMessage](../../../build.less.plugin.gradle.err/-invalid-configuration/index.md#1043865560%2FFunctions%2F73423754) | [JVM (Gradle)]<br>open fun [getLocalizedMessage](../../../build.less.plugin.gradle.err/-invalid-configuration/index.md#1043865560%2FFunctions%2F73423754)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [getStackTrace](../../../build.less.plugin.gradle.err/-invalid-configuration/index.md#2050903719%2FFunctions%2F73423754) | [JVM (Gradle)]<br>open fun [getStackTrace](../../../build.less.plugin.gradle.err/-invalid-configuration/index.md#2050903719%2FFunctions%2F73423754)(): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[StackTraceElement](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/StackTraceElement.html)&gt; |
| [getSuppressed](../../../build.less.plugin.gradle.err/-invalid-configuration/index.md#672492560%2FFunctions%2F73423754) | [JVM (Gradle)]<br>fun [getSuppressed](../../../build.less.plugin.gradle.err/-invalid-configuration/index.md#672492560%2FFunctions%2F73423754)(): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)&gt; |
| [initCause](../../../build.less.plugin.gradle.err/-invalid-configuration/index.md#-418225042%2FFunctions%2F73423754) | [JVM (Gradle)]<br>open fun [initCause](../../../build.less.plugin.gradle.err/-invalid-configuration/index.md#-418225042%2FFunctions%2F73423754)(p0: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)): [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html) |
| [printStackTrace](../../../build.less.plugin.gradle.err/-invalid-configuration/index.md#-1769529168%2FFunctions%2F73423754) | [JVM (Gradle)]<br>open fun [printStackTrace](../../../build.less.plugin.gradle.err/-invalid-configuration/index.md#-1769529168%2FFunctions%2F73423754)()<br>open fun [printStackTrace](../../../build.less.plugin.gradle.err/-invalid-configuration/index.md#1841853697%2FFunctions%2F73423754)(p0: [PrintStream](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/PrintStream.html))<br>open fun [printStackTrace](../../../build.less.plugin.gradle.err/-invalid-configuration/index.md#1175535278%2FFunctions%2F73423754)(p0: [PrintWriter](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/PrintWriter.html)) |
| [setStackTrace](../../../build.less.plugin.gradle.err/-invalid-configuration/index.md#2135801318%2FFunctions%2F73423754) | [JVM (Gradle)]<br>open fun [setStackTrace](../../../build.less.plugin.gradle.err/-invalid-configuration/index.md#2135801318%2FFunctions%2F73423754)(p0: [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[StackTraceElement](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/StackTraceElement.html)&gt;) |
