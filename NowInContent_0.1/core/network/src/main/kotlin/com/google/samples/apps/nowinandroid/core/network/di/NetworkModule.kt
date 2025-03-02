/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.samples.apps.nowinandroid.core.network.di

import android.content.Context
import androidx.tracing.trace
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.util.DebugLogger
import com.google.samples.apps.nowinandroid.core.network.BuildConfig
import com.google.samples.apps.nowinandroid.core.network.demo.DemoAssetManager
import com.google.samples.apps.nowinandroid.core.network.retrofit.RetrofitNiaNetworkApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {


  //todo: start
  @Provides
  @Singleton
  fun provideRetrofit(okHttpClient: OkHttpClient, networkJson: Json): Retrofit {
    return Retrofit.Builder()
      .baseUrl("https://your-backend-api.com/") // 실제 URL로 변경
      .client(okHttpClient)
      .addConverterFactory(
        networkJson.asConverterFactory("application/json".toMediaType())
      )
      .build()
  }

  @Provides
  @Singleton
  fun provideNiaNetworkApi(retrofit: Retrofit): RetrofitNiaNetworkApi {
    return retrofit.create(RetrofitNiaNetworkApi::class.java)
  }
  //todo: end


  @Provides
  @Singleton
  fun providesDemoAssetManager(
    @ApplicationContext context: Context,
  ): DemoAssetManager = DemoAssetManager(context.assets::open)



  @Provides
  @Singleton
  fun providesNetworkJson(): Json = Json {
    ignoreUnknownKeys = true
  }


  @Provides
  @Singleton
  fun okHttpCallFactory(): Call.Factory = trace("NiaOkHttpClient") {
    OkHttpClient.Builder()
      .addInterceptor(
        HttpLoggingInterceptor()
          .apply {
            if (BuildConfig.DEBUG) {
              setLevel(HttpLoggingInterceptor.Level.BODY)
            }
          },
      )
      .build()
  }

  /**
   * Since we're displaying SVGs in the app, Coil needs an ImageLoader which supports this
   * format. During Coil's initialization it will call `applicationContext.newImageLoader()` to
   * obtain an ImageLoader.
   *
   * @see <a href="https://github.com/coil-kt/coil/blob/main/coil-singleton/src/main/java/coil/Coil.kt">Coil</a>
   */
  @Provides
  @Singleton
  fun imageLoader(
    // We specifically request dagger.Lazy here, so that it's not instantiated from Dagger.
    okHttpCallFactory: dagger.Lazy<Call.Factory>,
    @ApplicationContext application: Context,
  ): ImageLoader = trace("NiaImageLoader") {
    ImageLoader.Builder(application)
      .callFactory { okHttpCallFactory.get() }
      .components { add(SvgDecoder.Factory()) }
      // Assume most content images are versioned urls
      // but some problematic images are fetching each time
      .respectCacheHeaders(false)
      .apply {
        if (BuildConfig.DEBUG) {
          logger(DebugLogger())
        }
      }
      .build()
  }
}
