package com.example.sqldemo.user

import com.example.sqldemo.Screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
  navController: NavHostController,
  userViewModel: UserViewModel = viewModel()
) {
  val users by userViewModel.allUsers.collectAsState(initial = emptyList())
  val scope = rememberCoroutineScope()
  var showAddUserDialog by remember { mutableStateOf(false) }

  Scaffold(
    topBar = {
      TopAppBar(
        title = { Text("User List") },
        actions = {
          IconButton(onClick = { navController.navigate(com.example.sqldemo.Screen.Settings.route) }) {
            Icon(Icons.Default.Settings, contentDescription = "Settings")
          }
        }
      )
    },
    floatingActionButton = {
      FloatingActionButton(onClick = { showAddUserDialog = true }) {
        Icon(Icons.Default.Add, contentDescription = "Add User")
      }
    }
  ) { padding ->
    Box(modifier = Modifier.padding(padding)) {
      if (users.isEmpty()) {
        Box(
          modifier = Modifier.fillMaxSize(),
          contentAlignment = Alignment.Center
        ) {
          Text("No users yet. Add some using the + button!")
        }
      } else {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
          items(users) { user ->
            UserItem(
              user = user ,
              onItemClick = { navController.navigate("${com.example.sqldemo.Screen.Detail.route}/${user.id}") } ,
              onFavoriteClick = { userViewModel.toggleFavorite(user.id, !user.isFavorite) }
            )
          }
        }
      }
    }
  }

  if (showAddUserDialog) {
    AddUserDialog(
      onDismiss = { showAddUserDialog = false },
      onAddUser = { name, age, email ->
        scope.launch {
          userViewModel.insert(UserEntity(name = name, age = age, email = email))
          showAddUserDialog = false
        }
      }
    )
  }
}

@Composable
private fun UserItem(
  user: UserEntity,
  onItemClick: () -> Unit,
  onFavoriteClick: () -> Unit
) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .padding(8.dp)
      .clickable(onClick = onItemClick),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Column(modifier = Modifier.weight(1f)) {
        Text(
          text = user.name,
          style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
          text = "Age: ${user.age}",
          style = MaterialTheme.typography.bodyMedium
        )
        Text(
          text = user.email,
          style = MaterialTheme.typography.bodySmall,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis
        )
      }

      IconButton(onClick = onFavoriteClick) {
        Icon(
          if (user.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
          contentDescription = if (user.isFavorite) "Remove from favorites" else "Add to favorites",
          tint = if (user.isFavorite) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
        )
      }
    }
  }
}

@Composable
fun AddUserDialog(
  onDismiss: () -> Unit,
  onAddUser: (name: String, age: Int, email: String) -> Unit
) {
  var name by remember { mutableStateOf("") }
  var age by remember { mutableStateOf("") }
  var email by remember { mutableStateOf("") }
  var isError by remember { mutableStateOf(false) }

  AlertDialog(
    onDismissRequest = onDismiss,
    title = { Text("Add New User") },
    text = {
      Column {
        TextField(
          value = name,
          onValueChange = { name = it },
          label = { Text("Name") },
          modifier = Modifier.fillMaxWidth(),
          isError = isError && name.isBlank()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
          value = age,
          onValueChange = { age = it },
          label = { Text("Age") },
          modifier = Modifier.fillMaxWidth(),
          isError = isError && (age.isBlank() || age.toIntOrNull() == null)
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
          value = email,
          onValueChange = { email = it },
          label = { Text("Email") },
          modifier = Modifier.fillMaxWidth(),
          isError = isError && email.isBlank()
        )
        if (isError) {
          Text(
            "Please fill all fields correctly",
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(top = 8.dp)
          )
        }
      }
    },
    confirmButton = {
      Button(
        onClick = {
          if (name.isBlank() || age.isBlank() || age.toIntOrNull() == null || email.isBlank()) {
            isError = true
          } else {
            onAddUser(name, age.toInt(), email)
          }
        }
      ) {
        Text("Add")
      }
    },
    dismissButton = {
      Button(onClick = onDismiss) {
        Text("Cancel")
      }
    }
  )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
  navController: NavHostController,
  userViewModel: UserViewModel = viewModel(),
  userId: String? = null
) {
  val users by userViewModel.allUsers.collectAsState(initial = emptyList())
  val user = users.find { it.id == userId }

  Scaffold(
    topBar = {
      TopAppBar(
        title = { Text(user?.name ?: "User Details") },
        navigationIcon = {
          IconButton(onClick = { navController.navigateUp() }) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
          }
        }
      )
    }
  ) { padding ->
    Box(modifier = Modifier.padding(padding)) {
      if (user != null) {
        Column(
          modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
        ) {
          Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
          ) {
            Column(modifier = Modifier.padding(16.dp)) {
              Text(
                text = user.name,
                style = MaterialTheme.typography.headlineSmall
              )
              Spacer(modifier = Modifier.height(8.dp))
              Text(
                text = "Age: ${user.age}",
                style = MaterialTheme.typography.bodyLarge
              )
              Spacer(modifier = Modifier.height(8.dp))
              Text(
                text = "Email: ${user.email}",
                style = MaterialTheme.typography.bodyLarge
              )
              Spacer(modifier = Modifier.height(16.dp))
              Row(
                verticalAlignment = Alignment.CenterVertically
              ) {
                Text(
                  text = "Favorite: ",
                  style = MaterialTheme.typography.bodyLarge
                )
                IconButton(
                  onClick = { userViewModel.updateFavorite(id = user.id ,
                                                           isFavorite = !user.isFavorite) }
                ) {
                  Icon(
                    if (user.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Toggle Favorite",
                    tint = if (user.isFavorite) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                  )
                }
              }
            }
          }
        }
      } else {
        Box(
          modifier = Modifier.fillMaxSize(),
          contentAlignment = Alignment.Center
        ) {
          Text("User not found")
        }
      }
    }
  }
}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun SettingsScreen(
//  navController: NavHostController,
//  settingsViewModel: SettingsViewModel = viewModel()
//) {
//  var isDarkMode by remember { mutableStateOf(settingsViewModel.isDarkModeEnabled()) }
//
//  Scaffold(
//    topBar = {
//      TopAppBar(
//        title = { Text("Settings") },
//        navigationIcon = {
//          IconButton(onClick = { navController.navigateUp() }) {
//            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
//          }
//        }
//      )
//    }
//  ) { padding ->
//    Column(
//      modifier = Modifier
//        .fillMaxSize()
//        .padding(padding)
//        .padding(16.dp)
//    ) {
//      Card(modifier = Modifier.fillMaxWidth()) {
//        Row(
//          modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp),
//          verticalAlignment = Alignment.CenterVertically,
//          horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//          Text(
//            text = "Dark Mode",
//            style = MaterialTheme.typography.titleMedium
//          )
//          Switch(
//            checked = isDarkMode,
//            onCheckedChange = { isChecked ->
//              isDarkMode = isChecked
//              settingsViewModel.setDarkMode(isChecked)
//            }
//          )
//        }
//      }
//
//      Spacer(modifier = Modifier.height(16.dp))
//
//      Button(
//        onClick = { navController.navigate(Screen.NewActivity.route) } ,
//        modifier = Modifier.fillMaxWidth()
//      ) {
//        Text("Open New Activity")
//      }
//    }
//  }
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewActivityScreen(navController: NavHostController? = null) {
  Scaffold(
    topBar = {
      navController?.let {
        TopAppBar(
          title = { Text("New Activity") },
          navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
              Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
          }
        )
      } ?: TopAppBar(title = { Text("New Activity") })
    }
  ) { padding ->
    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(padding),
      contentAlignment = Alignment.Center
    ) {
      Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
      ) {
        Icon(
          Icons.Default.Star,
          contentDescription = null,
          modifier = Modifier.size(80.dp),
          tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
          "This is a new activity!",
          style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
          "It can be launched independently from the main app flow",
          style = MaterialTheme.typography.bodyLarge,
          modifier = Modifier.padding(horizontal = 32.dp)
        )
      }
    }
  }
}
