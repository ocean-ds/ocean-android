buildscript {
    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://jitpack.io") }
    }

    dependencies {
        classpath(classpath.gradle)
        classpath(classpath.kotlin)
    }
}

allprojects {
    repositories {
        google()
        maven { setUrl("https://jitpack.io") }
        maven { setUrl("https://jcenter.bintray.com") }
        maven {
            name = "github-packages-maven-repo"
            url = uri("${Configs.gprBaseUrl}/${Configs.gprRepoOwner}/${Configs.gprRepoId}")

            credentials {
                username = Configs.gprUser
                password = Configs.gprApiKey
            }
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}