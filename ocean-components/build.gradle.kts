import java.io.FileInputStream
import java.util.Properties

plugins {
    id("maven-publish")
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
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
        targetSdk = Configs.targetSdkVersion

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
        val gprUser = System.getenv("GPR_USER")
        val gprApiKey = System.getenv("GPR_API_KEY")

        repositories {
            maven {
                name = "ocean-android"
                url = uri("${Configs.gprBaseUrl}/${Configs.gprRepoOwner}/${Configs.gprRepoId}")
                credentials {
                    username = gprUser
                    password = gprApiKey
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
    implementation(Libraries.androidKtx)
    implementation(Libraries.androidAppCompat)
    api(Libraries.skydovesBalloon)
    api(Libraries.androidMaterial)
    api(Libraries.canarinho)
    api(Libraries.glide)
    api(Libraries.circleIndicator)
    api(Libraries.imageCarousel)
    api(Libraries.expansionPanel)
    api(Libraries.calendarView)
    api(Libraries.mpChart)
    implementation(Libraries.skeletonBones)
    implementation(Libraries.skydovesLandscapist)

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