package com.ung.feedback.database;

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant


@Entity(tableName = "Post")
data class PostEntity(
  @PrimaryKey
  val id: String,
  val author: String,
  val title: String,
  val publishDate: Instant,
  val content: String,
  )
