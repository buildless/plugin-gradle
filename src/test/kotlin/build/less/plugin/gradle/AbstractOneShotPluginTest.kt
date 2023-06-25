package build.less.plugin.gradle

import org.gradle.testkit.runner.BuildResult
import org.gradle.testkit.runner.GradleRunner
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.io.TempDir
import java.io.File

/** Abstract test logic for plugin testing via Gradle TestKit. */
abstract class AbstractOneShotPluginTest(private val kotlin: Boolean = false) {
  @TempDir lateinit var testProjectDir: File
  private lateinit var settingsFile: File
  private lateinit var buildFile: File

  private fun scriptExtension(): String = if (kotlin) "gradle.kts" else "gradle"

  @BeforeEach open fun setup() {
    settingsFile = File(testProjectDir, "settings.${scriptExtension()}").apply {
      `gradle-settings`()?.let {
        writeText(it.toString())
      }
    }
    buildFile = File(testProjectDir, "build.${scriptExtension()}").apply {
      `gradle-build`()?.let {
        writeText(it.toString())
      }
    }
  }

  /**
   * Interrogated by the base test class to fill the `settings.gradle` or `setting.gradle.kts` file, depending on
   * whether [kotlin] was passed as `true`.
   *
   * @return String builder with content for the Gradle settings file; subclasses can return `null` to opt-out of the
   *   presence of a settings file entirely.
   */
  abstract fun `gradle-settings`(): StringBuilder?

  /**
   * Interrogated by the base test class to fill the root `build.gradle` or `build.gradle.kts` file, depending on
   * whether [kotlin] was passed as `true`.
   *
   * @return String builder with content for the Gradle build file; subclasses can return `null` to opt-out of the
   *   presence of a settings file entirely.
   */
  abstract fun `gradle-build`(): StringBuilder?

  /**
   * Invoked by the base class in order to configure the Gradle runner for use with this test case; the runner should be
   * configured via the receiver context.
   *
   * @receiver Gradle runner.
   */
  abstract fun GradleRunner.runner()

  /**
   * Obtain the result for the Gradle build described by this class, then execute [testCase] with it.
   *
   * This function is a utility for executing assertions after the Gradle under test build has been configured.
   *
   * @param testCase Test case assertions to run.
   */
  protected fun withResult(testCase: (BuildResult) -> Unit) {
    val runner = GradleRunner.create().withProjectDir(testProjectDir)
    runner.runner()
    runner.build().let { result ->
      testCase.invoke(result)
    }
  }
}
