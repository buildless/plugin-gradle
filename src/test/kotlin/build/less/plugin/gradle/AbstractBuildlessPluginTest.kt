@file:Suppress("unused")

package build.less.plugin.gradle

import org.gradle.testkit.runner.BuildResult
import org.gradle.testkit.runner.GradleRunner
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.io.TempDir
import java.io.File

/** Abstract test logic for plugin testing via Gradle TestKit. */
abstract class AbstractBuildlessPluginTest(private val kotlin: Boolean = true) {
  @TempDir lateinit var testProjectDir: File
  private lateinit var settingsFile: File
  private lateinit var buildFile: File

  private fun scriptExtension(): String = if (kotlin) "gradle.kts" else "gradle"

  /** Configured test case returned after specifying a runner. */
  inner class ConfiguredTestCase(private val runner: GradleRunner) {
    /**
     * Fill contents of the `settings.gradle.kts` or `settings.gradle` file.
     *
     * @param producer Producer of file contents.
     */
    fun withSettings(producer: () -> String): ConfiguredTestCase = apply {
      settingsFile.writeText(producer.invoke().trimIndent().trim())
    }

    /**
     * Fill contents of the `build.gradle.kts` or `build.gradle` file.
     *
     * @param producer Producer of file contents.
     */
    fun withBuild(producer: () -> String): ConfiguredTestCase = apply {
      buildFile.writeText(producer.invoke())
    }

    /**
     * Run a build against the configured Gradle [runner], and then perform the provided [block] of assertions based on
     * the result.
     *
     * Builds executed via this method are expected to succeed.
     *
     * @param block Block of assertions to run on the result of the build operation.
     */
    fun withSuccess(block: (BuildResult) -> Unit): Unit = runner.build().let { result ->
      block.invoke(result)
    }

    /**
     * Run a build against the configured Gradle [runner]. Builds executed via this method are expected to succeed.
     */
    fun doesNotFail() {
      runner.build()
    }

    /**
     * Run a build against the configured Gradle [runner], and then perform the provided [block] of assertions based on
     * the result.
     *
     * Builds executed via this method are expected to fail.
     *
     * @param block Block of assertions to run on the result of the build operation.
     */
    fun withFailure(block: (BuildResult) -> Unit): Unit = runner.buildAndFail().let { result ->
      block.invoke(result)
    }

    /**
     * Run a build against the configured Gradle [runner]. Builds executed via this method are expected to fail.
     */
    fun fails() {
      runner.buildAndFail()
    }
  }

  @BeforeEach open fun setup() {
    settingsFile = File(testProjectDir, "settings.${scriptExtension()}")
    buildFile = File(testProjectDir, "build.${scriptExtension()}")
  }

  /**
   * Design a Gradle TestKit case via the provided [builder].
   *
   * After a test has been configured via the [builder], a thin interface is returned where assertions can be made. The
   * Gradle project under test is (1) configured with a temporary directory which is cleared on each test, and (2) with
   * the applicable plugin classpath under test.
   *
   * @param builder Test case builder; runs in the context of the [GradleRunner].
   * @return Configured test case; it is up to the developer to assert further.
   */
  protected fun runner(builder: GradleRunner.() -> Unit): ConfiguredTestCase = ConfiguredTestCase(
    GradleRunner.create()
      .withPluginClasspath()
      .withProjectDir(testProjectDir).apply { builder.invoke(this) }
  )
}
