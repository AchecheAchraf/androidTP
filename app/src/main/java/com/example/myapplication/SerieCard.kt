package com.example.myapplication

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
@Composable
fun SerieCard(serie: Serie, viewModel: MainViewModel) {
    var showPopup by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                showPopup = true
                viewModel.fetchSerieCredits(serie.id) // Fetch details when clicked
            }
    ) {
        val imageUrl = "https://image.tmdb.org/t/p/w500${serie.poster_path}"
        Image(
            painter = rememberAsyncImagePainter(imageUrl),
            contentDescription = serie.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(RoundedCornerShape(12.dp))
        )

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = serie.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }

    if (showPopup) {
        val actors by viewModel.actors.collectAsState() // Observing actors
        AlertDialog(
            onDismissRequest = { showPopup = false },
            title = null, // Using custom title and layout
            text = {
                SerieDetail(
                    serie = serie,
                    actors = actors,
                    onClose = { showPopup = false }
                )
            },
            confirmButton = {} // Removed redundant confirm button
        )
    }
}


