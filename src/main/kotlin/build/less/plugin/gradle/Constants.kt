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
