package com.example.jetcaster.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import androidx.window.layout.DisplayFeature
import com.google.accompanist.adaptive.calculateDisplayFeatures


data class Profile(var name: String)

object ProfileRoute

@Composable
fun AnyScreen(
  profile: Profile, onNavigete: () -> Unit

) {
  Text("PROFILE")
  Button(onClick = { onNavigete }) {
    Text("Next Navigate")
  }
}


sealed class MainTheme {

  object Material {

    @Composable
    @OptIn(ExperimentalMaterial3AdaptiveApi::class)
    fun Bulid(displayFeatures: List<DisplayFeature>) {
      val navigate = rememberNavController()

      NavHost(navigate, startDestination = Profile("스타트데스티네이션")) {
        composable<Profile> { stack ->
          val profile: Profile = stack.toRoute()
          AnyScreen(profile = profile, onNavigete = { navigate.navigate(route = ProfileRoute) })
        }
      }

    }


  }

}


