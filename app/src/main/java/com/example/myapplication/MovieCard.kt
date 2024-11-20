package com.example.myapplication

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.example.myapplication.Movie
@Composable
fun MovieCard(movie: Movie) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(260.dp)
            .border(5.dp, Color(0xFFD3D3D3), RoundedCornerShape(12.dp)), // Border for the card
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(22.dp), // Rounded corners
        colors = CardDefaults.cardColors(containerColor = Color(0xFFD3D3D3))
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Movie Poster
            val imageUrl = "https://image.tmdb.org/t/p/w500${movie.backdrop_path}"
            Log.d("MovieCard", "Image URL: $imageUrl")
            AsyncImage(
                model = imageUrl,
                contentDescription = movie.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .border(4.dp, Color.White, RoundedCornerShape(12.dp)) // Border for the image
            )

            // Movie Title
            Text(
                text = movie.title,
                style = MaterialTheme.typography.h6,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )

            // Movie Release Date
            Text(
                text = "Release Date: ${movie.release_date}",
                style = MaterialTheme.typography.body2,
                modifier = Modifier
                    .padding(start = 8.dp, bottom = 4.dp)
            )

            // Movie Rating
            Text(
                text = "Rating: ${movie.vote_average}",
                style = MaterialTheme.typography.body2,
                modifier = Modifier
                    .padding(start = 8.dp, bottom = 8.dp)
            )
        }
    }
}
