plugins {
    id("com.android.library")
    kotlin("android")
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

    // DEVELOPER LIBS
    implementation(DevLibs.Kotlin)
    implementation(DevLibs.AppCompat)
    implementation(DevLibs.GoogleMaterial)

    // NAVIGATION
    implementation(DevLibs.NavigationFragment)
    implementation(DevLibs.NavigationUI)
    implementation("androidx.appcompat:appcompat:1.4.2")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // FIREBASE
    platform(DevLibs.Firebase)
    implementation(DevLibs.FirebaseAnalytics)
    implementation(DevLibs.FirebaseAuth)
    implementation(DevLibs.FirebaseCloudStorage)
    implementation(DevLibs.FirebaseFirestore)

    // UNIT TEST LIBS
    testImplementation(TestLibs.Junit4)
    testImplementation(TestLibs.KoinTest)
    testImplementation(TestLibs.KoinJunitTest)

    // ANDROID AND INSTRUMENTATION LIBS
    androidTestImplementation(InstrumentationTestLibs.AndroidJunit)
    androidTestImplementation(InstrumentationTestLibs.Espresso)
    androidTestImplementation(InstrumentationTestLibs.Navigation)
}
