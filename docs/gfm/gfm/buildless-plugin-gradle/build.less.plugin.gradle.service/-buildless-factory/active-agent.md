//[buildless-plugin-gradle](../../../index.md)/[build.less.plugin.gradle.service](../index.md)/[BuildlessFactory](index.md)/[activeAgent](active-agent.md)

# activeAgent

[JVM (Gradle)]\
abstract fun [activeAgent](active-agent.md)(): [AgentConfig](../../build.less.plugin.gradle/-agent-config/index.md)?

Retrieve the active agent configuration, if any.

The first time this method is called, the configuration is found, read, and loaded from JSON; subsequent calls use a cached version.

#### Return

Active and decoded agent configuration, or `null`, if none exists.
