plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

android {
    namespace = "com.user.userexplorer"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.user.userexplorer"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildFeatures {
        dataBinding = true
        compose = true
    }
}

dependencies {

    /*implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.4")
    implementation("androidx.activity:activity-compose:1.9.1")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.navigation:navigation-compose:2.8.2")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")*/


    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.6")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.8.6")
    implementation ("androidx.activity:activity-compose:1.9.2")
    implementation ("androidx.compose.ui:ui:1.7.3")
    implementation ("androidx.compose.material:material:1.7.3")
    //implementation ("androidx.compose.material3:material3:1.3.0")
    implementation ("androidx.compose.ui:ui-tooling:1.7.3") // For Preview support
    implementation ("androidx.compose.ui:ui-tooling-preview:1.7.3")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    //implementation ("coil-kt:coil-compose:2.0.0")  // For image loading
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.1")
    implementation("androidx.tracing:tracing-perfetto-handshake:1.0.0")  // Logging
    testImplementation ("org.mockito:mockito-core:4.0.0")
    implementation("androidx.navigation:navigation-compose:2.8.2")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Coroutines core dependency
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

    // Coroutines support for Android
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // ViewModel support for Compose
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.6")

    // Coil for image loading in Jetpack Compose
    implementation ("io.coil-kt:coil-compose:2.4.0")

    implementation("androidx.compose.material3:material3:1.3.0")

    // Kotlin standard library
    implementation ("org.jetbrains.kotlin:kotlin-stdlib:1.9.0")

    // AndroidX parcelize runtime
    implementation ("androidx.core:core-ktx:1.12.0")

    implementation ("com.google.accompanist:accompanist-systemuicontroller:0.30.1")
}