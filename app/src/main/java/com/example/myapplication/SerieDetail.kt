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
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
fun SerieDetail(serie: Serie, actors: List<Actor>, onClose: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Close button at the top-right
        IconButton(
            onClick = { onClose() }, // Close the dialog
            modifier = Modifier
                .align(Alignment.TopEnd) // Align the button to the top-right
                .zIndex(1f)
                .offset(y = (-10).dp) // Ensure the button is on top of other elements
        ) {
            Icon(
                imageVector = Icons.Default.Close, // Using close icon
                contentDescription = "Close",
                tint = Color.Black
            )
        }
        // Title at the top left
        Text(
            text = serie.name,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.align(Alignment.TopStart)
        )

        // Main Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(top = 40.dp) // Leave space for title and close button
        ) {
            // Series Image
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w500${serie.backdrop_path}",
                contentDescription = serie.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
            Spacer(modifier = Modifier.height(8.dp))

            // First Air Date and Rating
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Date de première diffusion : ", // French for "First Air Date"
                    fontSize = 14.sp,
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Bold // Bold only the label
                )
                Text(
                    text = serie.first_air_date, // Keep the air date in normal font
                    fontSize = 12.sp,
                    color = Color.DarkGray
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Note : ", // French for "Rating"
                    fontSize = 14.sp,
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Bold // Bold only the label
                )
                Text(
                    text = "${serie.vote_average}/10", // Keep the rating in normal font
                    fontSize = 12.sp,
                    color = Color.DarkGray
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Series Description
            Text(
                text = "Description: ",
                fontSize = 18.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = serie.overview ?: "Aucune description disponible", // French for "No description available"
                fontSize = 14.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Actor List
            Text(
                text = "Acteurs:",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))

            actors.take(5).forEach { actor ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/w500${actor.profile_path}",
                        contentDescription = actor.name,
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = actor.name,
                            fontSize = 14.sp,
                            color = Color.Black
                        )
                        Text(
                            text = "as ${actor.character}",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }
                }
            }
        }
    }
}

