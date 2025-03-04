


plugins {
	alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.serialization)
  alias(libs.plugins.ksp)
  alias(libs.plugins.hilt)
  alias(libs.plugins.compose)

}

android {
	namespace = "com.ung.feedback"
	compileSdk = 35

	defaultConfig {
		applicationId = "com.ung.feedback"
		minSdk = 26
		targetSdk = 35
		versionCode = 1
		versionName = "1.0"

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
  }
  packagingOptions {
    // Multiple dependency bring these files in. Exclude them to enable
    // our test APK to build (has no effect on our AARs)
    resources.excludes += "/META-INF/AL2.0"
    resources.excludes += "/META-INF/LGPL2.1"
  }

}

dependencies {
	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.appcompat)
	implementation(libs.material)
	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
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

}
