package com.ung.feedback.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ung.feedback.repository.IPostRepository

class ViewModelFactory(private val repository: IPostRepository) : ViewModelProvider.Factory {
  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    if (modelClass.isAssignableFrom(PostViewModel::class.java)) {
      @Suppress("UNCHECKED_CAST")
      return PostViewModel(repository) as T
    }
    throw IllegalArgumentException("Unknown ViewModel class")
  }
}
