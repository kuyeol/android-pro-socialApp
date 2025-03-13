package com.ung.feedback.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.ung.feedback.ui.HomeScreenUiState
import com.ung.feedback.viewmodel.HomeViewModel

class Home {
}

/**
 * @see com.ung.feedback.FeedBack
 * @sample com.ung.feedback.FeedBack.create.App
 */
@Composable
fun HomeScreen(
  uiState: HomeScreenUiState,
  windowSizeClass: WindowSizeClass,
  navigateToPlayer: () -> Unit,
  viewModel: HomeViewModel = hiltViewModel()
) {
//  val homeScreenUiState by viewModel.state.collectAsStateWithLifecycle()
//  val uiState = homeScreenUiState

  Box {
    Text("홈스크린")
  }
}
