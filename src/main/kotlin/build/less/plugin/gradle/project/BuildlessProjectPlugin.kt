/* Copyright (c) 2023 Elide Ventures LLC
 *
 * This is private computer source code. This code is part of an application which is licensed privately, as part of
 * intellectual property owned by Elide Ventures, LLC. All rights are reserved. Viewing and editing this code implies
 * agreement with the Elide Non-Disclosure Agreement and Elide Inventions Assignment Agreement.
 *
 * Code bearing this header may not be shared outside of authorized circumstances without prior written consent from
 * authorized corporate officers of Elide Ventures, LLC.
 */

package build.less.plugin.gradle.project

import build.less.plugin.gradle.core.API
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * # Buildless for Gradle: Project Plugin
 *
 * This plug-in is an internal project-level plugin which is auto-applied by the main settings-time plugin. It should
 * not be used by code directly. Enables use of the [BuildlessProjectExtension] from within Gradle projects when the
 * main settings plug-in is applied.
 */
@API internal class BuildlessProjectPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    target.extensions.add("buildless", BuildlessProjectExtension::class.java)
  }
}
