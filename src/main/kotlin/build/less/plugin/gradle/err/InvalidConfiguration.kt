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
) : BuildlessPluginException(message, cause) {
  /**
   * Unique error code for this error case.
   */
  public abstract val code: String
}
