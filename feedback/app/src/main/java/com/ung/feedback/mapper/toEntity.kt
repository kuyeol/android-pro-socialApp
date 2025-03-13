package com.ung.feedback.mapper

import com.ung.feedback.database.PostEntity
import com.ung.feedback.model.Post
import java.time.Instant

fun Post.toEntity()= PostEntity(
  id= id,
  title = title,
  author = author,
  publishDate = Instant.now(),
  content = content,
)

fun List<Post>.toEntity()= map{ Post::toEntity }
