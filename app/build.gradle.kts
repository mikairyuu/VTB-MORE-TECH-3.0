plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "ru.vtb.moretech"
        minSdk = 22
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
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
        kotlinCompilerExtensionVersion = libs.versions.compose.version.get()
    }
    packagingOptions {
       resources.excludes.add("META-INF/*.kotlin_module")
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.core.splash)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    implementation(libs.compose.ui.core)
    implementation(libs.compose.material)
    implementation(libs.compose.ui.preview)
    implementation(libs.compose.activity)

    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation)
    implementation(project(mapOf("path" to ":shared:stats")))
    kapt(libs.hilt.compiler)

    implementation("com.google.accompanist:accompanist-navigation-animation:0.19.0")
    implementation("com.google.accompanist:accompanist-pager:0.19.0")

    debugImplementation(libs.compose.ui.tooling)

    implementation(projects.libraries.network)
    implementation(projects.libraries.storage)
    implementation(projects.shared.day)
    implementation(projects.shared.auth)
}