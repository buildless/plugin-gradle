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
