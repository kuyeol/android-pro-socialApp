package com.example.sqldemo.user

import androidx.room.Dao
import androidx.room.Query
import com.example.jetcaster.core.data.database.dao.BaseDao
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao : BaseDao<UserEntity> {

  @Query("SELECT * FROM users")
  fun getAllList(): Flow<List<UserEntity>>

  @Query("SELECT * FROM users WHERE id = :arg")
  override suspend fun findById(arg : String): Flow<UserEntity?>




}
