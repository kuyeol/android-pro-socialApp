plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.serialization)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.compose)
  alias(libs.plugins.room)
  alias(libs.plugins.ksp)
  alias(libs.plugins.hilt)
}

android {
  namespace = "com.ung.feedback"
  compileSdk = 35

  defaultConfig {
    applicationId = "com.ung.feedback"
    minSdk = 26
    targetSdk = 34
    versionCode = 1
    versionName = "1.0"

    javaCompileOptions {
      annotationProcessorOptions {
        arguments(mapOf("room.schemaLocation" to projectDir.resolve("schemas").toString()))
      }
    }

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
//  kotlinOptions {
//    // work-runtime-ktx 2.1.0 and above now requires Java 8
//    jvmTarget = JavaVersion.VERSION_17.toString()
//
//    // Enable Coroutines and Flow APIs
//    freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
//    freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlinx.coroutines.FlowPreview"
//  }

  buildFeatures {
    compose = true
    dataBinding = true
    buildConfig = true
    viewBinding = true
  }


//  packagingOptions {
//    // Multiple dependency bring these files in. Exclude them to enable
//    // our test APK to build (has no effect on our AARs)
//    resources.excludes += "/META-INF/AL2.0"
//    resources.excludes += "/META-INF/LGPL2.1"
//  }

}



room {
  schemaDirectory(projectDir.resolve("schemas").toString())
}


dependencies {
  implementation(project(":mylibrary"))

  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.appcompat)
  implementation(libs.material)
  implementation(libs.androidx.constraintlayout)
  implementation(libs.androidx.lifecycle.livedata.ktx)
  implementation(libs.androidx.lifecycle.viewmodel.ktx)
  implementation(libs.androidx.navigation.fragment.ktx)
  implementation(libs.androidx.navigation.ui.ktx)
  implementation(libs.androidx.lifecycle.runtime.ktx)
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.ui)
  implementation(libs.androidx.ui.graphics)
  implementation(libs.androidx.compose.ui.tooling.preview)
  implementation(libs.androidx.compose.material3)
  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
  androidTestImplementation(platform(libs.androidx.compose.bom))
  androidTestImplementation(libs.androidx.compose.ui.test)
  debugImplementation(libs.androidx.compose.ui.tooling)
  debugImplementation(libs.androidx.compose.ui.testManifest)
  ksp(libs.hilt.compiler)
  implementation(libs.androidx.activity.compose)
  implementation(libs.androidx.compose.material3.adaptive)
  implementation(libs.androidx.compose.material3.adaptive.layout)
  implementation(libs.androidx.compose.material3.adaptive.navigation)
  implementation(libs.androidx.compose.material3.windowSizeClass)
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.core.splashscreen)
  implementation(libs.androidx.hilt.navigation.compose)
  implementation(libs.androidx.lifecycle.runtimeCompose)
  implementation(libs.androidx.navigation.compose)
  implementation(libs.kotlinx.coroutines.guava)
  implementation(libs.kotlinx.serialization.json)
  implementation(libs.hilt.android)
  ksp(libs.hilt.android.compiler)
  implementation(libs.room.runtime)
  ksp(libs.room.compiler)

  implementation(libs.room.ktx)

  //추가
  implementation(libs.coil.compose)
  implementation(libs.coil.network.okhttp)
}
