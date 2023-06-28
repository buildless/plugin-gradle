# Contributing to the Buildless for Gradle Plugin

Hey there! Welcome to our contribution guide. We're excited that you want to contribute. This document will help you get
started building the code, and shipping a change.

#### Legalese

In order to contribute to this code, you must sign the
[Elide Contributor License Agreement](https://less.build/legal/cla). We have a robot which will help facilitate this for
you on your PR.

## Getting started

### Requirements

You will need a recent copy of Java which can run Java 11-targeted code. We recommend Java 19 or later for build speed.

### Building the plugin

The codebase is a standard Gradle project, so you can build it with the `build` task:

```
./gradlew build
```

Running the build will:

- Compile the plugin
- Run unit and integration tests
- Run static analysis tools

### Testing the plugin

You can run the tests, specifically, with:

```
./gradlew test
```

The plugin uses [Gradle TestKit](https://docs.gradle.org/current/userguide/test_kit.html) to run integration tests, and
regular JUnit 5 for unit tests.

## Contributing your change

### Filing an issue

First up, it's often a good idea to [file an issue](https://github.com/buildless/plugin-gradle/issues/new), which allows
us to guide you while developing your PR. This is a totally optional step, though! Changes are encouraged which surprise
us. We love surprises and love new code even more.

### Making your change

1. **Fork the repo.** This makes sure you have appropriate permissions to make changes.
2. **Clone your fork.** You then use your local copy to build with your changes.
3. **Make your change.** We recommend using an IDE like IntelliJ IDEA or Eclipse to make your changes. Here's how:

Branch off of `main` to conduct your changes, like:

```
git checkout -b feat/my-feature
```

It's best to use one of the following prefixes, so we can identify how your branch fits in immediately. We follow the
[Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/) specification, so you can use any of the
following prefixes:

- `feat`: It's a feature. This is for larger work which adds new functionality.
- `chore`: It's a chore. This is for smaller work, routine work, and nits/small fixes.
- `fix`: It's a fix for a bug or flaw; when using this, make sure to tag the related issue.
- `test`: It helps with testing by adding test cases or enhancing the test framework.

Ideally, you should add tests _while_ you make your change. This helps us understand the change, and helps you make sure
your change works as expected. If you're not sure how to test your change, feel free to file an issue, and we'll help
you through that part.

Once you're ready and your tests pass locally, it's time to push it up. Once you do, you can open your pull request.

### Filing your PR

We use GitHub Pull Request templates, which make it a little easier to structure a well-formed PR. We aren't _super_
crazy about formatting, but consistency helps us find stuff later when searching, and helps keep other team members up
to date about changes.

Generally, you'll want to use the following format, or something along these lines:

```
PR Title:
feat(category): added something cool

PR Body:

### Summary
Here is a summary of this PR. It's explained here, in regular language.

### Changelog
- [x] feat: add impl for cool thing
- [x] fix: fix other thing
- [ ] chore: relock dependencies (or something)
```

#### Summary section

Use this section to explain your change, your approach, etc. It's a freeform area for your use.

#### Changelog section

Use this section to keep track of changes in the PR. If you want to, you can check the tasks off as you go.

### Draft lifecycle

We recommend filing the PR as a draft initially, especially if it's a big change. We don't review or proactively close
draft PRs unless they're open for a long time. Once you're ready for review, you can mark it as ready.

## Getting reviewed

Once you've filed your PR, make sure to tag `engineering` for review. We'll take a look at it, and provide feedback. We
may ask that you make changes, or that you allow us to make changes on top of yours. We'll work with you to make sure
your change is ready to go.

### More help if you need it

You can always reach out to the team at any of the following places; our team is staffed with engineers just like you,
who are ready to answer your questions:

- **[Discord](https://less.build/discord):** Join and chat with our team directly
- **[Support Portal](https://less.build/discord):** File a ticket for larger issues or if we are offline
- **[Email](mailto:support@less.build):** `support@less.build`\*\* goes to the entire team
- **[Phone](tel:+18444201414):** `+1-844-420-1414` will dial us directly, or you can leave a message (it files a ticket)
- **In-app:** From inside your Buildless Dashboard, click the profile icon, then click _Help & Support_

Or, just file an issue. We're happy to help!

## Thank you!

Overall, we're much happier about your change existing even if it isn't "up to snuff." Thanks for using Buildless and
we're excited to work with you.
