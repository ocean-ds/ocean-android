include (":playground")
include (":ocean-components")
include (":app")
rootProject.name = "ocean-android"

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://jitpack.io") }
    }

    versionCatalogs {
        create("classpath") {
            library("gradle", "com.android.tools.build:gradle:8.1.4")
            library("kotlin", "org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.20")
        }

        create("libs") {
            library("constraintLayout", "androidx.constraintlayout:constraintlayout:2.1.4")
            library("lifecycleExtensions", "androidx.lifecycle:lifecycle-extensions:2.2.0")

            version("lifecycleViewModel", "2.6.1")
            library("lifecycleViewModel", "androidx.lifecycle", "lifecycle-viewmodel-ktx").versionRef("lifecycleViewModel")

            library("androidKtx", "androidx.core:core-ktx:1.12.0")
            library("androidAppCompat", "androidx.appcompat:appcompat:1.6.1")

            library("skydovesBalloon", "com.github.skydoves:balloon:1.6.3")

            library("androidMaterial", "com.google.android.material:material:1.10.0")

            library("canarinho", "com.github.concretesolutions:canarinho:2.0.2")

            library("glide", "com.github.bumptech.glide:glide:4.16.0")

            library("circleIndicator", "me.relex:circleindicator:2.1.6")

            library("imageCarousel", "org.imaginativeworld.whynotimagecarousel:whynotimagecarousel:2.0.4")

            library("expansionPanel", "com.github.florent37:expansionpanel:1.2.4")

            library("toggleButtonLayout", "com.github.savvyapps:ToggleButtonLayout:1.3.0")

            library("calendarView", "com.github.prolificinteractive:material-calendarview:2.0.1")

            library("mpChart", "com.github.PhilJay:MPAndroidChart:v3.1.0")

            library("skeletonBones", "com.github.EudyContreras:Skeleton-Bones:v1.4.5")

            library("skydovesLandscapist", "com.github.skydoves:landscapist-glide:2.2.12")

            // COMPOSE DEPENDENCIES
            library("compose-lifecycleViewModel", "androidx.lifecycle", "lifecycle-viewmodel-compose").versionRef("lifecycleViewModel")
            library("compose-activity", "androidx.activity:activity-compose:1.8.0")

            library("compose-BOM", "androidx.compose:compose-bom:2023.10.01")
            library("compose-androidMaterial3", "androidx.compose.material3", "material3").withoutVersion()
            library("compose-uiToolingPreview", "androidx.compose.ui", "ui-tooling-preview").withoutVersion()
            library("compose-uiViewBinding", "androidx.compose.ui", "ui-viewbinding").withoutVersion()
            library("compose-uiTooling", "androidx.compose.ui", "ui-tooling").withoutVersion()
            library("compose-uiTestJunit4", "androidx.compose.ui", "ui-test-junit4").withoutVersion()
            library("compose-uiTestManifest", "androidx.compose.ui", "ui-test-manifest").withoutVersion()
            library("compose-runtimeLiveData", "androidx.compose.runtime", "runtime-livedata").withoutVersion()
        }
    }
}