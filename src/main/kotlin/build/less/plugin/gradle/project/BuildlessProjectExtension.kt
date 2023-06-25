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

import build.less.plugin.gradle.BuildlessExtensionAPI
import build.less.plugin.gradle.BuildlessExtensionAPI.BuildlessProjectSettings
import build.less.plugin.gradle.core.API
import org.gradle.api.Project

/**
 * # Buildless for Gradle: Project Extension
 *
 * Adds project-level configuration features to Gradle projects which are enabled with Buildless, via the main settings
 * time plugin.
 */
@API internal class BuildlessProjectExtension : BuildlessExtensionAPI<Project, BuildlessProjectSettings> {
  override fun config(context: Project, configure: BuildlessProjectSettings.() -> Unit) {
    TODO("Not yet implemented")
  }
}
