plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.jetbrainsKotlinKapt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.yoesuv.androidroom"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.yoesuv.androidroom"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "2.1.8"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        setProperty("archivesBaseName", "$applicationId-v$versionCode($versionName)")
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        release {
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
        dataBinding = true
        buildConfig = true
    }

    flavorDimensions.add("default")
    productFlavors {
        create("forTest") {
            resValue("string", "app_name", "Android Room TEST")
            applicationIdSuffix = ".test"
            dimension = "default"
        }
        create("production") {
            resValue("string", "app_name", "Android Room")
            dimension = "default"
            isDefault = true
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.espresso.contrib)

    implementation(libs.androidx.lifecycle)
    implementation(libs.androidx.room)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
}