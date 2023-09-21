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
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    api("com.github.skydoves:balloon:1.5.2")
    api("com.google.android.material:material:1.8.0")
    api("com.github.concretesolutions:canarinho:2.0.2")
    api("com.github.bumptech.glide:glide:4.15.1")
    api("me.relex:circleindicator:2.1.6")
    api("org.imaginativeworld.whynotimagecarousel:whynotimagecarousel:2.0.4")
    api("com.github.florent37:expansionpanel:1.2.4")
    api("com.github.prolificinteractive:material-calendarview:2.0.1")
    api("com.github.PhilJay:MPAndroidChart:v3.1.0")
    implementation("com.github.EudyContreras:Skeleton-Bones:v1.4.5")
    implementation("com.github.skydoves:landscapist-glide:2.2.2")

    // COMPOSE
    val composeBom = platform("androidx.compose:compose-bom:2023.06.01")
    implementation(composeBom)
    androidTestImplementation(composeBom)

    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.ui:ui-viewbinding")
    debugImplementation("androidx.compose.ui:ui-tooling")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
    implementation("androidx.compose.runtime:runtime-livedata")
}