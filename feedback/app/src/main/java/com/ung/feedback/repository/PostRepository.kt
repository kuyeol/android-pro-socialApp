package com.ung.feedback.repository

import androidx.room.Insert
import com.ung.feedback.database.PostEntity
import com.ung.feedback.database.PostDao
import com.ung.feedback.di.ApplicationScope
import com.ung.feedback.di.DefaultDispatcher
import com.ung.feedback.mapper.toModel
import com.ung.feedback.model.Post
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.time.Instant
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostRepository @Inject constructor(
  private val postdao: PostDao,
  @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
  @ApplicationScope private val scope: CoroutineScope,
) : IPostRepository {





  override fun getPostStream(): Flow<List<Post>> {
    TODO("Not yet implemented")
  }

  override suspend fun getPostList(): Flow<List<Post>> {
    TODO("Not yet implemented")
    return postdao.getPostList().map { posts->
      withContext (dispatcher){
        posts.toModel()
      }
    }

  }

  override suspend fun getPostById(id: String): Flow<Post> {
    TODO("Not yet implemented")
  }

  override suspend fun newPost(content: Post) {
    TODO("Not yet implemented")
    val post = PostEntity(
      id= content.id,
      title = content.title,
      author = content.author,
      publishDate = Instant.now(),
      content = content.content,
    )

    val model= Post(
      id= content.id,
      title = content.title,
      author = content.author,
      publishDate = Instant.now(),
      content = content.content,
    )
    postdao.insertPost(post)

  }

  override suspend fun editPost(content: Post) {
    TODO("Not yet implemented")
  }

  override suspend fun deletePost(id: String) {
    TODO("Not yet implemented")
  }


}
