buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(classpath.gradle)
        classpath(classpath.kotlin)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}