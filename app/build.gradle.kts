@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.hilt.android.plugin)
    kotlin("kapt")
}

android {
    namespace = "com.example.themovietv"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.themovietv"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    kapt {
        correctErrorTypes = true
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.leanback)
    implementation(libs.appcompat)

    //region General compose dependencies
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui.tooling.preview)
    debugImplementation(libs.ui.tooling)
    //endregion

    //region Compose for TV dependencies
    implementation(libs.tv.compose)
    implementation(libs.tv.material)
    //endregion

    implementation(libs.coil.compose)

    //region Retrofit dependencies
    implementation(libs.retrofit)
    implementation(libs.converter.moshi)
    implementation(libs.logging.interceptor)

    //region Hilt dependencies
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    //endregion
}
