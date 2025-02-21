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
    alias(libs.plugins.ktlint)
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    ktlint {
        outputToConsole = true
        outputColorName = "RED"
        ignoreFailures = false
        baseline = file("baseline.xml")
        additionalEditorconfig.set(
            mapOf(
                "max_line_length" to "250",
                "ktlint_standard_function-signature" to "disabled",
                "ij_kotlin_allow_trailing_comma" to "false",
                "ij_kotlin_allow_trailing_comma_on_call_site" to "false",
                "ktlint_function_signature_body_expression_wrapping" to "multiline"
            )
        )
        filter {
            exclude("**/baseline.xml")
        }
    }
}

tasks.prepareKotlinBuildScriptModel {
    finalizedBy(":addKtlintFormatGitPreCommitHook")
}
