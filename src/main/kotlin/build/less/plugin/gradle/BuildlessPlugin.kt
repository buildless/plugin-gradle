/*
 * Copyright (c) 2023 Elide Ventures, LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under the License.
 */

@file:Suppress("HttpUrlsUsage")

package build.less.plugin.gradle

import build.less.plugin.gradle.core.API
import build.less.plugin.gradle.project.BuildlessProjectPlugin
import build.less.plugin.gradle.service.BuildlessCache
import build.less.plugin.gradle.service.BuildlessCacheService
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.initialization.Settings
import org.gradle.api.internal.DocumentationRegistry
import org.gradle.api.invocation.Gradle
import org.gradle.caching.BuildCacheServiceFactory
import org.gradle.caching.BuildCacheServiceFactory.Describer
import org.gradle.caching.http.internal.HttpBuildCacheRequestCustomizer
import org.gradle.internal.resource.transport.http.Http2ClientHelper
import org.gradle.internal.resource.transport.http.Http2Settings
import org.gradle.internal.resource.transport.http.HttpClientHelper
import org.gradle.internal.resource.transport.http.SslContextFactory
import org.gradle.internal.verifier.HttpRedirectVerifier
import java.net.URI
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicReference
import javax.inject.Inject

/**
 * # Buildless for Gradle
 *
 * Entrypoint for the official Buildless plug-in for Gradle, which adds (1) optimized transport capabilities for caching
 * and artifact downloads, (2) a lightning fast remote build cache, and (3) helpful configuration for the Kotlin DSL.
 */
@API public class BuildlessPlugin @Inject constructor(
  docRegistry: DocumentationRegistry,
  sslContextFactory: SslContextFactory,
  private val httpClientHelperFactory: HttpClientHelper.Factory,
  private val requestCustomizer: HttpBuildCacheRequestCustomizer,
) : Plugin<Settings>, BuildCacheServiceFactory<BuildlessCache> {
  private companion object {
    private const val ENABLE_BUILD_CACHE_SERVICES = false
  }

  private val settings: AtomicReference<Settings> = AtomicReference(null)
  private val root: AtomicReference<Project> = AtomicReference(null)
  private val checkedActivation = AtomicBoolean(false)

  // Redirect verifier.
  private val redirectVerifier = HttpRedirectVerifier {
    // no-op at this time
  }

  // HTTP2 settings.
  private val http2Settings = Http2Settings.wrapping(sslContextFactory, redirectVerifier)

  // HTTP2 services.
  private val http2ClientHelper: Http2ClientHelper = Http2ClientHelper.Factory
    .createFactory(docRegistry)
    .create(http2Settings)

  // Cloud endpoint for caching.
  private val cloudUrl: URI by lazy {
    URI.create("https://${Constants.CLOUD_DOMAIN}/cache/generic")
  }

  override fun apply(target: Settings) {
    settings.compareAndSet(null, target)

    // install default service instance
    target.gradle.sharedServices.registerIfAbsent(BuildlessService.NAME, BuildlessService::class.java) {
      // nothing at this time
    }

    // register custom cache type
    if (ENABLE_BUILD_CACHE_SERVICES) target.buildCache.registerBuildCacheService(
      BuildlessCache::class.java,
      BuildlessPlugin::class.java,
    )

    // install extension instance
    target.extensions.add(BuildlessExtension.NAME, BuildlessExtension::class.java)

    // register build event listeners
    target.gradle.settingsEvaluated {
      it.onSettingsEvaluated()
    }
    target.gradle.projectsEvaluated {
      it.onProjectsEvaluated()
    }
    target.gradle.projectsLoaded {
      it.onProjectsLoaded()
    }
  }

  private fun Settings.onSettingsEvaluated() {
    // configure shared service from extension
    extensions.getByType(BuildlessExtension::class.java).let { extension ->
      gradle.sharedServices.registrations.named(BuildlessService.NAME).configure {
        // if the extension has not been configured at all yet, apply default configuration
        val applyDefaults = !extension.isConfigured()

        // if the agent is detected as running, we should apply its configuration
        (it.service.get() as BuildlessService).apply {
          if (applyDefaults) when (val agent = extension.activeAgent) {
            // we have no agent, so we either require an API key, or need to back out.
            null -> extension.activateByDefaults(this@onSettingsEvaluated)
            else -> extension.maybeActivateWithAgent(this@onSettingsEvaluated, agent)
          }
        }
      }
    }
  }

  private fun Gradle.onProjectsLoaded() {
    // check that caching was activated by property
    root.set(rootProject)

    if (startParameter.isBuildCacheEnabled) {
      // apply to root project
      rootProject
        .pluginManager
        .apply(BuildlessProjectPlugin::class.java)

      // install project-level plugin on all projects when enabled
      rootProject.subprojects { target ->
        target.plugins.apply(BuildlessProjectPlugin::class.java)
      }
    }
  }

  private fun Gradle.onProjectsEvaluated() {
    if (!checkedActivation.get()) {
      checkedActivation.compareAndSet(false, true)
      if (
        // don't show this notice for `clean`
        !taskGraph.hasTask("clean") &&

        // only show it if build caching is disabled
        !startParameter.isBuildCacheEnabled &&

        // only show it if the developer has not explicitly disabling the warning
        rootProject.findProperty("buildless.warn.config") != false
      ) {
        // check for cache activation
        val active = when (val caching = rootProject.findProperty(Constants.GRADLE_CACHING_PROPERTY) as? Boolean) {
          null -> false
          else -> caching
        }
        if (!active) rootProject.logger.warn(
          "Buildless plugin is added, but Gradle caching is not enabled; no caching will take place. " +
                  "To enable caching, run Gradle with the `--build-cache` flag or set `org.gradle.caching=true`. " +
                  "To disable this warning, set `buildless.warn.config=false`."
        )
      }
    }
  }

  override fun createBuildCacheService(configuration: BuildlessCache, describer: Describer): BuildlessCacheService {
    return BuildlessCacheService(
      cloudUrl,
      httpClientHelperFactory.create(http2Settings),
      http2ClientHelper,
      requestCustomizer,
      true,  // always use Expect/Continue negotiation
    )
  }
}
