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

/**
 * # Buildless for Gradle: Cache Transport
 *
 * Enumerates the types of transport engines which are available for use when interacting with Buildless. By default,
 * the [STANDARD] transport is used, which leverages Gradle's built-in remote build caching over HTTP.
 */
public enum class CacheTransport {
  /**
   * Use the built-in cache transport mechanism from Gradle.
   */
  STANDARD
}
