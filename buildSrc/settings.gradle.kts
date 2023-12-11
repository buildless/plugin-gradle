@file:Suppress("UnstableApiUsage")

pluginManagement {
  repositories {
    maven("https://gradle.pkg.st/")
  }
}

plugins {
  id("org.gradle.toolchains.foojay-resolver-convention")
}

rootProject.name = "gradle-build"
val embeddedDeps: String by settings

dependencyResolutionManagement {
  repositoriesMode.set(
    RepositoriesMode.PREFER_SETTINGS
  )
  repositories {
    maven("https://gradle.pkg.st/")
  }
  versionCatalogs {
    create("libs") {
      from(files(if (embeddedDeps != "true") {
        "../../../gradle/libs.versions.toml"
      } else {
        "../gradle/plugin.versions.toml"
      }))
    }
  }
}

enableFeaturePreview("STABLE_CONFIGURATION_CACHE")
