package com.ung.feedback

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope


@Composable
fun rememberAppState(
  coroutineScope: CoroutineScope = rememberCoroutineScope(),
  navController: NavHostController = rememberNavController(),
): AppState {

  return remember(
    navController,
    coroutineScope,

    ) {
    AppState(
      navController = navController,
      coroutineScope = coroutineScope,

      )
  }

}


@Stable
class AppState(
  val navController: NavHostController,
  coroutineScope: CoroutineScope,
) {
  private val previousDestination = mutableStateOf<NavDestination?>(null)

  //todo: 현재 경로
  val currentDestination: NavDestination?
    @Composable get() {
      // Collect the currentBackStackEntryFlow as a state
      val currentEntry = navController.currentBackStackEntryFlow.collectAsState(initial = null)

      // Fallback to previousDestination if currentEntry is null
      return currentEntry.value?.destination.also { destination ->
        if (destination != null) {
          previousDestination.value = destination
        }
      } ?: previousDestination.value
    }






}

