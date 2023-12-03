//[buildless-plugin-gradle](index.md)

# buildless-plugin-gradle

[JVM (Gradle)]\
This documentation describes reference material for the [Buildless](https://less.build) plugin for [Gradle](https://gradle.org). The plugin helps with configuration of remote build caching via the Buildless API. See the [Buildless docs](https://docs.less.build) for additional info including resources, code samples, API specs, and more.

<hr />

<h3>Complete config sample</h3>

`settings.gradle.kts` **(Kotlin DSL):**

```kotlin
plugins {
  id("build.less") version "..."
}

buildless {
  // various flags:
  debug = true  // activate or deactivate debug mode (verbose logging, API tracing, etc)
  telemetry = true  // activate or deactivate private build telemetry (build analytics in the dashboard)
  reportErrors = true  // activate or deactivate error reporting to the buildless team (just plugin errors)

  // options for configuring your API key:
  apiKey("...")
  apiKey(ApiKey.of("..."))
  apiKey(ApiKey.of("...", type = SubjectType.ORG))

  // configuring local caching (it defaults to being active):
  localCache.enabled = true  // turn the local cache on or off
  localCache.directory = "..."  // set the directory where the cache is stored (defaults to .gradle/cache)

  // or...
  localCache {
    enabled = true
    directory = "..."
  }

  // configuring remote caching (it defaults to being active):
  remoteCache.enabled = true  // turn the remote cache on or off
  remoteCache.endpoint = "..."  // set a custom or enterprise cache endpoint (not usually necessary)

  // or...
  remoteCache {
      enabled = true
      endpoint = "..."
  }
}
```
<hr />

**Other resources**

- 
   [**Buildless docs**](https://docs.less.build): API docs, reference material, getting started guides
- 
   [**Code samples**](https://less.build/samples): Sample codebases in various frameworks, pre-integrated with Buildless
- 
   [**Dashboard**](https://less.build/login): Log in to obtain your API keys and review analytics
- 
   [**Contact support**](https://support.less.build): Contact the support team by chat, phone, email
- 
   [**Our website**](https://less.build): Everything else including our Terms of Service and Privacy Policy

## Packages

| Name |
|---|
| [build.less.plugin.gradle](buildless-plugin-gradle/build.less.plugin.gradle/index.md) | Main package for the Buildless plugin for Gradle. Includes all user-facing interfaces and classes. |
| [build.less.plugin.gradle.err](buildless-plugin-gradle/build.less.plugin.gradle.err/index.md) | Exceptions which are reliably thrown by the plugin, and which the developer may want to catch. |
| [build.less.plugin.gradle.service](buildless-plugin-gradle/build.less.plugin.gradle.service/index.md) |
| [org.gradle.kotlin.dsl](buildless-plugin-gradle/org.gradle.kotlin.dsl/index.md) |
