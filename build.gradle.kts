
/**
 * Buildless Plugin for Gradle
 */

@file:Suppress("UnstableApiUsage", "COMPATIBILITY_WARNING")

import build.buf.gradle.BUF_BUILD_DIR
import build.buf.gradle.GENERATED_DIR
import org.gradle.jvm.tasks.Jar
import org.gradle.plugins.ide.idea.model.IdeaLanguageLevel
import org.jetbrains.dokka.DokkaConfiguration.Visibility
import org.jetbrains.dokka.base.DokkaBase
import org.jetbrains.dokka.base.DokkaBaseConfiguration
import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.gradle.dsl.ExplicitApiMode
import java.net.URI

plugins {
  idea
  signing
  `project-reports`
  `java-gradle-plugin`
  `groovy-gradle-plugin`
  kotlin("jvm")

  alias(libs.plugins.cyclonedx)
  alias(libs.plugins.buf)
  alias(libs.plugins.buildConfig)
  alias(libs.plugins.detekt)
  alias(libs.plugins.dokka)
  alias(libs.plugins.kotlin.plugin.allopen)
  alias(libs.plugins.kotlin.plugin.serialization)
  alias(libs.plugins.kotlinx.plugin.abiValidator)
  alias(libs.plugins.kover)
  alias(libs.plugins.ksp)
  alias(libs.plugins.nexus.publish)
  alias(libs.plugins.pluginPublish)
  alias(libs.plugins.sbom)
  alias(libs.plugins.sigstore)
  alias(libs.plugins.sonar)
  alias(libs.plugins.spotless)
  alias(libs.plugins.testLogger)
  alias(libs.plugins.versionCheck)
}

buildscript {
  dependencies {
    classpath("org.jetbrains.dokka:dokka-base:${libs.versions.dokka.get()}")
  }
}

val kotlinVersion by properties
val javaVersion: String by properties
val kotlinLangVersion: String by properties
val strict: String by properties
val pluginId: String by properties
val strictMode = strict == "true"
val buildDocs: String by properties
val enableChecks: String by properties
val lockDeps: String by properties
val isCI = project.hasProperty("ci") && project.properties["ci"] == "true"
val kotlinVersionEnum = org.jetbrains.kotlin.gradle.dsl.KotlinVersion.KOTLIN_1_9
val jvmTargetEnum = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
val javaVersionEnum = JavaVersion.VERSION_11

// Set version from `.version` if stamping is enabled.
val versionFile = File("./.version")
group = "build.less"
version = if (project.hasProperty("stamp") && project.properties["stamp"] == "true") {
  file(".version").readText().trim().replace("\n", "").ifBlank {
    throw IllegalStateException("Failed to load `.version`")
  }
} else if (project.hasProperty("version")) {
  project.properties["version"] as String
} else if (versionFile.exists()) {
  versionFile.readText()
} else {
  "1.0-SNAPSHOT"
}

// Code sample files to render.
val samplesList = arrayListOf(
  "samples/kotlin-dsl-basic.kts",
)

//
//  ------------------------------------------------------------------------------------------------------------------
//

// --- Plugin Configuration
//

buildConfig {
  packageName("build.less.plugin.gradle.cfg")

  buildConfigField("String", "PLUGIN_ID", "\"$pluginId\"")
  buildConfigField("String", "PLUGIN_VERSION", "\"$version\"")

  useKotlinOutput {
    topLevelConstants = true
    internalVisibility = true
  }
}

buf {
  enforceFormat = false
  publishSchema = false
  toolVersion = libs.versions.buf.tool.get()

  generate {
    includeImports = true
  }
}

gradlePlugin {
  website.set("https://less.build")
  vcsUrl.set("https://github.com/buildless/plugin-gradle.git")

  plugins {
    create("buildless") {
      id = "build.less"
      implementationClass = "build.less.plugin.gradle.BuildlessPlugin"
      displayName = "Buildless for Gradle"
      description = "Configures Gradle projects for use with Buildless as a drop-in remote cache"
      tags.addAll(listOf("buildcache", "build-cache", "remote-build-cache", "buildless"))
    }
  }
}

java {
  sourceCompatibility = javaVersionEnum
  targetCompatibility = javaVersionEnum

  withSourcesJar()
  withJavadocJar()
}

kotlin {
  explicitApi = ExplicitApiMode.Strict

  sourceSets.all {
    languageSettings.progressiveMode = false
    languageSettings.languageVersion = kotlinLangVersion
    languageSettings.apiVersion = kotlinLangVersion
  }
}

allOpen {
  annotation("build.less.plugin.gradle.core.API")
}

apiValidation {
  nonPublicMarkers.add("build.less.plugin.gradle.core.Internal")
}

testlogger {
  theme = com.adarshr.gradle.testlogger.theme.ThemeType.MOCHA_PARALLEL
  showExceptions = System.getenv("TEST_EXCEPTIONS") == "true"
  showFailedStandardStreams = System.getenv("TEST_LOGS") == "true"
  showFailed = true
  showPassed = true
  showSkipped = true
  showFullStackTraces = true
  slowThreshold = 30000L
}

kover {
  excludeJavaCode()

  if (!project.hasProperty("useIntellij"))
    useJacoco()

  excludeInstrumentation {
    packages(
      "com.validate",
      "com.publicapi",
      "com.openapi.v3",
      "com.google.type",
      "com.google.rpc",
      "com.google.api",
      "com.grpc",
      "com.grpc.health.v1",
      "com.elide.model",
      "com.buildless.service.v1",
      "com.buildless",
    )
  }
}

if (enableChecks == "true") detekt {
  parallel = true
  ignoreFailures = true
  buildUponDefaultConfig = true
  config.from(rootProject.layout.projectDirectory.file(".github/detekt.yml"))
}

nexusPublishing {
  this.repositories {
    sonatype {
      nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
      snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
    }
  }
}

publishing {
  repositories {
    maven {
      name = "GitHubPackages"
      url = uri("https://maven.pkg.github.com/buildless/plugin-gradle")
      credentials {
        username = System.getenv("GITHUB_ACTOR")
        password = System.getenv("GITHUB_TOKEN")
      }
    }
  }
  publications {
    fun MavenPublication.pluginPom() {
      pom {
        name.set("Buildless Plugin for Gradle")
        description.set("Easily configure Gradle builds for use with Buildless as a remote build cache")
        url.set("https://github.com/buildless/plugin-gradle")
        licenses {
          license {
            name.set("Private")
          }
        }
        developers {
          developer {
            id.set("sgammon")
            name.set("Sam Gammon")
          }
        }
        scm {
          connection.set("https://github.com/buildless/plugin-gradle.git")
          developerConnection.set("git@github.com:buildless/plugin-gradle.git")
          url.set("https://github.com/buildless/plugin-gradle")
        }
      }
    }

    afterEvaluate {
      withType(MavenPublication::class) {
        pluginPom()
      }
    }
  }
}

spdxSbom {
  targets {
    create("release") {
      scm {
        uri.set("https://github.com/buildless/plugin-gradle.git")
        tool.set("git")
      }

      document {
        name.set("Buildless for Gradle")
        namespace.set("https://less.build/spdx/758A8D36-F55C-4FF8-8C22-7DD1CBE82EDF")
        creator.set("Person:Sam Gammon")
        packageSupplier.set("Organization:Elide")
      }
    }
  }
}

sigstoreSign {
  oidcClient {
    gitHub {
      audience.set("sigstore")
    }
    web {
      clientId.set("sigstore")
      issuer.set("https://oauth2.sigstore.dev/auth")
    }
    client.set(web)
  }
}

if (enableChecks == "true") spotless {
  java {
    target("src/**/*.java")
    targetExclude("**/generated/**", "*/bin/*", "app/*/build")
    importOrder()
    removeUnusedImports()
    googleJavaFormat()
  }
  kotlin {
    target("src/**/*.kt")
    targetExclude("*/generated/*", "*/bin/*", "app/*/build")
    ktlint(libs.versions.ktlint.get()).apply {
      setEditorConfigPath("$rootDir/.editorconfig")
    }
  }
  kotlinGradle {
    ktlint(libs.versions.ktlint.get())
  }
}

if (enableChecks == "true") sonar {
  properties {
    property("sonar.sources", "$projectDir/src/main/kotlin")
    property("sonar.java.binaries", "$projectDir/build/classes")
    property("sonar.projectKey", "buildless_plugin-gradle")
    property("sonar.organization", "buildless")
    property("sonar.host.url", "https://sonarcloud.io")
    property("sonar.dynamicAnalysis", "reuseReports")
    property("sonar.junit.reportsPath", "$rootDir/build/test-results/test/")
    property("sonar.java.coveragePlugin", "jacoco")
    property("sonar.sourceEncoding", "UTF-8")
    property("sonar.coverage.jacoco.xmlReportPaths", rootProject.layout.buildDirectory.file("reports/kover/report.xml"))
  }
}

idea {
  project {
    languageLevel = IdeaLanguageLevel(javaVersion)
    vcs = "Git"
  }
}

koverReport {
  defaults {
    // reports configs for XML, HTML, verify reports
    xml {
      onCheck = isCI
    }
  }
}

// --- Dependencies, Source Sets, Configurations
//

if (lockDeps == "true") dependencyLocking {
  lockAllConfigurations()
}

sourceSets.main.configure {
  java {
    srcDirs(
      layout.buildDirectory.dir("$BUF_BUILD_DIR/$GENERATED_DIR/java"),
      layout.buildDirectory.dir("$BUF_BUILD_DIR/$GENERATED_DIR/pgv"),
    )
  }
  kotlin {
    srcDir(
      layout.buildDirectory.dir("$BUF_BUILD_DIR/$GENERATED_DIR/kotlin"),
    )
  }
}

configurations.all {
  resolutionStrategy {
    failOnVersionConflict()
    preferProjectModules()
    if (lockDeps != "true") failOnNonReproducibleResolution()

    if (name.contains("detached")) {
      disableDependencyVerification()
    }
  }
}

dependencies {
  implementation(libs.buf.connect.kotlin)
  implementation(libs.buf.proto.validate.core)
  implementation(libs.buf.proto.validate.grpc)
  implementation(libs.buf.proto.validate.stub)
  implementation(libs.kotlinx.serialization.core)
  implementation(libs.kotlinx.serialization.json)
  implementation(libs.protobuf.java)
  implementation(libs.protobuf.kotlin)
  implementation(libs.okhttp3)
  implementation(libs.grpc.auth)
  implementation(libs.grpc.api)
  implementation(libs.grpc.core)
  implementation(libs.grpc.protobuf)
  implementation(libs.grpc.stub)
  implementation(libs.grpc.kotlin.stub)

  testImplementation(kotlin("test"))
  testImplementation(libs.protobuf.util)
  testImplementation(libs.grpc.testing)
  testImplementation(libs.truth)
  testImplementation(libs.truth.proto)
  testImplementation(libs.junit.jupiter.api)
  testImplementation(libs.junit.jupiter.params)
  testRuntimeOnly(libs.junit.jupiter.engine)
  testImplementation(gradleTestKit())

  dokkaHtmlPlugin("com.glureau:html-mermaid-dokka-plugin:${libs.versions.mermaidDokka.get()}")
  dokkaHtmlPlugin("org.jetbrains.dokka:versioning-plugin:${libs.versions.dokka.get()}")
}

// --- Project Baselines
//

tasks.withType(Jar::class.java).configureEach {
  isReproducibleFileOrder = true
  isPreserveFileTimestamps = false
}

tasks.withType(Zip::class.java).configureEach {
  isReproducibleFileOrder = true
  isPreserveFileTimestamps = false
  isZip64 = true
}

tasks.withType(Tar::class.java).configureEach {
  isReproducibleFileOrder = true
  isPreserveFileTimestamps = false
}

// --- Task Configuration
//

val docs = if (buildDocs == "true") {
  tasks.register("docs") {
    dependsOn(
      tasks.dokkaHtml,
      tasks.dokkaGfm,
      tasks.dokkaJavadoc,
    )
  }
} else null

tasks.test {
  useJUnitPlatform()
}

tasks.bufGenerate.configure {
  outputs.cacheIf { true }
  inputs.files(
    "$projectDir/buf.yaml",
    "$projectDir/buf.lock",
    "$projectDir/buf.gen.yaml",
  )
}

tasks.compileGroovy.configure {
  dependsOn(tasks.compileKotlin)
  classpath += files(tasks.compileKotlin.get().destinationDirectory)
}

tasks.compileKotlin.configure {
  dependsOn(tasks.bufGenerate)

  kotlinOptions {
    jvmTarget = javaVersion
    javaParameters = true
  }
}

tasks.compileTestKotlin.configure {
  kotlinOptions {
    jvmTarget = javaVersion
    javaParameters = true
  }
}

tasks.cyclonedxBom {
  setProjectType("application")
  setDestination(project.file("build/reports"))
  setOutputName("sbom")
  setOutputFormat("json")
  setIncludeBomSerialNumber(true)
  setIncludeLicenseText(true)
}

tasks.withType<DokkaTask>().configureEach {
  pluginConfiguration<DokkaBase, DokkaBaseConfiguration> {
    customAssets = file("dokka/images").listFiles()?.toList() ?: emptyList()
    customStyleSheets = file("dokka/styles").listFiles()?.toList() ?: emptyList()
    footerMessage = "(c) 2023 Elide Ventures LLC d/b/a Buildless. All Rights Reserved."
    templatesDir = file("$projectDir/dokka/templates")
  }

  dokkaSourceSets.configureEach {
    displayName.set("JVM (Gradle)")
    documentedVisibilities.set(setOf(Visibility.PUBLIC))
    reportUndocumented.set(true)
    suppressGeneratedFiles.set(true)
    suppressedFiles.from(files(layout.buildDirectory.dir(BUF_BUILD_DIR)))
    jdkVersion.set(11)
    languageVersion.set(kotlinLangVersion)
    apiVersion.set(kotlinLangVersion)
    includes.from(project.files(), "Module.md")
    samples.from(project.files(), *samplesList.toTypedArray())

    perPackageOption {
      matchingRegex.set(".*internal.*")
      suppress.set(true)
    }
    sourceLink {
      localDirectory.set(projectDir.resolve("src"))
      remoteUrl.set(URI.create("https://github.com/buildless/plugin-gradle/tree/main/src").toURL())
      remoteLineSuffix.set("#L")
    }
  }
}
