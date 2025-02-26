package com.example.jetnews.ui.topic

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.jetnews.data.topic.TopicRepository
import androidx.lifecycle.ViewModel
import com.example.jetnews.network.NetworkClient
import com.example.jetnews.network.service.TopicApi

class TopicViewModel(
  application: Application
) : AndroidViewModel(application) {

  private val topicRepository = TopicRepository()

  val BACK_END: String = "http://10.0.2.2:8080"

  init {

    val client = NetworkClient.makeBasic(BACK_END)
    val serviceApi = client.create(TopicApi::class.java)


  }






}



