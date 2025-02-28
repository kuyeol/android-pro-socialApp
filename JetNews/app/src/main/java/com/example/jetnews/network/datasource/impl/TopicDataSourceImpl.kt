package com.example.jetnews.network.datasource.impl

import com.example.jetnews.model.Topic
import com.example.jetnews.network.datasource.TopicDataSource
import com.example.jetnews.network.service.TopicApi
import retrofit2.Response


class TopicDataSourceImpl(
  private val topicApi: TopicApi,

) : TopicDataSource {



  override suspend fun getTopics(ids: List<String>?): Result<Topic> {
    //TODO("Not yet implemented")
    try{

      return topicApi.getTopics().toResult()
    }
    catch (e:Throwable){
      return e.toResult()
    }
  }


fun <T>  Response<T>.toResult() : Result<T> {
    return Result.success(body()!!)
  }


  private fun <T> Throwable.toResult(): Result<T> {
    return Result.failure(this)
  }
}
