package org.gradle.kotlin.dsl

import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.kotlin.dsl.PkgstEcosystem.GRADLE
import org.gradle.kotlin.dsl.PkgstEcosystem.MAVEN
import java.net.URI

// Pkgst domain.
private const val PKGST_DOMAIN = "pkg.st"

// Denotes a content group which is bound to a repository.
private interface ContentGroup {
  /** Artifact group to denote. */
  val group: String
}

// Defines a domain for a Pkgst ecosystem.
private interface PkgstEndpoint {
  /** Domain target for this endpoint. */
  val domain: String

  /** URI for this endpoint. */
  val uri: URI get() = URI.create("https://$domain")

  /** List of content groups to direct to this repository. */
  val content: List<ContentGroup> get() = emptyList()
}

/** Names for each package ecosystem subdomain. */
private data object PkgstSubdomain {
  /** Gradle Plugins. */
  const val GRADLE: String = "gradle"

  /** Maven Central. */
  const val MAVEN: String = "maven"

  /** Google's Maven artifacts. */
  const val GOOGLE: String = "google"

  /** Jitpack-based artifacts. */
  const val JITPACK: String = "jitpack"

  /** NPM. */
  const val NPM: String = "npm"
}

/**
 * # Pkgst: Ecosystem
 *
 * Enumerates supported packaging ecosystems
 */
public enum class PkgstEcosystem(private val symbol: String) : PkgstEndpoint {
  /** Gradle Plugins. */
  GRADLE(PkgstSubdomain.GRADLE),

  /** Maven Central. */
  MAVEN(PkgstSubdomain.MAVEN),

  /** Google Maven. */
  GOOGLE(PkgstSubdomain.GOOGLE),

  /** Jitpack. */
  JITPACK(PkgstSubdomain.JITPACK),

  /** NPM. */
  NPM(PkgstSubdomain.NPM);

  override val domain: String get() = "$symbol.$PKGST_DOMAIN"
}

/**
 * # Pkgst DSL
 *
 * Install support for [Pkgst](https://docs.less.build/docs/pkgst) for the provided [ecosystems]; by default, the
 * appropriate ecosystem is inferred from the current context (but generally activates to one of [PkgstEcosystem.MAVEN]
 * or [PkgstEcosystem.GRADLE] when used from the Gradle plugin).
 *
 * ### What is Pkgst?
 *
 * Pkgst is a caching dependency firewall, based on Cloudflare and powered in concert with Buildless. Accelerate,
 * secure, and cache your package downloads over the same connection as your build cache.
 *
 * &nbsp;
 *
 * ## Usage
 *
 * Call `pkgst` within a Gradle block for configuring repositories:
 *
 * ```kotlin
 * repositories {
 *   pkgst()
 * }
 * ```
 *
 * Optionally, provide specific repositories to install through Pkgst:
 *
 * ```kotlin
 * repositories {
 *   pkgst(MAVEN, JITPACK)
 * }
 * ```
 *
 * You can use it from your `settings.gradle.kts` file as well:
 * ```kotlin
 * pluginManagement {
 *   repositories {
 *     pkgst(GRADLE)
 *   }
 * }
 *
 * dependencyResolutionManagement {
 *   repositoriesMode = RepositoriesMode.PREFER_PROJECT
 *   repositories {
 *     pkgst(MAVEN)
 *   }
 * }
 * ```
 *
 * @receiver Repositories handler in a Gradle script.
 * @param ecosystems Optional ecosystems to install Pkgst support for; if not provided, the appropriate ecosystem(s) are
 *   inferred from context.
 */
public fun RepositoryHandler.pkgst(vararg ecosystems: PkgstEcosystem): Unit = when (ecosystems.isEmpty()) {
  // if `true`, infer repositories from context
  true -> pkgst(MAVEN, GRADLE)

  // if `false`, install repositories for the provided ecosystems
  false -> ecosystems.forEach { repository ->
    maven {
      it.url = repository.uri
      repository.content.forEach { content ->
        it.content {
          it.includeGroup(content.group)
        }
      }
    }
  }
}
