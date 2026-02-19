import com.android.build.api.dsl.ManagedVirtualDevice
import com.android.build.api.dsl.TestExtension

plugins {
    id("com.android.test")
    id("androidx.baselineprofile")
}

extensions.configure<TestExtension> {
    namespace = "br.com.useblu.oceands.baselineprofile"
    compileSdk = Configs.compileSdkVersion

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    defaultConfig {
        minSdk = Configs.minSdkVersion
        targetSdk = Configs.targetSdkVersion
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    targetProjectPath = ":app"

    testOptions.managedDevices.allDevices.create<ManagedVirtualDevice>("pixel7Api30") {
        device = "Pixel 7"
        sdkVersion = 30
        systemImageSource = "aosp"
    }
}

// This is the configuration block for the Baseline Profile plugin.
// You can specify to run the generators on a managed devices or connected devices.
baselineProfile {
    managedDevices += "pixel7Api30"
    useConnectedDevices = false
}

dependencies {
    implementation(libs.androidx.junit)
    implementation(libs.androidx.espresso.core)
    implementation(libs.androidx.uiautomator)
    implementation(libs.androidx.benchmark.macro.junit4)
}
