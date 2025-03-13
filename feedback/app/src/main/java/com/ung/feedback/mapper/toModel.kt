package com.ung.feedback.mapper

import com.ung.feedback.database.PostEntity
import com.ung.feedback.model.Post
import java.time.Instant


fun PostEntity.toModel() =Post(
    id= id,
    title = title,
    author = author,
    publishDate = Instant.now(),
    content = content,
  )

fun List<PostEntity>.toModel()= map(PostEntity::toModel)
