object Configs {
    const val compileSdkVersion = 33
    const val buildToolsVersion = "30.0.3"
    const val minSdkVersion = 23
    const val targetSdkVersion = 33
    const val kotlinCompilerExtensionVersion = "1.4.8"
    const val gprBaseUrl = "https://maven.pkg.github.com"
    const val gprRepoOwner = "useblu"
    const val gprRepoId = "ocean-android"
    val gprUser by lazy { System.getenv("GPR_USER") }
    val gprApiKey by lazy { System.getenv("GPR_API_KEY") }
}