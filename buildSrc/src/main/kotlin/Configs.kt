object Configs {
    const val compileSdkVersion = 34
    const val minSdkVersion = 23
    const val targetSdkVersion = 34
    const val kotlinCompilerExtensionVersion = "1.5.6"
    const val gprBaseUrl = "https://maven.pkg.github.com"
    const val gprRepoOwner = "ocean-ds"
    const val gprRepoId = "ocean-android"
    val gprUser by lazy { System.getenv("GPR_USER") }
    val gprApiKey by lazy { System.getenv("GPR_API_KEY") }
}