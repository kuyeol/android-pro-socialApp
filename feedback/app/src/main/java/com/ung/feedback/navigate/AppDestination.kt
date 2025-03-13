package com.ung.feedback.navigate

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.ung.feedback.navigate.TodoScreens.STATISTICS_SCREEN
import kotlin.reflect.KClass

enum class AppDestinations(
  val selectedIcon: ImageVector,
  val unselectedIcon: ImageVector,
  @StringRes val iconTextId: Int,
  @StringRes val titleTextId: Int,
  val route: KClass<*>,
  val baseRoute: KClass<*> = route,
) {



}


private object TodoScreens {
  const val TASKS_SCREEN = "tasks"
  const val STATISTICS_SCREEN = "statistics"
  const val TASK_DETAIL_SCREEN = "task"
  const val ADD_EDIT_TASK_SCREEN = "addEditTask"
}


object Destinations {

  const val STATISTICS_ROUTE = STATISTICS_SCREEN

}
