package com.example.smartapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.smartapp.components.BottomNavItem

@Preview(showBackground = true)
@Composable
fun PreviewThingsScreen() {
    val navController = rememberNavController()  // Mock NavController for preview
    Things(navController = navController)
}

@Composable
fun Things(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier.fillMaxWidth()
                    .shadow(elevation = 5.dp)
                    .background(Color(0xFFFFEB3B))
                    .padding(5.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "My Smart Home",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontFamily = goodlandfontfamily,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                        .padding(start = 30.dp),
                    textAlign = TextAlign.Center
                )
                IconButton(onClick = { /*Handle Search*/ }) {
                    Icon(imageVector = Icons.Default.SavedSearch, contentDescription = "Search", tint = Color.White)
                }
                IconButton(onClick = { /*Handle Filter*/ }) {
                    Icon(imageVector = Icons.Default.FilterList, contentDescription = "Filter", tint = Color.White)
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth().weight(1f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.Dashboard,
                        contentDescription = "No Favourites",
                        modifier = Modifier.size(110.dp),
                        tint = Color.Gray,
                    )
                    Text("No Things!", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text("It looks like you didn't discover any devices.", fontSize = 16.sp, color = Color.Gray)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text("Try any option below", fontSize = 16.sp, textAlign = TextAlign.Center, color = Color.Gray, modifier = Modifier.padding(horizontal = 20.dp))
                    Row(modifier = Modifier.padding(horizontal = 28.dp, vertical = 10.dp)) {
                        HorizontalDivider(color = Color.LightGray, thickness = 1.2.dp)
                    }
                }
            }

            val options = listOf(
                Pair(Icons.Default.SavedSearch, "Run discovery"),
                Pair(Icons.Default.Add, "Add a cloud account"),
                Pair(Icons.Default.LightMode, "View our supported devices"),
                Pair(Icons.Default.Email, "Contact support")
            )

            options.forEach { (icon, label) ->
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(start = 30.dp, bottom = 30.dp)) {
                    Box(
                        modifier = Modifier.size(48.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF0F9CD3))
                            .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        IconButton(onClick = { /*Handle Click*/ }) {
                            Icon(imageVector = icon, contentDescription = label, tint = Color.White)
                        }
                    }
                    Text(label, color = Color(0xFF45718F), fontSize = 18.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 10.dp))
                }
            }



                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .shadow(elevation = 40.dp)
                            .background(Color.White)
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        BottomNavItem(icon = Icons.Default.Star, label = "Favorites") {
                            navController.navigate("favorites")
                        }
                        BottomNavItem(icon = Icons.Default.Dashboard, label = "Things") {
                            navController.navigate("things")
                        }
                        BottomNavItem(
                            icon = Icons.Default.SettingsBackupRestore,
                            label = "Routines"
                        ) {
                            navController.navigate("routines")
                        }
                        BottomNavItem(icon = Icons.Default.Lightbulb, label = "Ideas") {
                            navController.navigate("ideas")
                        }
                        BottomNavItem(icon = Icons.Default.Settings, label = "Settings") {
                            navController.navigate("settings")
                        }
                    }
        }
    }
}
