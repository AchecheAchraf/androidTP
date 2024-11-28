package com.example.myapplication
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.lifecycle.viewmodel.compose.viewModel
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
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun PlaylistScreen(navController: NavController, viewModel: MainViewModel) {
    // Assuming movies is a List of Result objects
    val movies by viewModel.movies.collectAsState()
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }


    LaunchedEffect(true) {
        viewModel.searchMovies()
    }

    Scaffold(
        topBar = {
            Column {
                Spacer(modifier = Modifier.height(35.dp).background(MaterialTheme.colorScheme.primary) // Set the height of the bottom bar
                    .fillMaxWidth())
                TopAppBar(
                    backgroundColor = MaterialTheme.colorScheme.primary,
                    title = {
                        Text(
                            text = "Films",
                            style = MaterialTheme.typography.headlineSmall
                        )
                    },
                    actions = {

                        OutlinedTextField(
                            value = searchQuery,

                            onValueChange = { query ->
                                searchQuery = query
                                viewModel.filterMovies(query.text) // Implement filtering
                            },
                            placeholder = { Text("Nom du film") },
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .width(220.dp)
                                .height(49.dp)// Adjust the height for better design
                                .background(Color.White, shape = MaterialTheme.shapes.medium), // Add a background with rounded shape
                            singleLine = true,
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color.Blue, // Match the desired color
                                unfocusedBorderColor = Color.Gray,
                                textColor = Color.Black,
                                backgroundColor = Color.Transparent // Avoid overriding the custom background
                            ),
                            shape = MaterialTheme.shapes.medium // Rounded corners for a sleek design
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
                    .height(60.dp) // Set the height of the bottom bar
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
                    label = { Text("Films") },
                    selected = currentRoute == "movie",
                    onClick = { navController.navigate("movie") }
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Face, contentDescription = "Actor") }, // Icon for Actor
                    label = { Text("Acteurs") },
                    selected = currentRoute == "actor",
                    onClick = { navController.navigate("actor") } // Ensure this route exists
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Tv, contentDescription = "Series") }, // Icon for Series
                    label = { Text("Series") },
                    selected = currentRoute == "serie",
                    onClick = { navController.navigate("serie") } // Ensure this route exists
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Tv, contentDescription = "Series") }, // Icon for Series
                    label = { Text("Playlist") },
                    selected = currentRoute == "playlist",
                    onClick = { navController.navigate("playlist") } // Ensure this route exists
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier.fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {Log.d("MovieCard", "Image URL: $movies")


// Use LazyVerticalGrid to display movies in 2 columns
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 180.dp), // Responsive grid
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .height(350.dp)
            ) {
                items(movies) { movie ->
                    Log.d("MovieScreen", "Movie: ${movie.title}")
                    MovieCard(movie = movie,  viewModel = viewModel)

                }
            }
        }
    }
}

