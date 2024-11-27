package com.example.myapplication

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items


import androidx.compose.foundation.text.BasicText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
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
fun FavoriteMoviesScreen(navController: NavController, viewModel: MainViewModel) {
    val favoriteMovies by viewModel.favoriteMovies.collectAsState() // Collect favorites
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }

    LaunchedEffect(true) {
        viewModel.loadFavoriteMovies() // Ensure favorites are loaded
    }

    Scaffold(
        topBar = {
            Column {
                Spacer(
                    modifier = Modifier
                        .height(35.dp)
                        .background(MaterialTheme.colorScheme.primary)
                        .fillMaxWidth()
                )
                TopAppBar(
                    backgroundColor = MaterialTheme.colorScheme.primary,
                    title = {
                        Text(
                            text = "Favoris",
                            style = MaterialTheme.typography.headlineSmall
                        )
                    },
                    actions = {
                        // Back button on the right side of the top bar
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.Black
                            )
                        }
                    }
                )
            }
        },
        bottomBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            BottomNavigation(
                backgroundColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(bottom = 0.dp)
                    .height(60.dp)
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
                    icon = { Icon(Icons.Default.Face, contentDescription = "Actor") },
                    label = { Text("Acteurs") },
                    selected = currentRoute == "actor",
                    onClick = { navController.navigate("actor") }
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Tv, contentDescription = "Series") },
                    label = { Text("Series") },
                    selected = currentRoute == "serie",
                    onClick = { navController.navigate("serie") }
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            // Displaying the favorite movies
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 180.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .height(350.dp)
            ) {
                items(favoriteMovies) { movie ->
                    MovieCard(
                        movie = movie, // Directly pass the Movie object
                        onFavoriteClick = { movie ->
                            // Handle favorite toggle
                            if (movie.isFavorite) {
                                viewModel.removeFavoriteMovie(movie.id.toString())
                            } else {
                                viewModel.addFavoriteMovie(movie)
                            }
                        },
                        viewModel = viewModel // You can pass this if needed for other operations
                    )
                }
            }
        }
    }
}

