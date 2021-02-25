const val kotlinVersion = "1.4.0"
const val kotlinAndroid = "android"
const val kotlinAndroidExtension = "android.extensions"
const val kotlinKapt = "kapt"
const val ktLintVersion = "0.37.0"

object Config {
    object Version {
        const val minSdkVersion: Int = 21
        const val compileSdkVersion: Int = 29
        const val targetSdkVersion: Int = 29
        const val versionName = "1.0"
        const val versionCode: Int = 1
    }

    const val isMultiDexEnabled: Boolean = true

    object Android {
        const val applicationId = "com.atef.clubhouse"
        const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

interface Libraries {
    val components: List<String>
}

object Dependencies {
    object AndroidX : Libraries {
        object Version {
            const val coreKtx = "1.3.0"
            const val navigation = "2.3.0"
            const val multidex = "2.0.1"
            const val lifeCycle = "2.0.0"
            const val activity = "1.1.0"
            const val preferenceKts = "1.1.0"
            const val crypto = "1.1.0-alpha02"
        }

        object AnnotationProcessor {
            const val lifeCycleAnnotations =
                "androidx.lifecycle:lifecycle-compiler:${Version.lifeCycle}"
        }

        const val preferenceKts = "androidx.preference:preference-ktx:${Version.preferenceKts}"
        const val crypto = "androidx.security:security-crypto:${Version.crypto}"
        const val coreKtx = "androidx.core:core-ktx:${Version.coreKtx}"
        const val navigationFragmentKtx =
            "androidx.navigation:navigation-fragment-ktx:${Version.navigation}"
        const val navigationUiKtx =
            "androidx.navigation:navigation-ui-ktx:${Version.navigation}"
        const val multiDex = "androidx.multidex:multidex:${Version.multidex}"
        const val activity = "androidx.activity:activity-ktx:${Version.activity}"
        const val viewModel =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifeCycle}"
        const val lifeCycleExt = "androidx.lifecycle:lifecycle-extensions:${Version.lifeCycle}"
        const val lifecycleReactiveStreams =
            "androidx.lifecycle:lifecycle-reactivestreams:${Version.lifeCycle}"
        override val components: List<String>
            get() = listOf(
                coreKtx, navigationFragmentKtx, navigationUiKtx, multiDex, activity, viewModel,
                lifeCycleExt, lifecycleReactiveStreams
            )
    }

    object View : Libraries {
        object Version {
            const val materialComponent = "1.1.0"
            const val shimmerLayout = "0.5.0"
            const val appCompat = "1.0.0"
            const val constraintLayout = "2.0.0-rc1"
            const val fragment = "1.2.4"
            const val cardView = "1.0.0"
            const val recyclerView = "1.1.0"
            const val coil = "0.11.0"
            const val swipeRefreshLayout = "1.1.0-rc01"
            const val materialDialogs = "3.3.0"
        }

        const val appCompat = "androidx.appcompat:appcompat:${Version.appCompat}"
        const val fragment = "androidx.fragment:fragment-ktx:${Version.fragment}"
        const val cardView = "androidx.cardview:cardview:${Version.cardView}"
        const val materialComponent =
            "com.google.android.material:material:${Version.materialComponent}"
        const val shimmerLayout =
            "com.facebook.shimmer:shimmer:${Version.shimmerLayout}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Version.constraintLayout}"
        const val recyclerView =
            "androidx.recyclerview:recyclerview:${Version.recyclerView}"
        const val coil = "io.coil-kt:coil:${Version.coil}"
        const val swipeRefreshLayout =
            "androidx.swiperefreshlayout:swiperefreshlayout:${Version.swipeRefreshLayout}"
        const val materialDialogs =
            "com.afollestad.material-dialogs:core:${Version.materialDialogs}"

        override val components: List<String> = listOf(
            appCompat, fragment, cardView, coil, recyclerView, swipeRefreshLayout,
            materialComponent, constraintLayout, materialDialogs
        )
    }

    object Kotlin : Libraries {
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion"
        const val coroutiensCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinVersion"
        const val coroutiensAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$kotlinVersion"
        const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}"
        override val components: List<String>
            get() = listOf(stdlib, coroutiensCore, coroutiensAndroid, kotlinReflect)
    }

    object Network : Libraries {
        object Version {
            const val okhttp = "4.7.2"
            const val retrofit = "2.9.0"
            const val gson = "2.9.0"
        }


        const val okhttp = "com.squareup.okhttp3:okhttp:${Version.okhttp}"
        const val loggingInterceptor =
            "com.squareup.okhttp3:logging-interceptor:${Version.okhttp}"
        const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
        const val gson = "com.squareup.retrofit2:converter-gson:${Version.gson}"
        override val components: List<String> = listOf(okhttp, loggingInterceptor, retrofit, gson)
    }

    object DI : Libraries {
        object Version {
            const val javaxInject = "1"
            const val daggerHiltAndroid = "2.28-alpha"
            const val hiltAndroidx = "1.0.0-alpha01"
        }

        object AnnotationProcessor {
            const val daggerHiltGoogle =
                "com.google.dagger:hilt-android-compiler:${Version.daggerHiltAndroid}"
            const val daggerHiltAndroidx =
                "androidx.hilt:hilt-compiler:${Version.hiltAndroidx}"
        }

        const val javaxInject: String = "javax.inject:javax.inject:${Version.javaxInject}"
        const val daggerHiltViewModels =
            "androidx.hilt:hilt-lifecycle-viewmodel:${Version.hiltAndroidx}"
        const val daggerHiltAndroid =
            "com.google.dagger:hilt-android:${Version.daggerHiltAndroid}"
        override val components: List<String>
            get() = listOf(daggerHiltAndroid, daggerHiltViewModels, javaxInject)
    }

    object RX : Libraries {
        object Version {
            const val rxAndroid = "2.0.1"
            const val rxJava = "2.2.19"
            const val rxKotlin = "2.4.0"
            const val rxRelay = "2.1.0"
        }

        const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Version.rxAndroid}"
        const val rxJava = "io.reactivex.rxjava2:rxjava:${Version.rxJava}"
        const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${Version.rxKotlin}"
        const val rxRelay = "com.jakewharton.rxrelay2:rxrelay:${Version.rxRelay}"
        override val components: List<String> = listOf(rxAndroid, rxJava, rxKotlin, rxRelay)
    }

    object Room : Libraries {
        const val roomVersion = "2.2.3"
        const val room = "androidx.room:room-runtime:$roomVersion"
        const val roomKtx = "androidx.room:room-ktx:$roomVersion"
        const val roomRx = "androidx.room:room-rxjava2:$roomVersion"
        override val components: List<String>
            get() = listOf(room, roomKtx, roomRx)
    }

    object Misc : Libraries {
        object Version {
            const val timber = "4.7.1"
            const val gson = "2.8.6"
            const val liveEvent = "1.2.0"
        }

        const val gson = "com.google.code.gson:gson:${Version.gson}"
        const val timber = "com.jakewharton.timber:timber:${Version.timber}"
        const val liveEvent = "com.github.hadilq.liveevent:liveevent:${Version.liveEvent}"
        override val components: List<String>
            get() = listOf(timber, gson, liveEvent)
    }

    object FireBase : Libraries {
        object Version {
            const val firebaseConfig = "19.1.4"
            const val firebaseMessaging = "20.1.7"
            const val firebaseAnalytics = "17.4.1"
        }

        const val firebaseConfig =
            "com.google.firebase:firebase-config-ktx:${Version.firebaseConfig}"
        const val firebaseMessaging =
            "com.google.firebase:firebase-messaging:${Version.firebaseMessaging}"
        const val firebaseAnalytics =
            "com.google.firebase:firebase-analytics:${Version.firebaseAnalytics}"

        override val components: List<String>
            get() = listOf(firebaseConfig, firebaseMessaging, firebaseAnalytics)
    }

    object Test {
        object Version {
            const val junit = "4.13"
            const val junitExt = "1.1.3-alpha03"
            const val assertJ = "3.15.0"
            const val runner = "1.1.0"
            const val rules = "1.3.0"
            const val testExt = "1.1.1"
            const val espresso = "3.2.0"
            const val fragment = "1.1.0-rc04"
            const val truth = "1.0.1"
            const val mockWebServer = "4.7.2"
            const val mockito = "2.2.0"
            const val hilt = "2.28-alpha"
            const val core = "2.1.0"
            const val mock = "1.10.5"
        }

        const val junitExt = "androidx.test.ext:junit-ktx:${Version.junitExt}"
        const val core = "androidx.arch.core:core-testing:${Version.core}"
        const val junit = "junit:junit:${Version.junit}"
        const val hiltTest = "com.google.dagger:hilt-android-testing:${Version.hilt}"
        const val runner = "androidx.test:runner:${Version.runner}"
        const val fragmentTesting = "androidx.fragment:fragment-testing:${Version.fragment}"
        const val testExt = "androidx.test.ext:junit:${Version.testExt}"
        const val espresso = "androidx.test.espresso:espresso-core:${Version.espresso}"
        const val espressoContrib = "androidx.test.espresso:espresso-contrib:${Version.espresso}"
        const val espressoIdlRes = "androidx.test.espresso:espresso-idling-resource:${Version.espresso}"
        const val espressoIntents = "androidx.test.espresso:espresso-intents:${Version.espresso}"
        const val rules = "androidx.test:rules:${Version.rules}"
        const val truth = "com.google.truth:truth:${Version.truth}"
        const val mokitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Version.mockito}"
        const val mock = "io.mockk:mockk:${Version.mock}"
        const val assertJ = "org.assertj:assertj-core:${Version.assertJ}"
        const val mockWebServer =
            "com.squareup.okhttp3:mockwebserver:${Version.mockWebServer}"

        const val coroutiensTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$kotlinVersion"
    }
}

