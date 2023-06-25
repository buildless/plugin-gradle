//[buildless-plugin-gradle](../../../../index.md)/[build.less.plugin.gradle](../../index.md)/[BuildlessExtensionAPI](../index.md)/[BuildlessSettings](index.md)/[telemetry](telemetry.md)

# telemetry

[JVM (Gradle)]\
abstract var [telemetry](telemetry.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

###  Telemetry

Whether to enable telemetry for this build.

Telemetry services send various events to the Buildless API, and additionally annotate builds with a unique ID which the service uses to correlate cache traffic. This is useful for debugging and diagnostics, and additionally powers several types of reporting provided by Buildless.

This data is inherently sensitive, and so it is never sold or shared with third parties (it's not that kind of analytics/telemetry). This feature may be disabled at any time, but it will result in missing report data.
