package com.example.mobile_app

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Add
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.graphics.painter.Painter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(navController: NavController) {
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
                            .fillMaxWidth()  // Take up the full width
                            .wrapContentWidth(Alignment.CenterHorizontally)
                    )
                },

                actions = {
                    IconButton(onClick = { /* Handle pen icon click */ }) {
                        Icon(
                            Icons.Default.Edit,
                            contentDescription = "Edit",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFF9E238), titleContentColor = Color.White),
                modifier = Modifier.shadow(7.dp)
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Handle click */ },
                containerColor = Color(0xFF119CD7),
                shape = RoundedCornerShape(50),
                modifier = Modifier.padding(16.dp),
            ) {
                Icon(
                    imageVector = Icons.Default.Add, // Default Add icon
                    contentDescription = "Add",
                    tint = Color.White // Set the icon color to white
                )
            }
        },
        bottomBar = { BottomNavigationBar(navController) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.star_1), // Same here to load the SVG
                    contentDescription = "No Favorites!",
                    modifier = Modifier.size(150.dp),
                    tint = Color.Gray
                )
                Text(text = "No Favorites!", fontSize = 26.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
                Text(
                    text = "Add your favorite routines for easy access here.",
                    fontSize = 19.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Gray,
                    modifier = Modifier.padding(horizontal = 32.dp)
                )
                Text(
                    text = "Tap the '+' button below to add your favourite routines",
                    fontSize = 19.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Gray,
                    modifier = Modifier.padding(vertical = 32.dp, horizontal = 32.dp)
                )
            }
        }
    }
}
