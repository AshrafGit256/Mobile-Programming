package com.example.smartapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color.Companion as Color1

@Composable
fun BottomNavItem(icon: ImageVector, label: String, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(4.dp)
            .width(64.dp) // Ensure enough width for text
            .clickable { onClick() } // Make the whole column clickable
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            modifier = Modifier.size(24.dp), // Reduce icon size if needed
            tint = Color1.Gray
        )
        Spacer(modifier = Modifier.height(4.dp)) // Add spacing between icon and text
        Text(
            text = label,
            fontSize = 12.sp, // Reduce text size if needed
            color = Color1.Gray
        )
    }
}
