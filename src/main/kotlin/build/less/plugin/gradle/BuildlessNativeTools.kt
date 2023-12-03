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

import java.nio.file.Path

/**
 * # Native Tools
 *
 * Provides an API for native OS functions which are needed by the Buildless plugin; implementations are supplied for
 * each supported OS, and resolved via the DI context and [BuildlessNativeToolsFactory].
 */
internal interface BuildlessNativeTools {
  /**
   * Returns the path to the agent configuration rendezvous file.
   *
   * @return Path to the configuration file for this OS.
   */
  fun agentConfigPath(): Path

  /**
   * Returns the current agent config, if any, or `null`.
   *
   * @return Agent configuration or `null`.
   */
  fun loadAgentConfig(): AgentConfig?
}
