package com.ung.feedback.ui

import androidx.compose.runtime.Immutable
import com.ung.feedback.database.PostEntity

@Immutable
data class HomeScreenUiState(
  val isLoading: Boolean = true,
  val errorMessage: String? = null,
  val posts: List<PostEntity>,
)
