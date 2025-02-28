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

  val BACK_END: String = "http://182.218.135.247:8081/android"

  init {

    val client = NetworkClient.makeBasic(BACK_END)
    val serviceApi = client.create(TopicApi::class.java)


  }






}



