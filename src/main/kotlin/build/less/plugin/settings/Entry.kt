/* Copyright (c) 2023 Elide Ventures LLC
 *
 * This is private computer source code. This code is part of an application which is licensed privately, as part of
 * intellectual property owned by Elide Ventures, LLC. All rights are reserved. Viewing and editing this code implies
 * agreement with the Elide Non-Disclosure Agreement and Elide Inventions Assignment Agreement.
 *
 * Code bearing this header may not be shared outside of authorized circumstances without prior written consent from
 * authorized corporate officers of Elide Ventures, LLC.
 */

package build.less.plugin.settings

import build.less.plugin.gradle.BuildlessExtension
import build.less.plugin.gradle.BuildlessExtensionAPI.BuildlessSettings
import org.gradle.api.initialization.Settings

/**
 * # Buildless: Plugin Configuration
 *
 * This function is the main entrypoint for configuring the Buildless Plugin for Gradle. All user-facing helpers which
 * are usable at [Settings] evaluation time are exposed in this package/file.
 *
 * The [buildless] function may be run within the [Settings] context once the plugin has loaded. This can be done via
 * the following snippet:
 *
 * ```kotlin
 * import build.less.plugin.settings.*;
 *
 * plugins {
 *   id("build.less") version "..."
 * }
 * ```
 *
 * ## Configuration Context
 *
 * The [configure] lambda is executed within the [BuildlessSettings] context, which allows for building configuration
 * which is then shared within the context of a (potentially multi-project) Gradle build. Settings can be configured
 * at the top-level, via this function, or within a subproject context using the project-level plugin.
 *
 * The [BuildlessSettings] interface should be consulted for an exhaustive list of configuration options.
 *
 * @see BuildlessSettings for an exhaustive list of configuration options available at settings evaluation time.
 */
public fun Settings.buildless(configure: BuildlessSettings.() -> Unit) {
  extensions.configure(BuildlessExtension::class.java) {
    it.config(this, configure)
  }
}
