plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("androidx.baselineprofile")
    alias(libs.plugins.compose.compiler)
}

android {
    compileSdk = Configs.compileSdkVersion

    defaultConfig {
        applicationId = "br.com.useblu.oceands.client"
        minSdk = Configs.minSdkVersion
        targetSdk = Configs.targetSdkVersion
        versionCode = 3
        versionName = "1.0.0"
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

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
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
    val composeBom = platform(libs.androidx.compose.bom)
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

    implementation(libs.skydoves.balloon)
    implementation(libs.skydoves.balloon.compose)
    implementation(libs.androidMaterial)
    implementation(libs.glide)
    implementation(libs.circleIndicator)
    implementation(libs.expansionPanel)
    implementation(libs.calendarView)
    implementation(libs.mpChart)
}
