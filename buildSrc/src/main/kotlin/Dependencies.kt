object LibVersions {

    const val Kotlin = "1.7.0"
    const val AppCompat = "1.4.2"
    const val GoogleMaterial = "1.6.1"

    const val Navigation = "2.4.2"
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
}

object TestLibs {

    const val Junit4 = "junit:junit:${TestVersions.Junit4}"
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

        const val Android = "com.android.application"
    }

    object KotlinModules {

        const val Android = "android"
    }
}
