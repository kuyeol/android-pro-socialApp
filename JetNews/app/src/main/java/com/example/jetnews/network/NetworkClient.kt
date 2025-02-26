package com.example.jetnews.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkClient {

  companion object {

    fun makeBasic(url: String): Retrofit {
      val retrofit = Retrofit.Builder().baseUrl(url).build()

      return retrofit
    }


    fun addConverter(url: String): Retrofit {

      val retrofit = Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build()
      return retrofit
    }

  }


}
