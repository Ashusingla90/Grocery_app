plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)          // ✅ Added: required for Kotlin
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.kapt)             // ✅ Added: required for kapt() calls
    alias(libs.plugins.kotlin.serialization)    // ✅ Added: required for kotlinx-serialization
    alias(libs.plugins.hilt.android)            // ✅ Added: required for Hilt
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.example.groceryapp"
    compileSdk = 36   // ✅ Fixed: was an invalid block syntax

    defaultConfig {
        applicationId = "com.example.groceryapp"
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

    kotlinOptions {
        jvmTarget = "11"   // ✅ Added: required when compileOptions targets Java 11
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    // AndroidX Core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    // Compose BOM (manages all Compose versions — no need to hardcode them)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    // Firebase — use BOM to manage versions; removed duplicate firebase-auth
    implementation(platform("com.google.firebase:firebase-bom:32.7.0"))
    implementation(libs.firebase.database)
    implementation("com.google.firebase:firebase-auth-ktx")  // ✅ Kept one; removed duplicate

    // Credentials / Google Sign-In
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth)
    implementation(libs.googleid)

    // Coil
    implementation("io.coil-kt:coil-compose:2.6.0")

    // Bottom Navigation Bar
    implementation("com.canopas.compose-animated-navigationbar:bottombar:1.0.1")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.31.1-alpha")

    // Lottie Animation
    implementation("com.airbnb.android:lottie-compose:4.2.0")

    // Splash Screen
    implementation("androidx.core:core-splashscreen:1.0.1")

    // ViewModel + LiveData + Coroutines
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")
    implementation("androidx.compose.runtime:runtime-livedata:1.7.8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Hilt Dependency Injection
    implementation("com.google.dagger:hilt-android:2.55")
    kapt("com.google.dagger:hilt-android-compiler:2.55")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    kapt("androidx.hilt:hilt-compiler:1.2.0")

//    implementation(libs.hilt.android)
//    kapt(libs.hilt.compiler)

    // Navigation
    implementation("androidx.navigation:navigation-compose:2.8.0-beta06")

    // Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")

    // Material Icons Extended  ✅ Fixed: was <compose_version> (invalid), now a real version string
    implementation("androidx.compose.material:material-icons-extended:1.7.8")

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}