// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.compose) apply false
    id("com.google.devtools.ksp") version "2.3.1" apply false
    id("com.google.dagger.hilt.android") version "2.60" apply false
    id("com.google.gms.google-services") version "4.5.0" apply false
    kotlin("plugin.serialization") version "2.0.0" apply false
}
buildscript {
    repositories {
        mavenCentral()
        google()
    }
    configurations.all {
        resolutionStrategy {
            force("org.jetbrains:annotations:23.0.0")
        }
    }
}
