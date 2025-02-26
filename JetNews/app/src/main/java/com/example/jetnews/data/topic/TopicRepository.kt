package com.example.jetnews.data.topic

import com.example.jetnews.model.Topic
import kotlinx.coroutines.flow.MutableStateFlow

class TopicRepository {


  private val topicList= MutableStateFlow<Topic?>(null)

//  suspend fun getTopics(id : String?): Flow<List<Topic>> {
//
//  }
    private var requestCount = 0

    private fun shouldRandomlyFail(): Boolean = ++requestCount % 5 == 0


}
