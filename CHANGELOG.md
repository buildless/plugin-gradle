## Buildless for Gradle: Changelog

### `1.0.0-beta8`: December 2nd, 2023

This release of the Buildless Plugin for Gradle fixes several issues with Windows support, and other known bugs. All
users are encouraged to upgrade.

### `1.0.0-beta5`: December 1st, 2023

This release of the Buildless Plugin for Gradle fixes several issues with Windows support, and other known bugs. All
users are encouraged to upgrade.

### `1.0.0-beta4`: November 29th, 2023

Patch release to `1.0.0-beta3` (release notes copied/enclosed), which aligns local cache settings with their expected
state. By default, local caching is disabled when using the Buildless plugin, because it is handled by the Buildless
Agent.

Added support for the Buildless Agent, which is automatically detected and used when running. Added support for the
Groovy DSL, and there is no need to import symbols to use Buildless from the Kotlin DSL.

#### New features

- **Agent:** Transparent/automatic support for the Buildless Agent
- **Groovy DSL:** Support for Gradle Groovy DSL

#### Bugfixes

- **No need for imports:** `buildless { }` and other symbols no longer need to be imported.

### `1.0.0-beta3`: November 28th, 2023

Patch release to `1.0.0-beta2` (release notes copied/enclosed), which aligns local cache settings with their expected
state. By default, local caching is disabled when using the Buildless plugin, because it is handled by the Buildless
Agent.

Added support for the Buildless Agent, which is automatically detected and used when running. Added support for the
Groovy DSL, and there is no need to import symbols to use Buildless from the Kotlin DSL.

#### New features

- **Agent:** Transparent/automatic support for the Buildless Agent
- **Groovy DSL:** Support for Gradle Groovy DSL

#### Bugfixes

- **No need for imports:** `buildless { }` and other symbols no longer need to be imported.

### `1.0.0-beta2`: November 28th, 2023

Added support for the Buildless Agent, which is automatically detected and used when running. Added support for the
Groovy DSL, and there is no need to import symbols to use Buildless from the Kotlin DSL.

#### New features

- **Agent:** Transparent/automatic support for the Buildless Agent
- **Groovy DSL:** Support for Gradle Groovy DSL

#### Bugfixes

- **No need for imports:** `buildless { }` and other symbols no longer need to be imported.

### `1.0.0-beta1`: June 25th, 2023

Initial release of the plugin to GitHub, GitHub Packages, and Maven Central. Currently pending approval for the Gradle
plugin portal.

Features include:

- **Setup Gradle local caching** just like with Gradle's DSL
- **Setup Gradle remote caching** just like with Gradle's DSL
- **Pre-wired with Buildless** configuration details, like the cache endpoint
- **Resolves API keys from local environment** or properties
