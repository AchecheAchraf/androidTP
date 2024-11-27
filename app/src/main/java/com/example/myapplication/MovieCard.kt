package com.example.myapplication

import android.util.Log
import androidx.compose.foundation.Image
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items


import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.example.myapplication.Movie
@Composable
fun MovieCard(
    movie: MovieEntity,
    onFavoriteClick: (MovieEntity) -> Unit, // Pass the lambda to handle favorite click
    viewModel: MainViewModel
) {
    var showPopup by remember { mutableStateOf(false) }
    val isFavorite = movie.isFavorite // Directly bind to movie.isFavorite

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                showPopup = true
                viewModel.fetchMovieCredits(movie.id.toInt()) // Fetch credits when the card is clicked
            }
    ) {
        // Movie Image with a Heart Icon at the top left
        val imageUrl = "https://image.tmdb.org/t/p/w500${movie.fiche.poster_path}" // Access poster_path through fiche
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(RoundedCornerShape(12.dp))
        ) {
            // Movie Poster Image
            Image(
                painter = rememberAsyncImagePainter(imageUrl),
                contentDescription = movie.fiche.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .clip(RoundedCornerShape(12.dp))
            )

            // Heart Icon at the top left of the image
            IconButton(
                onClick = { onFavoriteClick(movie) } // Invoke the passed lambda
            ) {
                Icon(
                    imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = Color.Red // Set the color of the heart icon
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Movie Title
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = movie.fiche.title, // Access title through fiche
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }

    if (showPopup) {
        val actors by viewModel.actors.collectAsState()
        AlertDialog(
            onDismissRequest = { showPopup = false },
            title = null,
            text = {
                MovieDetail(
                    movie = movie.fiche, // Pass fiche instead of movie
                    actors = actors,
                    onClose = { showPopup = false }
                )
            },
            confirmButton = {}
        )
    }
}




