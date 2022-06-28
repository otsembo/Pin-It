object LibVersions {

    const val Kotlin = "1.7.0"
    const val AppCompat = "1.4.2"
    const val GoogleMaterial = "1.6.1"

    const val Navigation = "2.4.2"

    const val Firebase = "30.1.0"
    const val FirebaseAnalytics = "21.0.0"
    const val FirebaseAuth = "21.0.5"
    const val FirebaseFirestore = "24.1.2"
    const val FirebaseCloudStorage = "20.0.1"

    const val GoogleServices = "4.3.10"

    const val KotlinSerialization = "1.3.2"

    const val LifecycleRuntime = "2.5.0-rc02"

    const val KoinVersion = "3.2.0"

    const val CoroutinePlayServices = "1.5.1"

    const val CircleImage = "3.1.0"
}

object TestVersions {

    const val Junit4 = "4.13.2"
}

object InstrumentationTestVersions {

    const val AndroidJunit = "1.1.3"
    const val Espresso = "3.4.0"
}

object DevLibs {

    const val Kotlin = "androidx.core:core-ktx:${LibVersions.Kotlin}"
    const val AppCompat = "androidx.appcompat:appcompat:${LibVersions.AppCompat}"
    const val GoogleMaterial = "com.google.android.material:material:${LibVersions.GoogleMaterial}"

    // Navigation
    const val NavigationFragment = "androidx.navigation:navigation-fragment-ktx:${LibVersions.Navigation}"
    const val NavigationUI = "androidx.navigation:navigation-ui-ktx:${LibVersions.Navigation}"
    const val NavigationFeatureModules = "androidx.navigation:navigation-dynamic-features-fragment:${LibVersions.Navigation}"

    // Firebase
    const val Firebase = "com.google.firebase:firebase-bom:${LibVersions.Firebase}"
    const val FirebaseAnalytics = "com.google.firebase:firebase-analytics-ktx:${LibVersions.FirebaseAnalytics}"
    const val FirebaseAuth = "com.google.firebase:firebase-auth-ktx:${LibVersions.FirebaseAuth}"
    const val FirebaseFirestore = "com.google.firebase:firebase-firestore-ktx:${LibVersions.FirebaseFirestore}"
    const val FirebaseCloudStorage = "com.google.firebase:firebase-storage-ktx:${LibVersions.FirebaseCloudStorage}"

    // Kotlin Serialization
    const val KotlinSerialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:${LibVersions.KotlinSerialization}"

    // Dagger Hilt

    const val HiltAndroid = "com.google.dagger:hilt-android:2.38.1"
    const val HiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:2.38.1"
    const val HiltLifeCycle = "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03"
    const val HiltCompiler = "androidx.hilt:hilt-compiler:1.0.0"

    const val LifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${LibVersions.LifecycleRuntime}"
    const val LifecycleProcessor = "androidx.lifecycle:lifecycle-compiler:${LibVersions.LifecycleRuntime}"

    // Koin Injection
    const val KoinCore = "io.insert-koin:koin-core:${LibVersions.KoinVersion}"
    const val KoinAndroid = "io.insert-koin:koin-android:${LibVersions.KoinVersion}"

    // play services coroutines
    const val CoroutinePlayServices = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${LibVersions.CoroutinePlayServices}"

    // CIRCLE IMAGEVIEW
    const val CircleImageView = "de.hdodenhof:circleimageview:${LibVersions.CircleImage}"
}

object TestLibs {

    const val Junit4 = "junit:junit:${TestVersions.Junit4}"
    const val KoinTest = "io.insert-koin:koin-test:${LibVersions.KoinVersion}"
    const val KoinJunitTest = "io.insert-koin:koin-test-junit4:${LibVersions.KoinVersion}"
}

object InstrumentationTestLibs {

    const val AndroidJunit = "androidx.test.ext:junit:${InstrumentationTestVersions.AndroidJunit}"
    const val Espresso = "androidx.test.espresso:espresso-core:${InstrumentationTestVersions.Espresso}"
    const val Navigation = "androidx.navigation:navigation-testing:${LibVersions.Navigation}"
}

object Global {

    const val JVM_TARGET = "1.8"
    const val INSTRUMENTATION_RUNNER = "androidx.test.runner.AndroidJUnitRunner"

    // application config
    object AppConfig {

        const val MIN_SDK = 22
        const val TARGET_SDK = 32
        const val COMPILE_SDK = 32
        const val VERSION_CODE = 1
        const val VERSION_NAME = "1.0"
        const val APP_ID = "com.otsembo.pinit"
    }

    // plugins
    object AndroidPlugins {

        // Google Services
        const val GoogleServices = "com.google.gms.google-services"
        const val Android = "com.android.application"
        const val Library = "com.android.library"
    }

    object KotlinModules {

        const val Android = "android"
        const val JVM = "jvm"

        // Kotlin serialization
        const val KotlinSerialization = "plugin.serialization"

        // dagger hilt
        const val Hilt = "dagger.hilt.android.plugin"

        // kotlin kapt
        const val Kapt = "kotlin-kapt"
    }
}
