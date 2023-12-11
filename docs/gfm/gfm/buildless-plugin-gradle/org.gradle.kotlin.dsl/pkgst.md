//[buildless-plugin-gradle](../../index.md)/[org.gradle.kotlin.dsl](index.md)/[pkgst](pkgst.md)

# pkgst

[JVM (Gradle)]\
fun RepositoryHandler.[pkgst](pkgst.md)(vararg ecosystems: [PkgstEcosystem](-pkgst-ecosystem/index.md))

# Pkgst DSL

Install support for [Pkgst](https://docs.less.build/docs/pkgst) for the provided [ecosystems](pkgst.md); by default, the appropriate ecosystem is inferred from the current context (but generally activates to one of [PkgstEcosystem.MAVEN](-pkgst-ecosystem/-m-a-v-e-n/index.md) or [PkgstEcosystem.GRADLE](-pkgst-ecosystem/-g-r-a-d-l-e/index.md) when used from the Gradle plugin).

###  What is Pkgst?

Pkgst is a caching dependency firewall, based on Cloudflare and powered in concert with Buildless. Accelerate, secure, and cache your package downloads over the same connection as your build cache.

 

##  Usage

Call `pkgst` within a Gradle block for configuring repositories:

```kotlin
repositories {
  pkgst()
}
```

Optionally, provide specific repositories to install through Pkgst:

```kotlin
repositories {
  pkgst(MAVEN, JITPACK)
}
```

You can use it from your `settings.gradle.kts` file as well:

```kotlin
pluginManagement {
  repositories {
    pkgst(GRADLE)
  }
}

dependencyResolutionManagement {
  repositoriesMode = RepositoriesMode.PREFER_PROJECT
  repositories {
    pkgst(MAVEN)
  }
}
```

#### Receiver

Repositories handler in a Gradle script.

#### Parameters

JVM (Gradle)

| | |
|---|---|
| ecosystems | Optional ecosystems to install Pkgst support for; if not provided, the appropriate ecosystem(s) are inferred from context. |
