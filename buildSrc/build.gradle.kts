@file:Suppress(
  "DSL_SCOPE_VIOLATION",
)

import org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompilerOptions
import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.util.targets
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val kotlinVersion by properties
val javaVersion: String by properties
val kotlinLangVersion: String by properties
val strict: String by properties
val strictMode = strict == "true"
val kotlinVersionEnum = org.jetbrains.kotlin.gradle.dsl.KotlinVersion.KOTLIN_1_9
val jvmTargetEnum = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
val javaVersionEnum = JavaVersion.VERSION_11

plugins {
  `kotlin-dsl`
  `kotlin-dsl-precompiled-script-plugins`
}

dependencies {
  implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
  implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

java {
  sourceCompatibility = javaVersionEnum
  targetCompatibility = javaVersionEnum
}

kotlin {
  sourceSets.all {
    languageSettings.progressiveMode = false
    languageSettings.languageVersion = kotlinLangVersion
    languageSettings.apiVersion = kotlinLangVersion
  }

  targets.forEach {
    it.compilations.all {
      kotlinOptions {
        apiVersion = kotlinLangVersion
        languageVersion = kotlinLangVersion
        allWarningsAsErrors = strictMode

        if (this is KotlinJvmCompilerOptions) {
          javaParameters.set(true)
          jvmTarget.set(jvmTargetEnum)
        }
      }
    }
  }
}

afterEvaluate {
  tasks.withType(KotlinCompile::class).configureEach {
    kotlinOptions.jvmTarget = javaVersion
  }
}
