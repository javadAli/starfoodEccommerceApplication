plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-parcelize")
    id("com.google.devtools.ksp")
    id("com.google.gms.google-services")
}
android {
    namespace = "com.example.starfood"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.starfood"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        multiDexEnabled = true
        missingDimensionStrategy("react-native-camera", "general")
    }
/*    kotlin {
        jvmToolchain(20)
    }

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(20))
        }
    }*/
    buildTypes {
        release{
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}
dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.firebase:firebase-messaging:24.0.1")
    implementation("androidx.activity:activity:1.8.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    implementation("androidx.recyclerview:recyclerview:1.3.1")
    // For control over item selection of both touch and mouse driven selection
    implementation("androidx.recyclerview:recyclerview-selection:1.2.0-alpha01")
    implementation ("com.squareup.okhttp3:okhttp:3.14.7")
// ViewModel and LiveData
    implementation ("androidx.lifecycle:lifecycle-extensions:2.2.0")
// alternatively - just ViewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1") // For Kotlin use lifecycle-viewmodel-ktx
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    //noinspection LifecycleAnnotationProcessorWithJava8
    annotationProcessor ("androidx.lifecycle:lifecycle-compiler:2.6.1") // For Kotlin use kapt instead of annotationProcessor
    implementation ("androidx.lifecycle:lifecycle-common-java8:2.6.1")
    implementation ("androidx.lifecycle:lifecycle-reactivestreams-ktx:2.6.1") // For Kotlin use lifecycle-reactivestreams-ktx
    testImplementation ("androidx.arch.core:core-testing:2.2.0")

    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    implementation ("androidx.cardview:cardview:1.0.0")
    implementation ("com.squareup.picasso:picasso:2.8")
    implementation ("de.hdodenhof:circleimageview:3.1.0")
    implementation ("io.insert-koin:koin-android:3.2.0")
    implementation ("io.insert-koin:koin-android-compat:3.2.0")
    // Jetpack WorkManager
    implementation ("io.insert-koin:koin-androidx-workmanager:3.2.0")
    // Navigation Graph
    implementation ("io.insert-koin:koin-androidx-navigation:3.2.0")

    //retrofit
    implementation ("com.google.code.gson:gson:2.8.9")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:adapter-rxjava2:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.7.2")

    implementation ("io.reactivex.rxjava2:rxandroid:2.1.1")
    implementation ("io.reactivex.rxjava2:rxjava:2.2.9")
    implementation ("com.jakewharton.timber:timber:4.7.1")

    implementation ("androidx.navigation:navigation-fragment-ktx:2.7.0")
    implementation ("androidx.navigation:navigation-ui-ktx:2.7.0")
    implementation ("androidx.navigation:navigation-runtime-ktx:2.7.0")
    //implementation ("com.facebook.fresco:fresco:1.11.0")
    implementation ("com.facebook.fresco:fresco:3.0.0")
    //implementation ("com.tbuonomo.andrui:viewpagerdotsindicator:4.1.2")
    implementation("com.tbuonomo:dotsindicator:5.0")
    implementation ("androidx.viewpager2:viewpager2:1.0.0")
    implementation ("androidx.dynamicanimation:dynamicanimation:1.0.0")
    implementation ("org.greenrobot:eventbus:3.3.1")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")
    implementation ("androidx.browser:browser:1.6.0")

    implementation ("androidx.room:room-runtime:2.5.2")
    implementation ("androidx.room:room-rxjava2:2.5.2")
    ksp("androidx.room:room-compiler:2.5.0")
    implementation ("com.github.smarteist:autoimageslider:1.4.0")
    implementation ("com.github.bumptech.glide:glide:4.11.0")
    implementation ("androidx.drawerlayout:drawerlayout:1.2.0")
    implementation ("com.github.SirLordPouya:PersianLinearDatePicker:1.3.0")
    implementation ("com.github.samanzamani:PersianDate:1.5.4")
    implementation ("com.github.dann41:luckywheel_android:0.3.1")
    implementation ("com.github.lukedeighton:wheelview:0.4.2")
    implementation ("androidx.swiperefreshlayout:swiperefreshlayout:1.2.0-alpha01")
    implementation ("com.github.ybq:Android-SpinKit:1.4.0")
//-1
    implementation ("androidx.appcompat:appcompat:1.6.0")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.0")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.0")
}

