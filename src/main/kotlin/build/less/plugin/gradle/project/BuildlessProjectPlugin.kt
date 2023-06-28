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
