plugins {
    id(Global.AndroidPlugins.Android)
    kotlin(Global.KotlinModules.Android)
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
        jvmTarget = Global.JVM_TARGET
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

    // UNIT TEST LIBS
    testImplementation(TestLibs.Junit4)

    // ANDROID AND INSTRUMENTATION LIBS
    androidTestImplementation(InstrumentationTestLibs.AndroidJunit)
    androidTestImplementation(InstrumentationTestLibs.Espresso)
}
