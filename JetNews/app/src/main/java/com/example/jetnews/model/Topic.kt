package com.example.jetnews.model

data class Topic(
  val id: String,
  val name: String,
  val shortDescription: String,
  val longDescription: String,
  val imageUrl: String,
  val url: String,
  val newslist :List<News> ,
)
