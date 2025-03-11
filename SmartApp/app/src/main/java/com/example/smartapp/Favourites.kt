package com.example.smartapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.SettingsBackupRestore
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

@Composable
fun Favourites(navController : NavController){
    var selectedTab by remember{ mutableStateOf("favourites") }
    Box(modifier = Modifier.fillMaxSize()){
        Column(modifier = Modifier.fillMaxSize()){
            Row(
                modifier = Modifier.fillMaxWidth()
                    .shadow(elevation = 5.dp)
                    .background(Color(0xFFFFEB3B))
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ){
                Text(
                    "My Smart Phone",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontFamily = goodlandfontfamily,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f), // Makes the Text take up available space
                    textAlign = TextAlign.Center // Center the text within its allocated space
                )
                IconButton(onClick={/*Handle Edit*/}
                ){
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit", tint = Color.White)
                }
            }
            Row(modifier = Modifier.fillMaxWidth()
                .weight(1f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically

            ){
                Column(horizontalAlignment = Alignment.CenterHorizontally){
                    Icon(
                        imageVector = Icons.Default.StarBorder,
                        contentDescription = "No Favourites",
                        modifier= Modifier.size(150.dp),
                        tint = Color.Gray,

                        )
                    Text(
                        text = "No Favorites!",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Add your favorite routines for easy access here.",
                        fontSize = 19.sp,
                        textAlign = TextAlign.Center,
                        color = Color.Gray,
                        modifier = Modifier.padding(horizontal = 32.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Tap the '+' button below to add your favorite routines.",
                        fontSize = 19.sp,
                        textAlign = TextAlign.Center,
                        color = Color.Gray,
                        modifier = Modifier.padding(horizontal = 32.dp)
                    )
                }

            }

            Row(
                modifier = Modifier.fillMaxWidth()
                    .shadow(elevation = 40.dp)
                    .background(Color.White)
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                BottomNavItem(icon = Icons.Default.Star,
                    label = "Favorites",
                    isSelected = selectedTab == "favourites",
                    onClick ={
                        selectedTab = "favourites"
                        navController.navigate("favorites")
                    }
                )


                BottomNavItem(icon = Icons.Default.Dashboard,
                    label = "Things",
                    isSelected = selectedTab == "things",
                    onClick ={
                        selectedTab = "things"
                        navController.navigate("things")

                    }
                )

                BottomNavItem(
                    icon = Icons.Default.SettingsBackupRestore,
                    label = "Routines",
                    isSelected = selectedTab == "routines",
                    onClick ={
                        selectedTab = "routines"
                        navController.navigate("routines")

                    }
                )

                BottomNavItem(icon = Icons.Default.Lightbulb,
                    label = "Ideas",
                    isSelected = selectedTab == "ideas",
                    onClick ={
                        selectedTab = "ideas"
                        navController.navigate("ideas")

                    }
                )
                BottomNavItem(icon = Icons.Default.Settings,
                    label = "Settings",
                    isSelected = selectedTab == "settings",
                    onClick ={
                        selectedTab ="settings"
                        navController.navigate("settings")

                    }
                )

            }
        }
        FloatingActionButton(
            onClick = { /* Handle click */ },
            containerColor = Color(0xFF2596BE),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 80.dp, end = 16.dp)
                .clip(RoundedCornerShape(32.dp))
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewSmartHomeScreen() {
    val navController = rememberNavController()  // Mock NavController for preview
    Favourites(navController = navController)
}