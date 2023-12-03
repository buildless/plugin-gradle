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
import java.util.concurrent.atomic.AtomicReference
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream

/**
 * # Native Tools Factory
 *
 * Prepares [BuildlessNativeTools] singletons based on the current operating system.
 */
internal class BuildlessNativeToolsFactory {
  companion object {
    private const val AGENT_CONFIG_PATH_UNIX = "/var/tmp/buildless/buildless-agent.json"
    private const val AGENT_CONFIG_PATH_WINDOWS = "C:\\ProgramData\\buildless\\buildless-agent.json"

    // Global native tools singleton.
    private val singleton: AtomicReference<BuildlessNativeTools> = AtomicReference(null)

    @JvmStatic private fun create(): BuildlessNativeTools = System.getProperty("os.name").lowercase().trim().let {
      when {
        it.contains("windows") -> WindowsNativeTools()
        else -> UnixLikeNativeTools()
      }
    }

    /**
     * Obtains the native tools singleton for the current OS.
     *
     * The singleton will be created the first time this is called, and then cached for subsequent calls.
     *
     * @return Native tool singleton.
     */
    @JvmStatic fun obtain(): BuildlessNativeTools = singleton.get() ?: create().also { singleton.set(it) }
  }

  // Baseline native tools implementation class.
  private abstract class BuildlessNativeOSTools (
    path: String,
    private val configPath: Path = Path.of(path),
  ) : BuildlessNativeTools {
    override fun agentConfigPath(): Path = configPath

    @OptIn(ExperimentalSerializationApi::class)
    override fun loadAgentConfig(): AgentConfig? = configPath.toFile().let { file ->
      if (file.exists() && file.canRead()) {
        file.inputStream().buffered().use {
          Json.decodeFromStream(AgentConfig.serializer(), it)
        }
      } else null
    }
  }

  /** Native tools implementation class for Windows. */
  private class WindowsNativeTools : BuildlessNativeTools, BuildlessNativeOSTools(AGENT_CONFIG_PATH_WINDOWS)

  /** Native tools implementation class for Darwin and Linux. */
  private class UnixLikeNativeTools : BuildlessNativeTools, BuildlessNativeOSTools(AGENT_CONFIG_PATH_UNIX)
}
