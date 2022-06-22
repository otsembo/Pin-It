plugins {
    id(Global.AndroidPlugins.Android)
    kotlin(Global.KotlinModules.Android)
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
