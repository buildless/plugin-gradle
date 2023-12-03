@file:Suppress("UnstableApiUsage")

pluginManagement {
  repositories {
    maven("https://gradle.pkg.st/")
  }
}

plugins {
  id("com.gradle.enterprise") version("3.15.1")
  id("org.gradle.toolchains.foojay-resolver-convention") version("0.7.0")
}

val embeddedDeps: String by settings
val micronautVersion: String by settings

gradleEnterprise {
  buildScan {
    termsOfServiceUrl = "https://gradle.com/terms-of-service"
    termsOfServiceAgree = "yes"
  }
}

dependencyResolutionManagement {
  repositoriesMode.set(
    RepositoriesMode.PREFER_SETTINGS
  )
  repositories {
    maven("https://maven.pkg.st/")
  }
  versionCatalogs {
    create("libs") {
      if (embeddedDeps != "true") {
        from(files("../../gradle/libs.versions.toml"))
      }
    }
  }
}

rootProject.name = "buildless-plugin-gradle"

val cacheUsername: String? by settings
val cachePassword: String? by settings
val cachePush: String? by settings
val remoteCache = System.getenv("GRADLE_CACHE_REMOTE")?.toBoolean() ?: false
val localCache = System.getenv("GRADLE_CACHE_LOCAL")?.toBoolean() ?: true

enableFeaturePreview("STABLE_CONFIGURATION_CACHE")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
enableFeaturePreview("GROOVY_COMPILATION_AVOIDANCE")
