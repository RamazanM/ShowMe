plugins {
    alias(libs.plugins.android.library)
    id("com.google.devtools.ksp") version "2.3.1"
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.ramazanm.showme.data"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.core.ktx)
    implementation(libs.material)
    implementation(project(":core:model"))
    // Hilt Core Dependencies
    implementation(libs.hilt.android)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.firestore)

    ksp(libs.hilt.compiler)

    implementation(platform(libs.firebase.bom))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
}