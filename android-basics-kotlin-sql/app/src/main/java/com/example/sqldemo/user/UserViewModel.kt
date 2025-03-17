package com.example.sqldemo.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.util.UUID
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class UserViewModel(private val repository : UserRepository) : ViewModel() {
  val allUsers = repository.allUsers
  private val userId = MutableStateFlow(0)

  @OptIn(ExperimentalCoroutinesApi::class)
  private val user = userId.flatMapLatest { id -> repository.findUserById(id.toString()) }

  suspend fun insert(user : UserEntity) {
    viewModelScope.launch {
      repository.insert(user)
    }
  }

  fun toggleFavorite(id : String , isFavorite : Boolean) {
    viewModelScope.launch {
      repository.updateFavorite(id , isFavorite)
    }
  }

  fun updateFavorite(id : String ,
                             isFavorite : Boolean) {
    viewModelScope.launch {
      repository.updateFavorite(id ,
                                isFavorite)
    }
  }

  suspend fun delete(user : UserEntity) {
    viewModelScope.launch {
      repository.delete(user)
    }
  }
}
