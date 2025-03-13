package com.ung.feedback.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.ung.feedback.R
import com.ung.feedback.model.Post
import com.ung.feedback.repository.IPostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import java.time.Instant



class HomeViewModel @Inject constructor(
  private val postRepo: IPostRepository,
  savedStateHandle: SavedStateHandle,

  ) : ViewModel() {
  private val _post: MutableStateFlow<Int?> = MutableStateFlow(null)
  private val _isLoading = MutableStateFlow(false)
  private val _isTaskDeleted = MutableStateFlow(false)
}

data class PostUiState(
  val post: Post? = null, val isLoading: Boolean? = false
)
