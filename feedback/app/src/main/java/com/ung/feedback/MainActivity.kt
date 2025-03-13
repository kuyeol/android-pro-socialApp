package com.ung.feedback

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.ung.feedback.ui.theme.FeedBackTheme
import com.ung.feedback.viewmodel.PostViewModel
import com.ung.feedback.viewmodel.ViewModelFactory
import com.ung.mylibrary.AccountRow
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {

      FeedBackTheme {

        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

          Column(
            modifier = Modifier
              .fillMaxSize()
              .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

          ) {
            Row {

              AccountRow(modifier = Modifier.fillMaxWidth(), "1", 1)

            }

            Greeting(
              name = "Android",
              modifier = Modifier.padding(innerPadding)
            )
          }
        }


      }
    }
  }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
  Text(
    text = "Hello $name!", modifier = modifier
  )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
  FeedBackTheme {
    Greeting("Android")
  }
}
