package com.example.jetcaster.di

import android.app.Activity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import android.util.Log
import android.view.Window




@Module
@InstallIn(ActivityComponent::class)
object AppStateModule {


  @Provides
  fun providesWindow(activity: Activity): Window = activity.window


}
