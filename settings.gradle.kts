include (":playground")
include (":ocean-components")
include (":app")
include(":baselineprofile")

rootProject.name = "ocean-android"

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://jitpack.io") }
    }
}
