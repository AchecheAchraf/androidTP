package com.example.myapplication
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items

import androidx.compose.foundation.text.BasicText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun ActorScreen(navController: NavController, viewModel: MainViewModel) {
    // Assuming movies is a List of Result objects
    val actors by viewModel.actors.collectAsState()
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }


    LaunchedEffect(true) {
        viewModel.searchActors()
    }

    Scaffold(
        topBar = {

            Column {
                Spacer(modifier = Modifier.height(50.dp).background(MaterialTheme.colorScheme.primary) // Set the height of the bottom bar
                    .fillMaxWidth())
                TopAppBar(
                    backgroundColor = MaterialTheme.colorScheme.primary,
                    title = {
                        Text(
                            text = "Actors List",
                            style = MaterialTheme.typography.headlineSmall
                        )
                    },
                    actions = {
                        OutlinedTextField(
                            value = searchQuery,
                            onValueChange = { query ->
                                searchQuery = query
                                viewModel.filterActors(query.text) // Implement filtering
                            },
                            placeholder = { Text("Search...") },
                            modifier = Modifier
                                .width(220.dp)
                                .height(49.dp)
                                .background(Color(0xFFB0BEC5)),

                            singleLine = true,
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = MaterialTheme.colorScheme.secondary, // Optional: Customize border color
                                unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant // Optional: Customize border color
                            ),
                            shape = MaterialTheme.shapes.medium

                        )
                    }
                )
            }
        },
        bottomBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            BottomNavigation(
                backgroundColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 0.dp) // Adjust the padding as needed
                    .height(80.dp) // Set the height of the bottom bar
                    .fillMaxWidth()
            ) {
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profil") },
                    label = { Text("Profil") },
                    selected = currentRoute == "profil",
                    onClick = { navController.navigate("profil") }
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Movie, contentDescription = "Movie") },
                    label = { Text("Movie") },
                    selected = currentRoute == "movie",
                    onClick = { navController.navigate("movie") }
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Face, contentDescription = "Actor") }, // Icon for Actor
                    label = { Text("Actor") },
                    selected = currentRoute == "actor",
                    onClick = { navController.navigate("actor") } // Ensure this route exists
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Tv, contentDescription = "Series") }, // Icon for Series
                    label = { Text("Serie") },
                    selected = currentRoute == "serie",
                    onClick = { navController.navigate("serie") } // Ensure this route exists
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier.fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {


// Use LazyVerticalGrid to display movies in 2 columns
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 150.dp), // Responsive grid
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                items(actors) { actor ->
                    ActorCard(actor = actor)

                }
            }
        }
    }
}

