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
import org.gradle.api.services.BuildService
import org.gradle.api.services.BuildServiceParameters
import java.util.concurrent.atomic.AtomicReference

/**
 * # Buildless for Gradle: Service
 *
 * This service implementation provides a dependency-injected way to access API clients for the Buildless service, from
 * within a Gradle build.
 */
@API public class BuildlessService : BuildService<BuildlessService.Params>, AutoCloseable {
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
