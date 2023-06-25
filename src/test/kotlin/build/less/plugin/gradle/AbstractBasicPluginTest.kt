package build.less.plugin.gradle

import org.gradle.testkit.runner.GradleRunner

/** Tests that the plugin does not break the build by being applied at all. */
abstract class AbstractBasicPluginTest(kotlin: Boolean) : AbstractOneShotPluginTest(kotlin) {
  override fun GradleRunner.runner() {
    withArguments("tasks")
    withPluginClasspath()
  }
}
