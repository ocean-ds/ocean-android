plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = Configs.compileSdkVersion

    defaultConfig {
        applicationId = "br.com.useblu.oceands.playground"
        minSdk = Configs.minSdkVersion
        targetSdk = Configs.targetSdkVersion
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    namespace = "br.com.useblu.oceands.playground"
}

dependencies {
    implementation(project(":ocean-components"))
    implementation(libs.constraintLayout)
    implementation(libs.lifecycleExtensions)
    implementation(libs.lifecycleViewModel)
}
