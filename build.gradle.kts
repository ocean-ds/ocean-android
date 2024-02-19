buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(classpath.gradle)
        classpath(classpath.kotlin)
        classpath(classpath.baselineprofile)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}