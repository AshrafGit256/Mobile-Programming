package com.example.mobile_app

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.SavedSearch
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.res.fontResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThingsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "My Smart Home",
                        fontSize = 45.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = robotoFontFamily, // Replace with actual Arial Narrow if available
           
                        modifier = Modifier
                            .fillMaxWidth() // Take up the full width
                            .wrapContentWidth(Alignment.CenterHorizontally) // Center the text horizontally
                    )
                },
                actions = {
                    Image(
                        painter = painterResource(id = R.drawable.zoom_1), // Reference to zoom.png
                        contentDescription = "Zoom Icon",
                        modifier = Modifier.size(28.dp),
                    )

                    Spacer(modifier = Modifier.width(16.dp)) // Space between the icons

                    Image(
                        painter = painterResource(id = R.drawable.sort_1), // Reference to sort.png
                        contentDescription = "Sort Icon",
                        modifier = Modifier.size(28.dp),
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFF9E335), titleContentColor = Color.White),
                modifier = Modifier.shadow(7.dp)
            )
        },
        bottomBar = { BottomNavigationBar(navController) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp), // Added horizontal padding for the left alignment
                horizontalAlignment = Alignment.CenterHorizontally, // Left align
                verticalArrangement = Arrangement.spacedBy(8.dp) // Reduced vertical spacing between rows
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_1),
                    contentDescription = "No Things",
                    modifier = Modifier.size(150.dp)
                )
                Text(text = "No things!", fontSize = 26.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
                Text(
                    text = "It looks like we didn’t discover any devices.",
                    fontSize = 17.sp,
                    textAlign = TextAlign.Start,
                    color = Color.Gray,

                )

                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Try any option below",
                    fontSize = 19.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Gray,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Row(modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)){
                    HorizontalDivider(
                        color = Color.LightGray,
                        thickness = 1.2.dp,
                    )
                }

                // Add Rows for each button and description here (Left aligned)
                ActionButtonRow(icon = Icons.Default.SavedSearch, text = "Run discovery")
                ActionButtonRow(icon = Icons.Default.Add, text = "Add a cloud account")
                ActionButtonRowWithImage(imageResId = R.drawable.drag, text = "View our supported devices")
                ActionButtonRow(icon = Icons.Default.Email, text = "Contact support")
            }
        }
    }
}

@Composable
fun ActionButtonRow(icon: ImageVector, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(start = 0.dp, bottom = 8.dp) // Adjusted the bottom padding to reduce vertical spacing
            .fillMaxWidth() // Ensures the Row takes full width for left alignment
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color(0xFF0F9CD3)) // Light blue background
                .padding(5.dp),
            contentAlignment = Alignment.Center
        ) {
            IconButton(onClick = { /* Handle Click */ }) {
                Icon(imageVector = icon, contentDescription = "Action", tint = Color.White)
            }
        }
        Text(
            text,
            color = Color(0xFF45718F),
            fontSize = 19.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 5.dp)
        )
    }
}

@Composable
fun ActionButtonRowWithImage(imageResId: Int, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color(0xFF0F9CD3)) // Light blue background
                .padding(5.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = text,
                modifier = Modifier.size(24.dp)
            )
        }
        Text(
            text,
            color = Color(0xFF45718F),
            fontSize = 19.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}