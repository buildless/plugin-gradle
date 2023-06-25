package build.less.plugin.gradle

import org.gradle.testkit.runner.TaskOutcome.SUCCESS
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/** Test that the Groovy DSL plugin doesn't break the build. */
class GroovyDslBasicPluginTest : AbstractBasicPluginTest(kotlin = false) {
  override fun `gradle-settings`(): StringBuilder = StringBuilder().append(
    // language=groovy
    """
    plugins {
     id 'build.less'
    }
    
    buildless {
      // nothing to do
    }
    """.trimIndent()
  )

  override fun `gradle-build`(): StringBuilder = StringBuilder().append(
    // language=groovy
    """
    // nothing to do
    """.trimIndent()
  )

  @Test fun `plugin should apply without error`() = withResult { result ->
    assertEquals(SUCCESS, result.task(":tasks")?.outcome)
  }
}
