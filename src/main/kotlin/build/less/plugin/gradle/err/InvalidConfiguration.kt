/* Copyright (c) 2023 Elide Ventures LLC
 *
 * This is private computer source code. This code is part of an application which is licensed privately, as part of
 * intellectual property owned by Elide Ventures, LLC. All rights are reserved. Viewing and editing this code implies
 * agreement with the Elide Non-Disclosure Agreement and Elide Inventions Assignment Agreement.
 *
 * Code bearing this header may not be shared outside of authorized circumstances without prior written consent from
 * authorized corporate officers of Elide Ventures, LLC.
 */

package build.less.plugin.gradle.err

/**
 * # Plugin Exception: Invalid Configuration
 *
 * Describes an abstract exception base for errors which originate from invalid configuration, or an illegal state
 * expressed by the user when configuring the plugin. These errors are terminal in the sense that they must be remedied
 * by a user in order to be fixed.
 *
 * @param message Error message to specify (optional).
 * @param cause Underlying error cause (optional).
 */
public abstract class InvalidConfiguration(
  message: String? = null,
  cause: Throwable? = null,
) : BuildlessPluginException(message, cause)
