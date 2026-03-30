plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.google.ksp)
    alias(libs.plugins.google.dagger.hilt)
}

android {
    namespace = "com.wbjang.hilt_codelab"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.wbjang.hilt_codelab"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.recyclerview)

    implementation(libs.material)
    implementation(libs.androidx.activity)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.google.dagger.hilt.android)
    ksp(libs.google.dagger.hilt.compiler)

    implementation(libs.bundles.room)
    ksp(libs.androidx.room.compiler)
}
