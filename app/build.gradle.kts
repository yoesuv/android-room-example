plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.ksp)
    base
}

val appApplicationId = "com.yoesuv.androidroom"
val appVersionName = "2.1.9"

base {
    archivesName = "$appApplicationId-v$appVersionName"
}

android {
    namespace = appApplicationId
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        applicationId = appApplicationId
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = appVersionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        dataBinding = true
        buildConfig = true
        resValues = true
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

androidComponents {
    onVariants { variant ->
        variant.outputs.forEach { output ->
            val appId = variant.applicationId.get()
            val versionName = output.versionName.get()
            output.outputFileName.set("$appId-v$versionName-${variant.name}.apk")
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