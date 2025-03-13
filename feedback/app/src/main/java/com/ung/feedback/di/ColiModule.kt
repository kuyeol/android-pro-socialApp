package com.ung.feedback.di

import android.app.Application
import android.content.Context
import coil3.ImageLoader
import coil3.SingletonImageLoader
import coil3.disk.DiskCache
import coil3.disk.directory
import coil3.memory.MemoryCache
import coil3.request.crossfade
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoilModule {
  @Provides
  @Singleton
  fun providesImageLoader(context : Context) : ImageLoader {
    return ImageLoader.Builder(context)
      .memoryCache {
        MemoryCache.Builder()
          .maxSizePercent(context)
          .build()
      }
      .diskCache {
        DiskCache.Builder()
          .directory(context.cacheDir.resolve("image_cache"))
          .maxSizePercent(0.02)
          .build()
      }
      .crossfade(true)
      .build()
  }

}
