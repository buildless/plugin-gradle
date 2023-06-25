/* Copyright (c) 2023 Elide Ventures LLC
 *
 * This is private computer source code. This code is part of an application which is licensed privately, as part of
 * intellectual property owned by Elide Ventures, LLC. All rights are reserved. Viewing and editing this code implies
 * agreement with the Elide Non-Disclosure Agreement and Elide Inventions Assignment Agreement.
 *
 * Code bearing this header may not be shared outside of authorized circumstances without prior written consent from
 * authorized corporate officers of Elide Ventures, LLC.
 */

@file:Suppress("unused")

package build.less.plugin.gradle

/** Constants used as part of the Buildless plugin for Gradle. */
internal object Constants {
  /** Link to the Buildless docs. */
  internal const val DOCS_LINK: String = "https://docs.less.build"

  /** Link to the main Buildless site. */
  internal const val SITE_LINK: String = "https://less.build"

  /** Link to the Buildless dashboard. */
  internal const val DASH_LINK: String = "https://beta.less.build"

  /** Link to Buildless support. */
  internal const val SUPPORT_LINK: String = "https://less.build/support"

  /** Link to Buildless code samples. */
  internal const val SAMPLES_LINK: String = "https://less.build/samples"

  /** Default API endpoint to use for the remote cache. */
  internal const val DEFAULT_CACHE_ENDPOINT: String = "https://gradle.less.build/cache/generic/"

  /** Describes specific Buildless Support contact information/methods. */
  internal object Support {
    /** Email address for Buildless Support. */
    internal const val EMAIL = "support@less.build"

    /** Phone number for Buildless Support. */
    internal const val PHONE = "+1-844-420-1414"
  }
}
