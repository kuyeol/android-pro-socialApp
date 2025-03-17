package com.example.sqldemo

import android.app.Application
import android.content.Context




class AndroidApplication : Application() {

  companion object {
    private lateinit var instance:  AndroidApplication

    val appContext: Context by lazy { instance.applicationContext }
  }
  override fun onCreate() {

    instance = this
    super.onCreate()
  }

}
