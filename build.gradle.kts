buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(libs.gradle)
        classpath(libs.kotlin.gradle.plugin)
        classpath(libs.benchmark.baseline.profile.gradle.plugin)
    }
}

plugins {
    alias(libs.plugins.compose.compiler) apply false
}