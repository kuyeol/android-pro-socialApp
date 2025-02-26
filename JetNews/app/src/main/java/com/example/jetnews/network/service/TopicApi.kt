package com.example.jetnews.network.service

import com.example.jetnews.model.Topic
import retrofit2.Response

import retrofit2.http.GET

interface TopicApi {

  @GET("topics")
  suspend fun getTopics(): Response<Topic>

}
