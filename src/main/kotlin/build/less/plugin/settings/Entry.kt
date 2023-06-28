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
