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
