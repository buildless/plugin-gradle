//[buildless-plugin-gradle](../../index.md)/[org.gradle.kotlin.dsl](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [PkgstEcosystem](-pkgst-ecosystem/index.md) | [JVM (Gradle)]<br>enum [PkgstEcosystem](-pkgst-ecosystem/index.md) : [Enum](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)&lt;[PkgstEcosystem](-pkgst-ecosystem/index.md)&gt; , PkgstEndpoint<br>Enumerates supported packaging ecosystems |

## Functions

| Name | Summary |
|---|---|
| [buildless](buildless.md) | [JVM (Gradle)]<br>fun Settings.[buildless](buildless.md)(configure: [BuildlessSettings](../build.less.plugin.gradle/-buildless-settings/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))<br>This function is the main entrypoint for configuring the Buildless Plugin for Gradle. All user-facing helpers which are usable at Settings evaluation time are exposed in this package/file. |
| [pkgst](pkgst.md) | [JVM (Gradle)]<br>fun RepositoryHandler.[pkgst](pkgst.md)(vararg ecosystems: [PkgstEcosystem](-pkgst-ecosystem/index.md))<br>Install support for [Pkgst](https://docs.less.build/docs/pkgst) for the provided [ecosystems](pkgst.md); by default, the appropriate ecosystem is inferred from the current context (but generally activates to one of [PkgstEcosystem.MAVEN](-pkgst-ecosystem/-m-a-v-e-n/index.md) or [PkgstEcosystem.GRADLE](-pkgst-ecosystem/-g-r-a-d-l-e/index.md) when used from the Gradle plugin). |
