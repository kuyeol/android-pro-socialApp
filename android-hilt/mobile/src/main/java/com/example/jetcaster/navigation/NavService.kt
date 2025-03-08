package com.example.jetcaster.navigation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.example.jetcaster.Activity.AppState

class NavService

@Composable
fun NavService(
  appState: AppState,
  onShowSnackbar: suspend (String, String?) -> Boolean,
  modifier: Modifier = Modifier,
) {
  val navController = appState.navController
//  NavHost(
//    navController = navController,
//    //todo: 모듈에서 가져오기
//    s
// tartDestination = ForYouBaseRoute,
//    modifier = modifier,
//  )
}
