/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.jetcaster.Activity


import androidx.compose.animation.EnterTransition
import androidx.compose.animation.scaleOut
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.window.layout.DisplayFeature
import com.example.jetcaster.R
import com.example.jetcaster.ui.ChatBubble
import com.example.jetcaster.ui.Message

data class AppColors(
  val primary: Color = Color.Blue,
  val onPrimary: Color = Color.White
)

val LocalAppColors = staticCompositionLocalOf { AppColors() }

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ActivityController(
  displayFeatures: List<DisplayFeature>,
  appState: AppState = rememberJetcasterAppState()
) {
  val appColors = AppColors(primary = Color.Green)
  val adaptiveInfo = currentWindowAdaptiveInfo()
  if (appState.isOnline) {
    NavHost(
      navController = appState.navController,
      startDestination = Screen.Home.route,
      popExitTransition = { scaleOut(targetScale = 0.9f) },
      popEnterTransition = { EnterTransition.None }
    ) {

      composable(Screen.Home.route) {
        backStackEntry ->
        CompositionLocalProvider(
          LocalAppColors provides appColors) {
          MyScreen()
        }
        Text("Home")
      }

    }
  } else {
    OfflineDialog { appState.refreshOnline() }
  }
}

@Composable
fun MyScreen() {
  // 암묵적으로 색상에 접근
  val colors = LocalAppColors.current
var strMe = Message("sss","dfff","dddd")
  Text(text = "안녕하세요", color = colors.onPrimary)
  ChatBubble(strMe)
}

@Composable
fun OfflineDialog(onRetry: () -> Unit) {
  AlertDialog(
    onDismissRequest = {},
    title = { Text(text = stringResource(R.string.connection_error_title)) },
    text = { Text(text = stringResource(R.string.connection_error_message)) },
    confirmButton = {
      TextButton(onClick = onRetry) {
        Text(stringResource(R.string.retry_label))
      }
    }
  )
}
