package com.example.jetnews.network.datasource.impl

import com.example.jetnews.model.Topic
import com.example.jetnews.network.datasource.TopicDataSource
import com.example.jetnews.network.service.TopicApi

class TopicDataSourceImpl(
  private val topicApi: TopicApi,
) : TopicDataSource {



  override suspend fun getTopics(ids: List<String>?): Result<Topic> {
    //TODO("Not yet implemented")
    try{

      return topicApi.getTopics().toResult()
    }
  }


}
