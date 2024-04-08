import java.io.FileInputStream
import java.util.Properties

plugins {
    id("maven-publish")
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("androidx.baselineprofile")
}

fun getVersionName(): String {
    val versionNameKey = "VERSION_NAME"

    val props = Properties()
    val propFile = file("version.properties")

    if (propFile.canRead()) {
        props.load(FileInputStream(propFile))

        if (props.containsKey(versionNameKey)) {
            return props[versionNameKey].toString()
        }
    }

    return ""
}

android {
    compileSdk = Configs.compileSdkVersion

    defaultConfig {
        minSdk = Configs.minSdkVersion

        consumerProguardFiles("consumer-rules.pro")
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
    namespace = "br.com.useblu.oceands"
}

afterEvaluate {
    publishing {
        repositories {
            maven {
                name = "ocean-android"
                url = uri("${Configs.gprBaseUrl}/${Configs.gprRepoOwner}/${Configs.gprRepoId}")
                credentials {
                    username = Configs.gprUser
                    password = Configs.gprApiKey
                }
            }
        }

        val mavenGroupId = "br.com.useblu"
        val mavenArtifactId = project.name

        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                groupId = mavenGroupId
                artifactId = mavenArtifactId
                version = getVersionName()
            }
        }
    }
}

dependencies {
    implementation(libs.baselineProfileInstaller)
    "baselineProfile"(project(":baselineprofile"))

    implementation(libs.androidKtx)
    implementation(libs.androidAppCompat)
    api(libs.skydoves.balloon)
    api(libs.skydoves.balloon.compose)
    api(libs.androidMaterial)
    api(libs.canarinho)
    api(libs.glide)
    api(libs.circleIndicator)
    api(libs.expansionPanel)
    api(libs.calendarView)
    api(libs.mpChart)
    implementation(libs.skeletonBones)
    implementation(libs.recyclerView)
    implementation(libs.skydovesLandscapist)

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

    testImplementation(libs.junit)
}

baselineProfile {
    automaticGenerationDuringBuild = false
    saveInSrc = true
    filter {
        include("br.com.useblu.oceands.**")
        exclude("br.com.useblu.oceands.client.**")
    }
}