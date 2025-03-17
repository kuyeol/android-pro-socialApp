package com.example.sqldemo.user


import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {

  val allUsers: Flow<List<UserEntity>> = userDao.getAllList()


  suspend fun findUserById(userId: String) : Flow<UserEntity?> {
    return userDao.findById(userId)
  }

  suspend fun insert(user: UserEntity) {
    userDao.insert(user)
  }

  suspend fun updateFavorite(userId: String, isFavorite: Boolean) {
    userDao.upsert(userId,isFavorite)
  }

  suspend fun delete(user: UserEntity) {
    userDao.delete(user)
  }

}
