package com.ung.feedback.repository

import com.ung.feedback.model.Post
import kotlinx.coroutines.flow.Flow

interface IPostRepository {

  fun getPostStream(): Flow<List<Post>>
 suspend fun getPostList():Flow<List<Post>>

  suspend fun getPostById(id:String):Flow<Post>

  suspend fun newPost(content: Post)

  suspend fun editPost(content: Post)

  suspend fun deletePost(id: String)
}
