import Dependencies.AndroidX
import Dependencies.DI
import Dependencies.Kotlin
import Dependencies.Network
import Dependencies.View
import Dependencies.Misc
import Dependencies.Test
import Dependencies.FireBase

plugins {
    androidApplication
    kotlin(kotlinAndroid)
    kotlin(kotlinAndroidExtension)
    kotlin(kotlinKapt)
    safeArgs
    daggerHilt
}

android {
    compileSdkVersion(Config.Version.targetSdkVersion)

    defaultConfig {
        applicationId = Config.Android.applicationId
        minSdkVersion(Config.Version.minSdkVersion)
        compileSdkVersion(Config.Version.compileSdkVersion)
        targetSdkVersion(Config.Version.targetSdkVersion)
        versionCode = Config.Version.versionCode
        versionName = Config.Version.versionName
        testInstrumentationRunner = Config.Android.testInstrumentationRunner
    }
    androidExtensions {
        isExperimental = true
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    setDefaultSigningConfigs(project)

    buildTypes {
        named(BuildType.DEBUG) {
            val baseUrl = project.properties["baseUrl"].toString()
            buildConfigString("BASE_URL", baseUrl)
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
            applicationIdSuffix = BuildTypeDebug.applicationIdSuffix
            versionNameSuffix = BuildTypeDebug.versionNameSuffix
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementAll(View.components)
    implementAll(Kotlin.components)
    implementAll(Misc.components)
    implementAll(DI.components)
    implementAll(Network.components)
    AndroidX.run {
        implementation(activity)
        implementation(coreKtx)
        implementation(navigationFragmentKtx)
        implementation(navigationUiKtx)
        implementation(lifeCycleExt)
        implementation(viewModel)
        implementation(lifecycleReactiveStreams)
    }
    implementation(FireBase.firebaseMessaging)
    kapt(DI.AnnotationProcessor.daggerHiltGoogle)
    kapt(DI.AnnotationProcessor.daggerHiltAndroidx)

    testImplementation(Test.junit)
    testImplementation(Test.truth)
    testImplementation(Test.hiltTest)
    testImplementation(Test.mokitoKotlin)
    testImplementation(Test.mockWebServer)
    testImplementation(Test.coroutiensTest)
    testImplementation(Test.mock)
    testImplementation(Test.core)
    androidTestImplementation(Test.rules)
    androidTestImplementation(Test.core)
    androidTestImplementation(Test.runner)
    androidTestImplementation(Test.fragmentTesting)
    androidTestImplementation(Test.espressoIntents)
    androidTestImplementation(Test.espresso)
    androidTestImplementation(Test.espressoContrib)
    androidTestImplementation(Test.espressoIdlRes)
    androidTestImplementation(Test.testExt)
    kaptAndroidTest(DI.AnnotationProcessor.daggerHiltGoogle)

}
