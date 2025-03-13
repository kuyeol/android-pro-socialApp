package com.ung.feedback

import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.window.layout.DisplayFeature
import com.ung.feedback.screen.HomeScreen
import com.ung.feedback.viewmodel.HomeViewModel

class FeedBack {

  object create {

    @Composable
    @OptIn(ExperimentalMaterial3AdaptiveApi::class)
    fun App(
      displayFeatures: List<DisplayFeature>,
      appState: AppState = rememberAppState()
    ) {
      val adaptiveInfo = currentWindowAdaptiveInfo()
      val homeViewModel: HomeViewModel = viewModel()




    }
  }

}


