package com.ung.feedback.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow



interface BaseDao<T> {

  companion object {
    const val colName: String = "id"
    const val ALL_POST_QUERY: String = "SELECT * FROM Post ORDER BY + $colName ASC"
  }

  @Query(value = ALL_POST_QUERY)
  fun getPostList(): Flow<List<T>>

  @Query(ALL_POST_QUERY)
  suspend fun getPostById(postId: String): PostEntity?

  @Insert
  suspend fun insertPost(post: PostEntity)


  @Delete
  suspend fun deletePost(postId: PostEntity)

  @Upsert
  suspend fun upsertPost(post: PostEntity)


}

@Dao
interface PostDao : BaseDao<PostEntity> {

  companion object {
    const val colName: String = "author"
    const val ALL_POST_QUERY = "SELECT * FROM Post ORDER BY + $colName ASC"
    const val POST_ID_QUERY = "SELECT * from Post WHERE id = :postId"

  }

  @Query(value = ALL_POST_QUERY)
  override fun getPostList(): Flow<List<PostEntity>>

  @Query(POST_ID_QUERY)
  override  suspend fun getPostById(postId: String): PostEntity?


}
