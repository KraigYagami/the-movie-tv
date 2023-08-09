
import java.util.Properties

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.hilt.android.plugin)
    kotlin("kapt")
}

val propertiesFile = rootProject.file("apikey.properties")
val properties = Properties()

if (propertiesFile.exists()) {
    properties.load(propertiesFile.reader())
}

val apiKey: String = properties.getProperty("apiKey", "")

android {
    namespace = "com.example.themovietv"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.themovietv"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        buildConfigField("String", "API_KEY", "\"$apiKey\"")
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
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    kapt {
        correctErrorTypes = true
    }
}

tasks.register("printProperties") {
    doLast {
        println("App ApiKey: $apiKey")
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

    //region Lifecycle for collect flow
    implementation(libs.lifecycle.runtime.compose)
    //endregion

    implementation(libs.coil.compose)

    //region Retrofit dependencies
    implementation(libs.retrofit)
    implementation(libs.converter.moshi)
    implementation(libs.moshi.kotlin)
    implementation(libs.logging.interceptor)

    //region Hilt dependencies
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(libs.hilt.compose)
    //endregion
}
