package build.less.plugin.gradle
import org.junit.jupiter.api.Test

/** Make sure the plugin rejects invalid API keys. */
class ConfigRejectInvalidApiKey : AbstractBuildlessPluginTest() {
  @Test fun `plugin should reject malformed API keys`() = runner {
    withArguments("tasks")
  }.withSettings {
    // language=kts
    """
    plugins {
      id("build.less")
    }

    buildless {
      apiKey("totally malformed key")
    }
    """
  }.fails()

  @Test fun `plugin should reject invalid API key prefix`() = runner {
    withArguments("tasks")
  }.withSettings {
    // language=kts
    """
    plugins {
      id("build.less")
    }

    buildless {
      apiKey("bunk_ABC123ABC123ABC123ABC123ABC123ABC123ABC123ABC123")
    }
    """
  }.fails()

  @Test fun `plugin should reject API key prefix mismatch`() = runner {
    withArguments("tasks")
  }.withSettings {
    // language=kts
    """
    import build.less.plugin.gradle.ApiKey.SubjectType

    plugins {
      id("build.less")
    }

    buildless {
      apiKey("org_ABC123ABC123ABC123ABC123ABC123ABC123ABC123ABC123", type = SubjectType.USER)
    }
    """
  }.fails()
}
