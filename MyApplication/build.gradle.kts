// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {

  id("com.android.application") version "8.2.2" apply false // Android Gradle Plugin 8.2.x
  id("com.android.library") version "8.2.2" apply false     // Android Gradle Plugin 8.2.x
  id("com.android.test") version "8.2.2" apply false        // Android Gradle Plugin 8.2.x
  id("androidx.baselineprofile") version "1.3.3" apply false // Baseline Profile Plugin
  id("org.jetbrains.compose") version "1.5.12" apply false   // Compose Multiplatform Plugin
  id("org.jetbrains.kotlin.jvm") version "2.1.0" apply false // Kotlin JVM Plugin
  id("org.jetbrains.kotlin.plugin.serialization") version "1.9.22" apply false // Kotlin Serialization Plugin
  id("com.dropbox.dependency-guard") version "0.5.0" apply false // Dependency Guard Plugin
  id("com.google.firebase.crashlytics") version "3.0.3" apply false // Firebase Crashlytics Plugin
  id("com.google.firebase.firebase-perf") version "1.4.2" apply false // Firebase Performance Plugin
  id("com.google.gms.google-services") version "4.4.2" apply false // Google Services Plugin
  id("com.google.dagger.hilt.android") version "2.55" apply false // Hilt Plugin
  id("com.google.devtools.ksp") version "2.1.10-1.0.29" apply false // KSP Plugin (Kotlin Symbol Processing)
  id("io.github.takahirom.roborazzi") version "1.40.1" apply false // Roborazzi Plugin
  id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin") version "2.0.1" apply false // Secrets Gradle Plugin
  id("androidx.room") version "2.6.1" apply false // Room Plugin
  id("com.jraska.module.graph.assertion") version "2.7.1" apply true // Module Graph Assertion Plugin
}
