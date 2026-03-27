import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.wbjang.coroutines_rest_coil_codelab_bookshelf"
    compileSdk = 35



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
    buildFeatures {
        compose = true
        buildConfig = true
    }
    // 2. local.properties 파일을 읽어오는 로직 추가
    val properties = Properties().apply {
        val propertiesFile = project.rootProject.file("local.properties")
        if (propertiesFile.exists()) {
            load(propertiesFile.inputStream())
        }
    }
    val apiKey = properties.getProperty("google_books_api_key") ?: ""

    defaultConfig {
        applicationId = "com.wbjang.coroutines_rest_coil_codelab_bookshelf"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "GOOGLE_BOOKS_API_KEY", "\"$apiKey\"")
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    // Updated BOM from 2023.06.01 to 2024.09.00 to match Kotlin 2.0.21 Compose Compiler
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    //viewmodel
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    //Retrofit
    implementation(libs.bundles.network)
//    implementation(libs.retrofit.core)
//    // Retrofit with Kotlin serialization Converter
//    implementation(libs.retrofit.converter.kotlinx)
//    implementation(libs.okhttp.core)
//    // Kotlin serialization
//    implementation(libs.kotlinx.serialization.json)

    // Coil
    implementation(libs.bundles.coil)
//    implementation(libs.coil.compose)
//    implementation(libs.coil.network.okhttp)

    //navigation
    implementation(libs.androidx.navigation.compose)
}