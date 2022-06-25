plugins {
    id(Global.AndroidPlugins.Android)
    id(Global.AndroidPlugins.GoogleServices)
    kotlin(Global.KotlinModules.Android)
    kotlin(Global.KotlinModules.KotlinSerialization)
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = Global.AppConfig.COMPILE_SDK

    defaultConfig {
        applicationId = Global.AppConfig.APP_ID
        minSdk = Global.AppConfig.MIN_SDK
        targetSdk = Global.AppConfig.TARGET_SDK
        versionCode = Global.AppConfig.VERSION_CODE
        versionName = Global.AppConfig.VERSION_NAME

        testInstrumentationRunner = Global.INSTRUMENTATION_RUNNER
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = Global.JVM_TARGET
    }

    buildFeatures {
        dataBinding = true
    }
}

dependencies {

    // MODULE DEPENDENCIES
    implementation(project(":core:authentication"))

    // DEVELOPER LIBS
    implementation(DevLibs.Kotlin)
    implementation(DevLibs.AppCompat)
    implementation(DevLibs.GoogleMaterial)

    // NAVIGATION
    implementation(DevLibs.NavigationFragment)
    implementation(DevLibs.NavigationUI)
    implementation(DevLibs.NavigationFeatureModules)

    // FIREBASE
    platform(DevLibs.Firebase)
    implementation(DevLibs.FirebaseAnalytics)
    implementation(DevLibs.CoroutinePlayServices)

    // DAGGER HILT
    implementation(DevLibs.HiltAndroid)
    kapt(DevLibs.HiltAndroidCompiler)
    implementation(DevLibs.HiltLifeCycle)
    kapt(DevLibs.HiltCompiler)

    // Koin
    implementation(DevLibs.KoinCore)
    implementation(DevLibs.KoinAndroid)

    implementation(DevLibs.LifecycleRuntime)
    kapt(DevLibs.LifecycleProcessor)

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
