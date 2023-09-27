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
    implementation(libs.lifecycleViewModel)

    implementation(libs.constraintLayout)
    implementation(libs.lifecycleExtensions)
    implementation(libs.toggleButtonLayout)

    // COMPOSE
    val composeBom = platform(libs.compose.bom)
    implementation(composeBom)
    androidTestImplementation(composeBom)

    implementation(libs.compose.androidMaterial3)
    implementation(libs.compose.uiToolingPreview)
    implementation(libs.compose.uiViewBinding)
    debugImplementation(libs.compose.uiTooling)
    androidTestImplementation(libs.compose.uiTestJunit4)
    debugImplementation(libs.compose.uiTestManifest)
    implementation(libs.compose.activity)
    implementation(libs.compose.lifecycleViewModel)
    implementation(libs.compose.runtimeLiveData)
}