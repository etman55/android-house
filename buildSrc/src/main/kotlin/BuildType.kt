interface BuildType {

    companion object {
        const val DEBUG: String = "debug"
        const val RELEASE: String = "release"
    }

    val isDebuggable: Boolean
    val isMinifyEnabled: Boolean
    val isTestCoverageEnabled: Boolean
}

object BuildTypeDebug : BuildType {
    override val isDebuggable: Boolean = true
    override val isMinifyEnabled: Boolean = false
    override val isTestCoverageEnabled: Boolean = true

    const val applicationIdSuffix: String = ".debug"
    const val versionNameSuffix: String = "-DEBUG"
}

object BuildTypeRelease : BuildType {
    override val isDebuggable: Boolean = false
    override val isMinifyEnabled: Boolean = true
    override val isTestCoverageEnabled: Boolean = false
}
