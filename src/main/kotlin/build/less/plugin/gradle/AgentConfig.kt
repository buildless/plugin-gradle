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

import kotlinx.serialization.Serializable

/**
 * # Agent Configuration
 *
 * Describes the structure of a local agent configuration rendezvous file.
 */
@Serializable public data class AgentConfig(
  val pid: Int,
  val port: Int,
  val socket: String? = null,
  val control: AgentEndpoint? = null,
) {
  /** Describes a single agent endpoint. */
  @Serializable public data class AgentEndpoint(
    val port: Int,
    val socket: String? = null,
  )
}
