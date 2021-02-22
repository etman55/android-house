import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Project
import java.io.File
import java.io.FileInputStream
import java.lang.Exception
import java.util.Properties


fun BaseAppModuleExtension.setDefaultSigningConfigs(project: Project) = signingConfigs {
    getByName("debug") {
        storeFile = project.rootProject.file("debug-key.jks")
        storePassword = "android"
        keyAlias = "androiddebugkey"
        keyPassword = "android"
    }
    register("release") {
        val keystorePropertiesFile = project.rootProject.file("release.keystore.properties")
        if (!keystorePropertiesFile.exists()) {
            System.err.println("📜 Missing release.keystore.properties file for release signing")
        } else {
            val keystoreProperties = Properties().apply {
                load(FileInputStream(keystorePropertiesFile))
            }
            try {
                storeFile = File(keystoreProperties["storeFile"] as String)
                storePassword = keystoreProperties["storePassword"] as String
                keyAlias = keystoreProperties["keyAlias"] as String
                keyPassword = keystoreProperties["keyPassword"] as String
            } catch (e: Exception) {
                System.err.println("📜 release.keystore.properties file is malformed")
            }
        }
    }
}