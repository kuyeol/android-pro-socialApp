package com.google.samples.apps.nowinandroid.core.network.api
import androidx.tracing.trace
import com.google.samples.apps.nowinandroid.core.network.BuildConfig
import com.google.samples.apps.nowinandroid.core.network.NiaNetworkDataSource
import com.google.samples.apps.nowinandroid.core.network.model.NetworkChangeList
import com.google.samples.apps.nowinandroid.core.network.model.NetworkNewsResource
import com.google.samples.apps.nowinandroid.core.network.model.NetworkTopic
import com.google.samples.apps.nowinandroid.core.network.retrofit.NetworkResponse
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton


interface TopicApi {
  @GET(value = "topics")
  suspend fun getTopics(
    @Query("id") ids: List<String>?,
  ): NetworkResponse<List<NetworkTopic>>

  @GET(value = "newsresources")
  suspend fun getNewsResources(
    @Query("id") ids: List<String>?,
  ): NetworkResponse<List<NetworkNewsResource>>

  @GET(value = "changelists/topics")
  suspend fun getTopicChangeList(
    @Query("after") after: Int?,
  ): List<NetworkChangeList>

  @GET(value = "changelists/newsresources")
  suspend fun getNewsResourcesChangeList(
    @Query("after") after: Int?,
  ): List<NetworkChangeList>
}
