/* Copyright (c) 2023 Elide Ventures LLC
 *
 * This is private computer source code. This code is part of an application which is licensed privately, as part of
 * intellectual property owned by Elide Ventures, LLC. All rights are reserved. Viewing and editing this code implies
 * agreement with the Elide Non-Disclosure Agreement and Elide Inventions Assignment Agreement.
 *
 * Code bearing this header may not be shared outside of authorized circumstances without prior written consent from
 * authorized corporate officers of Elide Ventures, LLC.
 */

package build.less.plugin.gradle

import build.less.plugin.gradle.core.API
import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings

/**
 * # Buildless for Gradle
 *
 * Entrypoint for the official Buildless plug-in for Gradle, which adds (1) optimized transport capabilities for caching
 * and artifact downloads, (2) a lightning fast remote build cache, and (3) helpful configuration for the Kotlin DSL.
 */
@API internal class BuildlessPlugin : Plugin<Settings> {
  override fun apply(target: Settings) {
    // install extension instance
    target.extensions.add("buildless", BuildlessExtension::class.java)

    // install default service instance
    target.gradle.sharedServices.registerIfAbsent("buildless", BuildlessService::class.java) {
      // nothing at this time
    }
  }
}
