// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "7.2.1" apply false
    id("com.android.library") version "7.2.1" apply false
    id("com.google.gms.google-services") version "4.3.10" apply false
    kotlin("android") version "1.7.0" apply false
    kotlin("jvm") version "1.7.0" apply false
    kotlin("plugin.serialization") version "1.7.0" apply false
    id("dagger.hilt.android.plugin") version "2.40.1" apply false
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
