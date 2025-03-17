/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.sqldemo

import android.app.Application
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.provider.BaseColumns
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sqldemo.sqlite.DbOpener
import com.example.sqldemo.sqlite.FReaderContract
import com.example.sqldemo.ui.theme.SQLDemoTheme
import com.example.sqldemo.user.HomeScreen
import com.example.sqldemo.user.NewActivityScreen
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.Instant

class MainActivity : ComponentActivity() {
  val dbOpener = DbOpener(this)

  lateinit var mainApplication: AndroidApplication

  override fun onCreate(savedInstanceState : Bundle?) {
    super.onCreate(savedInstanceState)
    val DbWriter = dbOpener.writableDatabase
    var col1 : String? = null
    var col2 : String? = null
    val values = ContentValues().apply {
      put(FReaderContract.FEntry.COLUMN_NAME_TITLE ,
          col1
      )
      put(FReaderContract.FEntry.COLUMN_NAME_SUBTITLE ,
          col2)
    }
    val newRowId = DbWriter?.insert(FReaderContract.FEntry.TABLE_NAME ,
                                    null ,
                                    values
    )
    val dbRead = dbOpener.readableDatabase
    GlobalScope.launch {
      AppDatabase.getDatabase(applicationContext)
        .emailDao()
        .getAll()
    }
    dbOpener.exportSchemaToSQLFile(this)
    val projection = arrayOf(BaseColumns._ID ,
                             FReaderContract.FEntry.COLUMN_NAME_TITLE ,
                             FReaderContract.FEntry.COLUMN_NAME_SUBTITLE)
    val selection = "${FReaderContract.FEntry.COLUMN_NAME_TITLE} = ?"
    val selectionArgs = arrayOf("My Title")
    val sortOrder = "${FReaderContract.FEntry.COLUMN_NAME_SUBTITLE} DESC"
    val cursor = dbRead?.query(
      FReaderContract.FEntry.TABLE_NAME ,   // The table to query
      projection ,             // The array of columns to return (pass null to get all)
      selection ,              // The columns for the WHERE clause
      selectionArgs ,          // The values for the WHERE clause
      null ,                   // don't group the rows
      null ,                   // don't filter by row groups
      sortOrder               // The sort order
    )
    val itemIds = mutableListOf<Long>()

    fun dataClickevent() {
      col1 = "hi11"
      col2 = "ww111" + Instant.now()
      val values2 = ContentValues().apply {
        put(FReaderContract.FEntry.COLUMN_NAME_TITLE ,
            col1
        )
        put(FReaderContract.FEntry.COLUMN_NAME_SUBTITLE ,
            col2)
      }
      val newRowId2 = DbWriter?.insert(FReaderContract.FEntry.TABLE_NAME ,
                                       null ,
                                       values2
      )
    }

var application = application as AndroidApplication


    setContent {
      SQLDemoTheme {
        // A surface container using the 'background' color from the theme
        Surface(
          modifier = Modifier.fillMaxSize() ,
          color = MaterialTheme.colors.background
        ) {
          val navController = rememberNavController()
          val userViewModel = application.user
          NavHost(
            navController = navController,
            startDestination = Screen.Home.route
          ) {
            mainApplication.
            composable(Screen.Home.route) {
              HomeScreen(   navController ,
                         userViewModel = mainApplication.userViewModel)
            }
            composable(
              route = "${Screen.Detail.route}/{userId}" ,
              arguments = listOf(
                navArgument("userId") {
                  type = NavType.IntType
                }
              )
            ) { backStackEntry ->
              val userId = backStackEntry.arguments?.getInt("userId")
              DetailScreen(
                navController = navController ,
                userViewModel = mainApplication.userViewModel ,
                userId = userId
              )
            }
            composable(Screen.NewActivity.route) {
              NewActivityScreen(navController)
            }
          }
          Box(
            modifier = Modifier.fillMaxSize() ,
            contentAlignment = Alignment.Center
          ) {
            Text("The database is ready!")
            Text(itemIds.toString())
            Button(onClick = { dataClickevent() })
            {
              Row {
                Text(projection.size.toString())
              }
            }
          }
        }
      }
    }
  }

  override fun onDestroy() {
    super.onDestroy()
  }

}

