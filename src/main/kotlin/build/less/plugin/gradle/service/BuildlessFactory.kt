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

package build.less.plugin.gradle.service

import build.less.plugin.gradle.AgentConfig

/**
 * # Buildless Factory
 *
 * Specifies extended method support for the Buildless Service factory.
 */
public interface BuildlessFactory {
  /**
   * Retrieve the active agent configuration, if any.
   *
   * The first time this method is called, the configuration is found, read, and loaded from JSON; subsequent calls use
   * a cached version.
   *
   * @return Active and decoded agent configuration, or `null`, if none exists.
   */
  public fun activeAgent(): AgentConfig?
}