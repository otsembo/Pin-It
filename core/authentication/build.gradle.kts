plugins {
    id(Global.AndroidPlugins.Library)
    id(Global.AndroidPlugins.GoogleServices)
    kotlin(Global.KotlinModules.Android)
    kotlin(Global.KotlinModules.KotlinSerialization)
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = Global.AppConfig.COMPILE_SDK

    defaultConfig {
        minSdk = Global.AppConfig.MIN_SDK
        targetSdk = Global.AppConfig.TARGET_SDK

        testInstrumentationRunner = Global.INSTRUMENTATION_RUNNER
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        dataBinding = true
    }
}

dependencies {

    // DEVELOPER LIBS
    implementation(DevLibs.Kotlin)
    implementation(DevLibs.AppCompat)
    implementation(DevLibs.GoogleMaterial)

    // NAVIGATION
    implementation(DevLibs.NavigationFragment)
    implementation(DevLibs.NavigationUI)

    // FIREBASE
    platform(DevLibs.Firebase)
    implementation(DevLibs.FirebaseAnalytics)
    implementation(DevLibs.FirebaseAuth)
    implementation(DevLibs.FirebaseCloudStorage)
    implementation(DevLibs.FirebaseFirestore)

    // Kotlin Serialization
    implementation(DevLibs.KotlinSerialization)

    // DAGGER HILT
    implementation(DevLibs.HiltAndroid)
    kapt(DevLibs.HiltAndroidCompiler)
    implementation(DevLibs.HiltLifeCycle)
    kapt(DevLibs.HiltCompiler)

    implementation(DevLibs.LifecycleRuntime)
    kapt(DevLibs.LifecycleProcessor)

    // Koin
    implementation(DevLibs.KoinCore)
    implementation(DevLibs.KoinAndroid)

    // Coroutine Play Services
    implementation(DevLibs.CoroutinePlayServices)

    // UNIT TEST LIBS
    testImplementation(TestLibs.Junit4)
    testImplementation(TestLibs.KoinTest)
    testImplementation(TestLibs.KoinJunitTest)

    // ANDROID AND INSTRUMENTATION LIBS
    androidTestImplementation(InstrumentationTestLibs.AndroidJunit)
    androidTestImplementation(InstrumentationTestLibs.Espresso)
    androidTestImplementation(InstrumentationTestLibs.Navigation)
}

kapt {
    correctErrorTypes = true
}
