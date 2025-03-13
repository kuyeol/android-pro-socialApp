package com.ung.feedback

import android.app.Application
import android.content.Context
import android.content.pm.ApplicationInfo
import android.os.StrictMode
import coil3.ImageLoader
import coil3.SingletonImageLoader
import coil3.request.crossfade
import com.ung.feedback.database.PostDatabase
import com.ung.feedback.repository.IPostRepository
import com.ung.feedback.repository.PostRepository
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Inject
import android.os.StrictMode.ThreadPolicy.Builder

@HiltAndroidApp
class AndroidApplication : Application() , SingletonImageLoader.Factory {
  override fun onCreate() {
    super.onCreate()
    setStrictModePolicy()
  }

  override fun newImageLoader(context : Context) : ImageLoader {
    return ImageLoader.Builder(context)
      .crossfade(true)
      .build()
  }

  fun isDebuggable() : Boolean {
    return 0 != applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE
  }

  private fun setStrictModePolicy() {
    if (isDebuggable()) {
      StrictMode.setThreadPolicy(
        Builder().detectAll()
          .penaltyLog()
          .penaltyDeath()
          .build() ,
      )
    }
  }

}
