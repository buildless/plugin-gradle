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

package build.less.plugin.gradle.project

import build.less.plugin.gradle.core.API
import build.less.plugin.gradle.project.BuildlessProjectExtension.ProjectCondition
import org.gradle.api.Project
import java.util.*
import java.util.concurrent.atomic.AtomicReference
import javax.inject.Inject

/**
 * # Buildless for Gradle: Project Extension
 *
 * Adds project-level configuration features to Gradle projects which are enabled with Buildless, via the main settings
 * time plugin.
 */
@API internal class BuildlessProjectExtension @Inject constructor () {
  /** Known plugin IDs which can be configured by Buildless. */
  private data object KnownPluginID {
    const val ANDROID_LIBRARY = "com.android.library"
    const val ANDROID_APPLICATION = "com.android.application"
    const val DOCKER = "com.bmuschko.docker-remote-api"
    const val MICRONAUT_LIBRARY = "io.micronaut.library"
    const val MICRONAUT_APPLICATION = "io.micronaut.application"
    const val MICRONAUT_AOT = "io.micronaut.aot"
    const val MICRONAUT_GRAALVM = "io.micronaut.graalvm"
    const val SHADOW = "com.github.johnrengelman.shadow"
  }

  /** Known plugin task names. */
  private data object PluginTask {
    const val SHADOW_JAR = "shadowJar"
    const val DOCKER_BUILD_LAYERS = "buildLayers"
    const val AOT_BUILD_OPTIMIZED_LAYERS = "optimizedBuildLayers"
    const val AOT_BUILD_OPTIMIZED_NATIVE_LAYERS = "optimizedBuildNativeLayersTask"
    const val OPTIMIZED_JIT_JAR_ALL = "optimizedJitJarAll"
  }

  /** Describes a project condition applying to a [ProjectConfigurator]. */
  @FunctionalInterface private fun interface ProjectCondition {
    /**
     * Determine if the given project is eligible for configuration.
     *
     * @param project Project to check.
     * @return `true` if the project is eligible for configuration, `false` otherwise.
     */
    fun eligible(project: Project): Boolean
  }

  /** Describes an action which takes place on a project if a [ProjectCondition] matches. */
  @FunctionalInterface private fun interface ProjectConfigurator {
    fun applyPolicy(utils: ProjectUtilities, project: Project) {
      utils.execute(project)
    }

    fun ProjectUtilities.execute(project: Project)
  }

  /** Known plugins which can be configured by Buildless. */
  private enum class KnownPlugin (vararg ids: String) : ProjectCondition {
    // Android family of plugins.
    ANDROID(KnownPluginID.ANDROID_LIBRARY, KnownPluginID.ANDROID_APPLICATION),

    // Docker builds.
    DOCKER(KnownPluginID.DOCKER),

    // Micronaut family of plugins.
    MICRONAUT(KnownPluginID.MICRONAUT_LIBRARY, KnownPluginID.MICRONAUT_APPLICATION),

    // Micronaut AOT.
    MICRONAUT_AOT(KnownPluginID.MICRONAUT_AOT),

    // Micronaut plugin for GraalVM Native Image.
    MICRONAUT_GRAALVM(KnownPluginID.MICRONAUT_GRAALVM),

    // Shadow plugin.
    SHADOW(KnownPluginID.SHADOW);

    private val ids: SortedSet<String> = sortedSetOf(*ids)

    override fun eligible(project: Project): Boolean = ids.any {
      project.plugins.hasPlugin(it)
    }
  }

  /** Interface provided by a [KnownReason], and, by extension, a [DisabledReason]. */
  private interface Reasoning {
    /** Indicates whether reasoning is known for a disablement. */
    val known: Boolean

    /** Describes why the item was disabled. */
    val description: String
  }

  /** Enumerates known reasons why something might be disabled by default for caching. */
  private enum class KnownReason (override val description: String): Reasoning {
    TOO_BIG("Task has large outputs which are generally too big to cache."),
    BUGGY("Task or task outputs have known conflicts or issues with build caching.");

    override val known: Boolean get() = true  // always known
  }

  /** Describes why a task was disabled for caching by Buildless, either because of a [KnownReason] or custom one. */
  @JvmInline private value class DisabledReason(private val knownOrCustom: Pair<KnownReason?, String?>): Reasoning {
    override val known: Boolean get() = knownOrCustom.first?.known ?: false
    override val description: String get() = knownOrCustom.second ?: knownOrCustom.first?.description ?: "unknown"

    companion object {
      @JvmStatic fun known(reason: KnownReason): DisabledReason = DisabledReason(reason to null)
      @JvmStatic fun because(reason: String): DisabledReason = DisabledReason(null to reason)

      /** The object is generally too big to cache, or too big to be profitable for caching. */
      val TOO_BIG = known(KnownReason.TOO_BIG)

      /** The tasks have known issues related to caching. */
      val BUGGY = known(KnownReason.BUGGY)
    }
  }

  /** Utility method context for project configurators. */
  private inner class ProjectUtilities (initial: Project) {
    // Keeps track of tasks which have already been disabled.
    private val disabledTasks = TreeSet<String>()

    // Active project for these utilities for a given policy application.
    private val activeProject: AtomicReference<Project> = AtomicReference(
      initial
    )

    private val targetProject get() = activeProject.get()

    fun withProject(project: Project) {
      activeProject.set(project)
    }

    fun withProject(project: Project, block: ProjectUtilities.() -> Unit) {
      withProject(project)
      block()
    }

    /**
     * Disable the provided tasks for caching on a given project.
     *
     * @param reason Reason for disabling the tasks.
     * @param names Names of tasks to disable.
     */
    fun disableCaching(reason: DisabledReason, vararg names: String) {
      // filter already-disabled tasks, and for each, disable state tracking
      names.filter { !disabledTasks.contains(it) }.mapNotNull {
        targetProject.tasks.findByName(it)
      }.forEach {
        it.doNotTrackState(reason.description)
      }
    }

    /** The task output is generally too big to be profitable for caching. */
    @Suppress("unused") fun tooBig(): DisabledReason = DisabledReason.TOO_BIG

    /** The task outputs have known bugs related to caching. */
    @Suppress("unused") fun buggy(): DisabledReason = DisabledReason.BUGGY

    /** Custom reasoning for a disablement. */
    @Suppress("unused") fun because(reason: String) = DisabledReason.because(reason)
  }

  @JvmInline private value class CompoundFilter(
    private val filters: Pair<Boolean, Array<out ProjectCondition>>,
  ): ProjectCondition {
    private val strict: Boolean get() = filters.first

    override fun eligible(project: Project): Boolean = when (strict) {
      true -> filters.second.all { it.eligible(project) }
      else -> filters.second.any { it.eligible(project) }
    }
  }

  /** Project filter with a [condition] and [configurator]. */
  @JvmInline private value class BuildlessProjectFilter(
    private val pair: Pair<ProjectConfigurator, ProjectCondition>,
  ): ProjectCondition {
    val condition: ProjectCondition get() = pair.second
    val configurator: ProjectConfigurator get() = pair.first

    override fun eligible(project: Project): Boolean = condition.eligible(project)
  }

  @Suppress("unused") private companion object {
    @JvmStatic fun allOf(vararg conditions: ProjectCondition): ProjectCondition =
      CompoundFilter(true to conditions)

    @JvmStatic fun anyOf(vararg conditions: ProjectCondition): ProjectCondition =
      CompoundFilter(false to conditions)

    // Run a configurator on a project if it has the supplied plugin.
    @JvmStatic fun whenAllApplied(vararg plugins: KnownPlugin, configure: ProjectConfigurator): BuildlessProjectFilter =
      BuildlessProjectFilter(configure to allOf(*plugins))

    // Run a configurator on a project if it has the supplied plugin.
    @JvmStatic fun whenApplied(vararg plugins: KnownPlugin, configure: ProjectConfigurator): BuildlessProjectFilter =
      BuildlessProjectFilter(configure to anyOf(*plugins))

    // Run a configurator on a project if it matches a property condition.
    @JvmStatic fun whenPropertyEquals(
      property: String,
      configure: ProjectConfigurator,
      condition: (Any?) -> Boolean = { it != null },
    ): BuildlessProjectFilter = BuildlessProjectFilter(configure to ProjectCondition {
      // only configure if the plugin has been applied
      it.findProperty(property)?.let(condition) ?: false
    })

    // All policies which should be applied.
    private val projectPolicies = listOf(
      whenApplied(KnownPlugin.MICRONAUT, KnownPlugin.DOCKER) {
        disableCaching(tooBig(), PluginTask.DOCKER_BUILD_LAYERS)
      },
      whenApplied(KnownPlugin.MICRONAUT_AOT) {
        disableCaching(tooBig(), PluginTask.AOT_BUILD_OPTIMIZED_LAYERS)
      },
      whenAllApplied(KnownPlugin.MICRONAUT_AOT, KnownPlugin.MICRONAUT_GRAALVM) {
        disableCaching(tooBig(), PluginTask.AOT_BUILD_OPTIMIZED_NATIVE_LAYERS)
      },
      whenAllApplied(KnownPlugin.MICRONAUT_AOT, KnownPlugin.SHADOW) {
        disableCaching(tooBig(), PluginTask.OPTIMIZED_JIT_JAR_ALL)
      },
      whenApplied(KnownPlugin.SHADOW) {
        disableCaching(tooBig(), PluginTask.SHADOW_JAR)
      },
    )
  }

  // Initialize the project extension.
  internal fun initialize(target: Project) = ProjectUtilities(target).let { utils ->
    // apply policies to the project
    projectPolicies.forEach { policy ->
      if (policy.condition.eligible(target)) utils.apply {
        withProject(target) {
          policy.configurator.applyPolicy(this, target)
        }
      }
    }
  }
}
