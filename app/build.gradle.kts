plugins {
    kotlin("kapt")
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.dagger.hilt.android)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.example.vkpokeintern"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.vkpokeintern"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            buildConfigField("String", "BASE_URL", "\"https://pokeapi.co/api/v2/\"")
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", "\"https://pokeapi.co/api/v2/\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {
//    Modules
    implementation(project(":domain"))
    implementation(project(":data"))

//    Base
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

//    DI
    implementation(libs.google.dagger.hilt.android)
    kapt(libs.google.dagger.hilt.android.compiler)

//    Navigation
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

//    Network
    implementation(libs.squareup.retrofit2.retrofit)
    implementation(libs.squareup.retrofit2.converter.gson)
    implementation(libs.squareup.okhttp3.logging.interceptor)
    implementation(libs.glide)

    //    Local db
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    annotationProcessor(libs.androidx.room.compiler)
    kapt(libs.androidx.room.compiler)

//    Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

kapt {
    correctErrorTypes = true
}