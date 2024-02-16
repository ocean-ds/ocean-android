buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(classpath.gradle)
        classpath(classpath.kotlin)
        classpath("androidx.benchmark:benchmark-baseline-profile-gradle-plugin:1.2.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}