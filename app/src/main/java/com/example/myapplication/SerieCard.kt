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
@Composable
fun SerieCard(serie: Serie) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(260.dp)
            .border(5.dp, Color(0xFF6470A8), RoundedCornerShape(12.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF6470A8))
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Series Poster
            val imageUrl = "https://image.tmdb.org/t/p/w500${serie.backdrop_path}"
            AsyncImage(
                model = imageUrl,
                contentDescription = serie.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .border(4.dp, Color.White, RoundedCornerShape(12.dp))
            )

            // Series Name
            Text(
                text = serie.name,
                style = MaterialTheme.typography.h6,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )

            // Series First Air Date
            Text(
                text = "First Air Date: ${serie.first_air_date}",
                style = MaterialTheme.typography.body2,
                modifier = Modifier
                    .padding(start = 8.dp, bottom = 4.dp)
            )

            // Series Rating
            Text(
                text = "Rating: ${serie.vote_average}",
                style = MaterialTheme.typography.body2,
                modifier = Modifier
                    .padding(start = 8.dp, bottom = 8.dp)
            )
        }
    }
}

