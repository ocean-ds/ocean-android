object Configs {
    const val compileSdkVersion = 33
    const val minSdkVersion = 23
    const val targetSdkVersion = 33
    const val kotlinCompilerExtensionVersion = "1.4.8"
    const val gprBaseUrl = "https://maven.pkg.github.com"
    const val gprRepoOwner = "ocean-ds"
    const val gprRepoId = "ocean-android"
    val gprUser by lazy { System.getenv("GPR_USER") }
    val gprApiKey by lazy { System.getenv("GPR_API_KEY") }
}