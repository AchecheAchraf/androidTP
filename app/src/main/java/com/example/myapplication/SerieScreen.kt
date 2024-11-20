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
fun SerieScreen(navController: NavController, viewModel: MainViewModel) {
    val series by viewModel.series.collectAsState()
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }

    // Fetch series when the screen is loaded
    LaunchedEffect(true) {
        viewModel.searchSeries()
    }

    Scaffold(
        topBar = {
            Column {
                Spacer(
                    modifier = Modifier
                        .height(50.dp)
                        .background(MaterialTheme.colorScheme.primary)
                        .fillMaxWidth()
                )
                TopAppBar(
                    backgroundColor = MaterialTheme.colorScheme.primary,
                    title = {
                        Text(
                            text = "Series List",
                            style = MaterialTheme.typography.headlineSmall
                        )
                    },
                    actions = {
                        OutlinedTextField(
                            value = searchQuery,
                            onValueChange = { query ->
                                searchQuery = query
                                viewModel.filterSeries(query.text)
                            },
                            placeholder = { Text("Search...") },
                            modifier = Modifier
                                .width(220.dp)
                                .height(49.dp)
                                .background(Color(0xFFB0BEC5)),
                            singleLine = true,
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = MaterialTheme.colorScheme.secondary,
                                unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant
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
                modifier = Modifier
                    .height(80.dp)
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
                    icon = { Icon(Icons.Default.Face, contentDescription = "Actor") },
                    label = { Text("Actor") },
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
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 150.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                items(series) { serie ->
                    SerieCard(serie = serie)
                }
            }
        }
    }
}



