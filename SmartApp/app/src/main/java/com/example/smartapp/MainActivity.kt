package com.example.smartapp


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.smartapp.ui.theme.SmartAppTheme
import com.example.smartapp.components.BottomNavItem


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            Scaffold(
                //bottomBar = {BottomNavigationBar(navController)}
            ) { paddingValues ->
                Box(modifier = Modifier.padding(paddingValues)){
                    NavHost(
                        navController = navController,
                        startDestination = "favourites"
                    ){
                        composable("favourites"){Favourites(navController)}
                        composable("things"){Things(navController)}
                    }
                }
            }
        }
    }

}

val goodlandfontfamily= FontFamily(Font(R.font.goodland))




/*@Composable
fun BottomNavigationBar(navController: NavController) {
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route

    NavigationBar {
        BottomNavItem(icon = Icons.Default.Star, label = "Favourites") {
            if (currentDestination != "favourites") {
                navController.navigate("favourites")
            }
        }
        BottomNavItem(icon = Icons.Default.Dashboard, label = "Things") {
            if (currentDestination != "things") {
                navController.navigate("things")
            }
        }

    }
}*/

