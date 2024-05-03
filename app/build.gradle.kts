import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-parcelize")
    id("org.jlleitschuh.gradle.ktlint")
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.0"
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

android {
    namespace = "com.sopetit.softie"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.sopetit.softie"
        minSdk = 28
        targetSdk = 34
        versionCode = 10
        versionName = "1.0.1"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "BASE_URL", getApiKey("PROD_BASE_URL"))
        buildConfigField("String", "KAKAO_NATIVE_APP_KEY", getApiKey("KAKAO_NATIVE_APP_KEY"))
        manifestPlaceholders["KAKAO_REDIRECT_SCHEME"] = getApiKey("KAKAO_REDIRECT_SCHEME")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
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
        viewBinding = true
        dataBinding = true
    }
}

dependencies {

    // Glide
    implementation("com.github.bumptech.glide:glide:4.13.2")
    kapt("com.github.bumptech.glide:glide:4.13.2")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.48.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    kapt("com.google.dagger:hilt-compiler:2.48.1")

    // Shared Preference
    implementation("androidx.security:security-crypto-ktx:1.1.0-alpha06")

    // KTX
    implementation("androidx.activity:activity-ktx:1.8.2")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.fragment:fragment-ktx:1.6.2")

    // RecyclerView
    implementation("androidx.recyclerview:recyclerview:1.3.2")

    // Coroutine
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Network
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
    implementation("com.squareup.retrofit2:retrofit:2.5.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")
    implementation(platform("com.squareup.okhttp3:okhttp-bom:4.10.0"))
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor")

    // JSON
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.1")

    // Timber
    implementation("com.jakewharton.timber:timber:5.0.1")

    // Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.6")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.6")

    // Coil
    implementation("io.coil-kt:coil:2.5.0")
    implementation("io.coil-kt:coil-svg:2.4.0")

    // Lottie
    implementation("com.airbnb.android:lottie:5.2.0")

    // ExifInterface
    implementation("androidx.exifinterface:exifinterface:1.3.7")

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:32.7.3"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-crashlytics")
    implementation("com.google.android.gms:play-services-tagmanager:18.0.4")

    // viewPager2
    implementation("androidx.viewpager2:viewpager2:1.1.0-beta02")
    implementation("me.relex:circleindicator:2.1.6")
    implementation("com.tbuonomo:dotsindicator:5.0")

    // Splash Screen
    implementation("androidx.core:core-splashscreen:1.0.1")

    // kakao
    implementation("com.kakao.sdk:v2-user:2.12.1")

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Coroutine
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.3")
}

fun getApiKey(propertyKey: String): String {
    return gradleLocalProperties(rootDir).getProperty(propertyKey) ?: "null"
}
