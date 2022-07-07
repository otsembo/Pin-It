plugins {
    id(Global.AndroidPlugins.Library)
    id(Global.AndroidPlugins.GoogleServices)
    kotlin(Global.KotlinModules.Android)
    kotlin(Global.KotlinModules.KotlinSerialization)
    kotlin("kapt")
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 22
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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

    implementation(project(":core:theming"))

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

    // CIRCLE IMAGE VIEW
    implementation(DevLibs.CircleImageView)

    // Coil
    implementation(DevLibs.Coil)

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

    // Apache Commons
    implementation(DevLibs.ApacheCommons)

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
