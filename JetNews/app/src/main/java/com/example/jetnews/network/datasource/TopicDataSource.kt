package com.example.jetnews.network.datasource

import com.example.jetnews.model.Topic

interface TopicDataSource {

  suspend fun getTopics(ids: List<String>? = null): Result<Topic>

//  suspend fun getNewsResources(ids: List<String>? = null): List<NetworkNewsResource>
//
//  suspend fun getTopicChangeList(after: Int? = null): List<NetworkChangeList>
//
//  suspend fun getNewsResourceChangeList(after: Int? = null): List<NetworkChangeList>

}
