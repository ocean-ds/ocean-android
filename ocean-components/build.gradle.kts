import java.io.FileInputStream
import java.util.Properties

plugins {
    id("maven-publish")
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("androidx.baselineprofile")
    alias(libs.plugins.compose.compiler)
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

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
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
    namespace = "br.com.useblu.oceands"
}

afterEvaluate {
    publishing {
        repositories {
            maven {
                name = "ocean-android"
                url = uri("https://maven.pkg.github.com/ocean-ds/ocean-android")
                credentials {
                    username = System.getenv("GPR_USER")
                    password = System.getenv("GPR_API_KEY")
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
    implementation(libs.skydoves.balloon)
    implementation(libs.skydoves.balloon.compose)
    implementation(libs.androidMaterial)
    implementation(libs.glide)
    implementation(libs.circleIndicator)
    implementation(libs.expansionPanel)
    implementation(libs.calendarView)
    implementation(libs.mpChart)
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
    implementation(libs.compose.uiTestManifest)
    implementation(libs.compose.activity)
    debugImplementation(libs.compose.uiTooling)
    androidTestImplementation(libs.compose.uiTestJunit4)
    testImplementation(libs.compose.uiTestJunit4)

    testImplementation(libs.junit)
    testImplementation(libs.robolectric)
}

baselineProfile {
    automaticGenerationDuringBuild = false
    saveInSrc = true
    filter {
        include("br.com.useblu.oceands.**")
        exclude("br.com.useblu.oceands.client.**")
    }
}
