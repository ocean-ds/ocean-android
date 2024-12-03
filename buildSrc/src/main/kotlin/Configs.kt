object Configs {
    const val compileSdkVersion = 35
    const val minSdkVersion = 26
    const val targetSdkVersion = 35
    const val gprBaseUrl = "https://maven.pkg.github.com"
    const val gprRepoOwner = "ocean-ds"
    const val gprRepoId = "ocean-android"
    val gprUser by lazy { System.getenv("GPR_USER") }
    val gprApiKey by lazy { System.getenv("GPR_API_KEY") }
}