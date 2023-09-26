plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = Configs.compileSdkVersion

    defaultConfig {
        applicationId = "br.com.useblu.oceands.client"
        minSdk = Configs.minSdkVersion
        targetSdk = Configs.targetSdkVersion
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Configs.kotlinCompilerExtensionVersion
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    namespace = "br.com.useblu.oceands.client"
}

dependencies {
    implementation(project(":ocean-components"))
    implementation(Libraries.lifecycleViewModel)

    implementation(Libraries.constraintLayout)
    implementation(Libraries.lifecycleExtensions)
    implementation(Libraries.toggleButtonLayout)

    // COMPOSE
    val composeBom = platform(Libraries.composeBOM)
    implementation(composeBom)
    androidTestImplementation(composeBom)

    implementation(Libraries.androidMaterial3)
    implementation(Libraries.composeUiToolingPreview)
    implementation(Libraries.composeUiViewBinding)
    debugImplementation(Libraries.composeUiTooling)
    androidTestImplementation(Libraries.composeUiTestJunit4)
    debugImplementation(Libraries.composeUiTestManifest)
    implementation(Libraries.activityCompose)
    implementation(Libraries.lifecycleViewModelCompose)
    implementation(Libraries.composeRuntimeLiveData)
}