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
 * # Plugin Exception: Base
 *
 * Describes an abstract base exception from which all Buildless plugin exceptions inherit; this type may be caught to
 * handle all plugin exceptions.
 *
 * @param message Error message to specify (optional).
 * @param cause Underlying error cause (optional).
 */
public sealed class BuildlessPluginException(
  message: String? = null,
  cause: Throwable? = null,
) : RuntimeException(message, cause)
