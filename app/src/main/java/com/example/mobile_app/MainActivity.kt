package com.example.mobile_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            MainNavigation(navController)
        }
    }
}

@Composable
fun MainNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "favorites") {
        composable("favorites") { FavoritesScreen(navController) }
        composable("things") { ThingsScreen(navController) }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val currentRoute = navController.currentBackStackEntry?.destination?.route

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(10.dp)
            .background(Color(0xFFF5F4F4)) // Custom light gray color (RGB hex code)
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        BottomNavItem(
            navController,
            "favorites",
            if (currentRoute == "favorites") painterResource(id = R.drawable.kid_star3) else painterResource(id = R.drawable.kid_star),
            "Favorites"
        )
        BottomNavItem(
            navController,
            "things",
            painterResource(id = R.drawable.img),
            "Things"
        )
        BottomNavItem(
            navController,
            "routines",
            painterResource(id = R.drawable.routine),
            "Routines"
        )
        BottomNavItem(
            navController,
            "ideas",
            painterResource(id = R.drawable.bulb),
            "Ideas"
        )
        BottomNavItem(
            navController,
            "settings",
            painterResource(id = R.drawable.sliders),
            "Settings"
        )
    }
}

@Composable
fun BottomNavItem(navController: NavController, route: String, icon: Any, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { navController.navigate(route) }
    ) {
        when (icon) {
            is ImageVector -> Icon(imageVector = icon, contentDescription = label, modifier = Modifier.size(24.dp))
            is Painter -> Image(painter = icon, contentDescription = label, modifier = Modifier.size(24.dp))
        }
        Text(text = label, fontSize = 12.sp, color = Color.Gray)
    }
}

val robotoFontFamily = FontFamily(
    Font(R.font.teko)  // Reference the Roboto font in res/font/ directory
)
