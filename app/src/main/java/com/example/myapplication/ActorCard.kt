package com.example.myapplication

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
fun ActorCard(actor: Actor) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(120.dp)
            .border(2.dp, Color(0xFF6470A8), RoundedCornerShape(12.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF6470A8))
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Actor's Profile Picture
            val imageUrl = "https://image.tmdb.org/t/p/w500${actor.profile_path}"
            AsyncImage(
                model = imageUrl,
                contentDescription = actor.name,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.White, CircleShape)
            )

            // Actor's Name
            Text(
                text = actor.name,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}

