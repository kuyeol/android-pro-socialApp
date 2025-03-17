package com.example.sqldemo

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sqldemo.user.DetailScreen
import com.example.sqldemo.user.HomeScreen
import com.example.sqldemo.user.NewActivityScreen
import com.example.sqldemo.user.UserEntity
import com.example.sqldemo.user.UserViewModel

class Navgraph {
}
sealed class Screen(val route: String) {
  object Home : Screen("home")
  object Detail : Screen("detail")
  object Settings : Screen("settings")
  object NewActivity : Screen("new_activity")
}

@Composable
fun NavGraph(navController: NavHostController) {
  NavHost(navController = navController, startDestination = Screen.Home.route) {
    composable(Screen.Home.route) { HomeScreen(navController) }
    composable(Screen.Detail.route) { DetailScreen(navController) }
   // composable(Screen.Settings.route) { SettingsScreen(navController) }
    composable(Screen.NewActivity.route) { NewActivityScreen(navController) }
  }
}




