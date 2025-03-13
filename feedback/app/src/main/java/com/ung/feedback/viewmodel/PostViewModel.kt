package com.ung.feedback.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ung.feedback.PostsTypeFilter
import com.ung.feedback.R
import com.ung.feedback.model.Post
import com.ung.feedback.repository.IPostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.ung.feedback.PostsTypeFilter.ALL_POST
import com.ung.feedback.PostsTypeFilter.ACTIVE_TASKS
import com.ung.feedback.PostsTypeFilter.COMPLETED_TASKS
import com.ung.feedback.util.WhileUiSubscribed
import com.ung.feedback.util.combine
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.jetbrains.annotations.Async


data class PostsUiState(
  val items: List<Post> = emptyList(),
  val isLoading: Boolean = false,
  val filteringUiInfo: FilteringUiInfo = FilteringUiInfo(),
  val userPost: Int? = null
)
data class FilteringUiInfo(
  val currentFilteringLabel: Int = R.string.label_all,

)
const val TASKS_FILTER_SAVED_STATE_KEY = "TASKS_FILTER_SAVED_STATE_KEY"


class PostViewModel (
  private val postRepo: IPostRepository,
) : ViewModel(){

  val postList: LiveData<List<Post>> =postRepo.getPostStream().asLiveData()

  fun makePost(post:Post) = viewModelScope.launch {
    postRepo.newPost(post)
  }


}
