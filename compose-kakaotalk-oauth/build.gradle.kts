import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
}

val properties = Properties().apply {
    val propertiesFile = project.rootProject.file("local.properties")
    if (propertiesFile.exists()) {
        load(propertiesFile.inputStream())
    }
}

// 특정 키값 가져오기 (예: kakao_native_app_key)
val kakaoNativeAppKey = properties.getProperty("kakao_native_app_key") ?: ""
//val kakaoNativeAppKey = properties.getProperty("kakao_native_app_key")?.replace("\"", "") ?: ""
android {
    namespace = "com.wbjang.compose_kakaotalk_oauth"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        applicationId = "com.wbjang.compose_kakaotalk_oauth"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        // manifestPlaceholders를 통해 XML로 값 전달
        manifestPlaceholders["kakaoNativeAppKey"] = kakaoNativeAppKey.replace("\"", "") ?: ""

        // 코드(Kotlin/Java)에서 직접 사용하고 싶을 때
        buildConfigField("String", "KAKAO_NATIVE_APP_KEY", kakaoNativeAppKey)
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
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.identity.credential)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    implementation(libs.kakao.all)// 전체 모듈 추가, 2.11.0 버전부터 지원
    implementation(libs.kakao.user)// 카카오 로그인 API 모듈
    implementation(libs.kakao.share)// 카카오톡 공유 API 모듈
    implementation(libs.kakao.talk)// 카카오톡 채널, 카카오톡 소셜, 카카오톡 메시지 API 모듈
    implementation(libs.kakao.friend)// 피커 API 모듈
    implementation(libs.kakao.navi)// 카카오내비 API 모듈
    implementation(libs.kakao.cert)// 카카오톡 인증 서비스 API 모듈
}