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
import org.gradle.api.services.BuildService
import org.gradle.api.services.BuildServiceParameters
import java.util.concurrent.atomic.AtomicReference

/**
 * # Buildless for Gradle: Service
 *
 * This service implementation provides a dependency-injected way to access API clients for the Buildless service, from
 * within a Gradle build.
 */
@API public class BuildlessService :
  BuildService<BuildlessService.Params>,
  AutoCloseable {
  /** Constants associated with the Buildless service. */
  public companion object {
    /** Name where the Buildless service is available. */
    public const val NAME: String = "buildless"
  }

  // Active plugin-wide configuration.
  private val settings: AtomicReference<BuildlessPluginConfig?> = AtomicReference(null)

  /**
   * ## Buildless Service: Parameters
   *
   */
  public interface Params : BuildServiceParameters

  /**
   * Notify the shared Buildless service of settings from the user.
   *
   * @param settings User settings to apply.
   */
  internal fun notifySettings(settings: BuildlessPluginConfig) {
    this.settings.set(settings)
  }

  /**
   * Retrieve the active set of parameters for the current service instance.
   *
   * @return Active parameters.
   */
  override fun getParameters(): Params {
    return object : Params { /* nothing at this time */ }
  }

  /**
   * Close active use of the service, including underlying resources.
   */
  override fun close() {
    // nothing at this time
  }
}
