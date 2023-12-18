plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin") version "2.0.1" apply false
}

android {
    namespace = "com.my.campingcuy"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.my.campingcuy"
        minSdk = 24
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
        buildConfig = true
    }
    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {

    implementation ("androidx.test.espresso:espresso-contrib:3.5.1")
    implementation("androidx.test.ext:junit-ktx:1.1.5")


    //special testing
    testImplementation ("androidx.arch.core:core-testing:2.2.0") // InstantTaskExecutorRule
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1")


    //TestCoroutineDispatcher
    testImplementation ("org.mockito:mockito-core:4.6.1")
    testImplementation ("org.mockito:mockito-inline:4.6.1")
    testImplementation("com.squareup.okhttp3:mockwebserver:4.9.3")
    testImplementation("io.strikt:strikt-core:0.31.0")
    androidTestImplementation ("com.squareup.okhttp3:mockwebserver3:5.0.0-alpha.2")

    //special instrumentation testing
    androidTestImplementation ("androidx.arch.core:core-testing:2.2.0") // InstantTaskExecutorRule
    androidTestImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1")

    //TestCoroutineDispatcher//RecyclerViewActions
    debugImplementation ("androidx.fragment:fragment-testing:1.6.1")
    implementation ("androidx.test.espresso:espresso-idling-resource:3.4.0")
    androidTestImplementation ("androidx.test.espresso:espresso-intents:3.4.0")

    //maps
    implementation ("com.google.android.gms:play-services-location:21.0.1")
    implementation ("com.google.android.gms:play-services-places:17.0.0")
    implementation ("com.google.android.gms:play-services-maps:18.2.0")

    implementation ("androidx.paging:paging-runtime-ktx:3.2.1")
    implementation ("androidx.room:room-paging:2.6.0")

    implementation ("androidx.room:room-runtime:2.6.0")
    implementation ("com.google.android.gms:play-services-maps:18.2.0")
    testImplementation ("junit:junit:4.13.2")
    testImplementation ("junit:junit:4.13.2")
    kapt ("androidx.room:room-compiler:2.6.0")
    implementation ("androidx.room:room-ktx:2.6.0")

    implementation ("com.github.bumptech.glide:glide:4.15.1")
    implementation ("id.zelory:compressor:3.0.1")

    // api
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.5")

    // core
    implementation ("androidx.datastore:datastore-preferences:1.0.0")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")

    implementation ("androidx.core:core-ktx:1.12.0")
    implementation ("androidx.appcompat:appcompat:1.6.1")
    implementation ("com.google.android.material:material:1.10.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.4.0")
    implementation ("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
}