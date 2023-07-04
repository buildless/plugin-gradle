@file:Suppress(
  "DSL_SCOPE_VIOLATION",
)

val kotlinVersion by properties
val javaVersion: String by properties
val kotlinLangVersion: String by properties
val strict: String by properties
val strictMode = strict == "true"
val javaVersionEnum = JavaVersion.VERSION_11

val resolvedKotlinLangVersion = when (val versionTarget = gradle.gradleVersion.first().toString().toIntOrNull() ?: 8) {
  in 0..5 -> "1.3"
  in 6..7 -> "1.7"
  else -> kotlinLangVersion
}

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
    languageSettings.progressiveMode = true
    languageSettings.languageVersion = resolvedKotlinLangVersion
    languageSettings.apiVersion = resolvedKotlinLangVersion
  }

  target.compilations.all {
    kotlinOptions {
      apiVersion = kotlinLangVersion
      languageVersion = kotlinLangVersion
      allWarningsAsErrors = strictMode
      javaParameters = true
      jvmTarget = javaVersionEnum.majorVersion
    }
  }
}
