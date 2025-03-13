package com.ung.feedback.navigate
import android.app.Activity
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import kotlinx.coroutines.CoroutineScope

class NavGraph {}
//@Composable
//fun TodoNavGraph(
//  modifier: Modifier = Modifier,
//  navController: NavHostController = rememberNavController(),
//  coroutineScope: CoroutineScope = rememberCoroutineScope(),
//  drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
//  startDestination: String = Destinations.STATISTICS_ROUTE,
//  navActions: TodoNavigationActions = remember(navController) {
//    TodoNavigationActions(navController)
//  }
//) {
//  val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
//  val currentRoute = currentNavBackStackEntry?.destination?.route ?: startDestination
//
//  NavHost(
//    navController = navController,
//    startDestination = startDestination,
//    modifier = modifier
//  ) {
//    composable(
//      Destinations.STATISTICS_ROUTE,
//      arguments = listOf(
//        navArgument(USER_MESSAGE_ARG) { type = NavType.IntType; defaultValue = 0 }
//      )
//    ) { entry ->
//      AppModalDrawer(drawerState, currentRoute, navActions) {
//        TasksScreen(
//          userMessage = entry.arguments?.getInt(USER_MESSAGE_ARG)!!,
//          onUserMessageDisplayed = { entry.arguments?.putInt(USER_MESSAGE_ARG, 0) },
//          onAddTask = { navActions.navigateToAddEditTask(R.string.add_task, null) },
//          onTaskClick = { task -> navActions.navigateToTaskDetail(task.id) },
//          openDrawer = { coroutineScope.launch { drawerState.open() } }
//        )
//      }
//    }
//    composable(TodoDestinations.STATISTICS_ROUTE) {
//      AppModalDrawer(drawerState, currentRoute, navActions) {
//        StatisticsScreen(openDrawer = { coroutineScope.launch { drawerState.open() } })
//      }
//    }
//    composable(
//      TodoDestinations.ADD_EDIT_TASK_ROUTE,
//      arguments = listOf(
//        navArgument(TITLE_ARG) { type = NavType.IntType },
//        navArgument(TASK_ID_ARG) { type = NavType.StringType; nullable = true },
//      )
//    ) { entry ->
//      val taskId = entry.arguments?.getString(TASK_ID_ARG)
//      AddEditTaskScreen(
//        topBarTitle = entry.arguments?.getInt(TITLE_ARG)!!,
//        onTaskUpdate = {
//          navActions.navigateToTasks(
//            if (taskId == null) ADD_EDIT_RESULT_OK else EDIT_RESULT_OK
//          )
//        },
//        onBack = { navController.popBackStack() }
//      )
//    }
//    composable(TodoDestinations.TASK_DETAIL_ROUTE) {
//      TaskDetailScreen(
//        onEditTask = { taskId ->
//          navActions.navigateToAddEditTask(R.string.edit_task, taskId)
//        },
//        onBack = { navController.popBackStack() },
//        onDeleteTask = { navActions.navigateToTasks(DELETE_RESULT_OK) }
//      )
//    }
//  }
//}

// Keys for navigation
//const val ADD_EDIT_RESULT_OK = Activity.RESULT_FIRST_USER + 1
//const val DELETE_RESULT_OK = Activity.RESULT_FIRST_USER + 2
//const val EDIT_RESULT_OK = Activity.RESULT_FIRST_USER + 3
