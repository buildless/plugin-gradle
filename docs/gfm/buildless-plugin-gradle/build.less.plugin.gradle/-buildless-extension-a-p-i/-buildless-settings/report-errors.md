//[buildless-plugin-gradle](../../../../index.md)/[build.less.plugin.gradle](../../index.md)/[BuildlessExtensionAPI](../index.md)/[BuildlessSettings](index.md)/[reportErrors](report-errors.md)

# reportErrors

[JVM (Gradle)]\
abstract var [reportErrors](report-errors.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

###  Error Reporting

Specifies whether errors should be reported to the Buildless API. When error reporting is active, errors which are encountered when interacting with the cache are reported to a central service. These logs include only the authorization material provided by the developer, along with light context from the Gradle build.

For an exhaustive list of telemetry sent during error reporting, please contact support. It isn't anything weird, we just haven't documented it fully yet.
