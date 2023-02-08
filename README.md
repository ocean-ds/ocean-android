<h1 align="center">Ocean Android</h1>

<p align="center">
  <a href="https://github.com/ocean-ds/ocean-android/actions">
    <img alt="Actions Status" src="https://github.com/ocean-ds/ocean-android/workflows/CI/badge.svg">
  </a>
  <a href="https://sonarcloud.io/dashboard?id=ocean-ds_ocean-android">
    <img alt="Sonarcloud Status" src="https://sonarcloud.io/api/project_badges/measure?project=ocean-ds_ocean-android&metric=alert_status">
  </a>
  <a href="https://github.com/ocean-ds/ocean-android/blob/master/LICENSE">
    <img alt="GitHub License" src="https://img.shields.io/github/license/ocean-ds/ocean-android">
  </a>
  <a href="https://github.com/ocean-ds/ocean-android/graphs/commit-activity">
    <img alt="GitHub last commit" src="https://img.shields.io/github/last-commit/ocean-ds/ocean-android">
  </a>
  <a href="https://github.com/ocean-ds/ocean-android/network/updates">
    <img alt="Dependabot" src="https://img.shields.io/badge/Dependabot-enabled-brightgreen">
  </a>
  <a href="https://conventionalcommits.org">
    <img alt="Conventional Commits" src="https://img.shields.io/badge/Conventional%20Commits-1.0.0-yellow.svg">
  </a>
  <a href="http://makeapullrequest.com">
    <img alt="Conventional Commits" src="https://img.shields.io/badge/PRs-welcome-brightgreen.svg">
  </a>
  <a href="https://discord.gg/hDWzGPKuG2">
    <img alt="Discord Server" src="https://img.shields.io/discord/875438252172136468.svg?color=7389D8&labelColor=6A7EC2&logo=discord&logoColor=ffffff">
  </a>  
</p>

This project provides an UIKit for android that implements the [Ocean](https://zeroheight.com/9c9b2b3aa/p/257272-ocean-ds/t/968532) design system.

## Installation

```
    maven {
        name = "github-packages-maven-repo"
        url = uri("https://maven.pkg.github.com/ocean-ds/ocean-ds-android")

        credentials {
            username = System.getenv("GPR_USER")
            password = System.getenv("GPR_API_KEY")
           }
        }
```

## Usage

```
implementation br.com.useblu:ocean-components:LAST_VERSION
```

## Contributing

Whether you're helping us fix bugs, improve the docs, or spread the word, we'd love to have you as part of this project! :blue_heart: Read below to learn how you can take part of it.

### Code of Conduct

We adopted a Code of Conduct that we expect project participants to adhere to. Please read [the full text](.github/CODE_OF_CONDUCT.md) so that you can understand what actions will and will not be tolerated.

### Contributing Guide

Read our [contributing guide](.github/CONTRIBUTING.md) to learn about our development process, how to propose bugfixes and improvements, and how to build and test your changes.

### Good First Issues

To help you get your feet wet and get you familiar with our contribution process, we have a list of [good first issues](https://github.com/ocean-ds/ocean-android/labels/good%20first%20issue) that contain bugs which have a relatively limited scope. This is a great place to get started.

## Join our community

Join our [discord server](https://discord.gg/hDWzGPKuG2) to get the latest updates, chat with other Ocean enthusiasts, and see what's happening in the community!

## License

All packages are licensed under the terms of the [GPL-3.0 License](LICENSE)
