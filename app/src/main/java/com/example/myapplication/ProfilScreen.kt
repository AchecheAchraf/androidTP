package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass

@Composable
fun MonImage() {
    Box(
        modifier = Modifier
            .fillMaxWidth() // Take full width of the parent for responsive behavior
            .aspectRatio(1f) // Maintain a square shape for the box
            .padding(80.dp) // Use a general padding instead of fixed start and top padding
            .border(2.dp, Color.Black, CircleShape)
            .clip(CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.achraf),
            contentDescription = "moi",
            modifier = Modifier
                .fillMaxSize() // Make the image fill the box's size responsively
                .clip(CircleShape)
        )
    }
}

@Composable
fun MonImageHorizontal() {
    Box(
        modifier = Modifier
            .size(150.dp) // Smaller image size for landscape
            .border(2.dp, Color.Black, CircleShape)
            .clip(CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.moi),
            contentDescription = "moi",
            modifier = Modifier
                .fillMaxSize() // Fill the box size
                .clip(CircleShape)
        )
    }
}


@Composable
fun ConnectButton(navController: NavController) {
    Button(
        onClick = {
            navController.navigate("Movie") // Navigate to the "Movie" screen when the button is clicked
        },
        modifier = Modifier
            .fillMaxWidth(0.8f) // Responsive width (80% of the parent width)
            .padding(16.dp) // General padding for spacing
            .height(50.dp) // Set a consistent button height
    ) {
        Text(text = "Démarrer", fontSize = 18.sp)
    }
}

@Composable
fun ContactInfo() {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(8.dp) // Space between each row
    ) {
        // Email Row
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp) // Space between icon and text
        ) {
            Icon(
                imageVector = Icons.Default.Email, // Default Email icon
                contentDescription = "Email Icon",
                modifier = Modifier.size(35.dp),
                tint = Color.Unspecified // Ensure the original color of the email icon is used
            )
            Text(text = "achecheachraf@gmail.com", fontSize = 14.sp)
        }

        // LinkedIn Row
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp) // Space between icon and text
        ) {
            Image(
                painter = painterResource(id = R.drawable.linkedin), // Custom LinkedIn icon
                contentDescription = "LinkedIn Icon",
                modifier = Modifier.size(35.dp)
            )
            Text(text = "linkedin.com/in/achecheachraf", fontSize = 14.sp)
        }
    }
}



@Composable
fun ProfilScreen(windowClass: WindowSizeClass, navController: NavController) {
    when (windowClass.windowWidthSizeClass) {
        WindowWidthSizeClass.COMPACT -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopCenter // Align the Column to the top-center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    MonImage() // The padding within MonImage will be preserved

                    // Name
                    Text(
                        buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold, // Bold style for "ACHECHE"
                                    fontSize = 26.sp,
                                )
                            ) {
                                append("ACHECHE ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    fontSize = 26.sp,
                                )
                            ) {
                                append("Achraf")
                            }
                        }
                    )

                    // Titles
                    Spacer(modifier = Modifier.height(40.dp)) // Space between name and titles
                    Text(
                        text = "Étudiant en informatique pour la santé",
                        fontSize = 21.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Gray
                    )
                    Text(
                        text = "Développeur Web",
                        fontSize = 21.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(100.dp)) // Space before the button
                    ConnectButton(navController)
                    ContactInfo()
                }
            }
        }
        else -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center // Align content centrally
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp), // Space between columns
                    verticalAlignment = Alignment.CenterVertically, // Center content vertically
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    // Left side: Image and Name
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp), // Space between elements
                        modifier = Modifier.weight(1f) // Take up 50% of the width
                    ) {
                        MonImageHorizontal() // Image
                        // Name
                        Text(
                            buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        fontWeight = FontWeight.Bold, // Bold style for "ACHECHE"
                                        fontSize = 26.sp,
                                    )
                                ) {
                                    append("ACHECHE ")
                                }
                                withStyle(
                                    style = SpanStyle(
                                        fontSize = 26.sp,
                                    )
                                ) {
                                    append("Achraf")
                                }
                            }
                        )


                        // Titles
                        Text(
                            text = "Étudiant en informatique pour la santé",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Gray
                        )
                        Text(
                            text = "Développeur Web",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Gray
                        )
                    }

                    // Right side: Button and Contact Info
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp), // Space between button and contact info
                        modifier = Modifier.weight(1f) // Take up 50% of the width
                    ) {
                        ConnectButton(navController)
                        ContactInfo()
                    }
                }
            }
        }
    }
}
