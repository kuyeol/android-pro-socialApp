package com.example.jetcaster.navigation

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.jetcaster.designsystem.icon.IconsPack
import kotlin.reflect.KClass
import com.example.jetcaster.R

enum class NavItem(
  val selectedIcon: ImageVector,
  val unselectedIcon: ImageVector,
  @StringRes val iconTextId: Int,
  @StringRes val titleTextId: Int,
  val route: KClass<*>,
  val baseRoute: KClass<*> = route,
) {
  FOR_YOU(
    selectedIcon = IconsPack.Check,
    unselectedIcon = IconsPack.Close
   // iconTextId = R.string.,
    titleTextId = R.string.app_name,
    //todo: feature 패키지 모듈 생성하여 추가
//    route = ForYouRoute::class,
//    baseRoute = ForYouBaseRoute::class,
  ),

}
