package com.example.smartapp

import android.app.TimePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.smartapp.components.BottomNavItem
import com.example.smartapp.db.RoutineDatabaseHelper
import java.util.*

@Preview(showBackground = true)
@Composable
fun PreviewRoutinesScreen() {
    val navController = rememberNavController()
    Routines(navController = navController)
}

@Composable
fun Routines(navController: NavController) {
    var selectedTab by remember { mutableStateOf("routines") }
    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val dbHelper = remember { RoutineDatabaseHelper(context) }
    var routines by remember { mutableStateOf(dbHelper.getAllRoutines()) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Top Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFFFEB3B))
                    .padding(vertical = 10.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "My Smart Home",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            // Content
            if (routines.isEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.SettingsBackupRestore,
                        contentDescription = "No Routines",
                        modifier = Modifier.size(100.dp),
                        tint = Color.Gray
                    )
                    Text(
                        "No Routines!",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                    Text(
                        "Click the '+' button below to get started",
                        fontSize = 16.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(top = 8.dp),
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(16.dp)) {
                    routines.forEach {
                        Text("Task Name: ${it.name}", fontWeight = FontWeight.Bold)
                        Text("Timing: ${it.time}")
                        Text("Recurrence: ${it.recurrence}")
                        Divider(modifier = Modifier.padding(vertical = 8.dp))
                    }
                }
            }

            // Bottom Navigation
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                BottomNavItem(
                    icon = Icons.Default.Star,
                    label = "Favorites",
                    isSelected = selectedTab == "favourites",
                    onClick = {
                        selectedTab = "favourites"
                        navController.navigate("favorites")
                    }
                )
                BottomNavItem(
                    icon = Icons.Default.Dashboard,
                    label = "Things",
                    isSelected = selectedTab == "things",
                    onClick = {
                        selectedTab = "things"
                        navController.navigate("things")
                    }
                )
                BottomNavItem(
                    icon = Icons.Default.SettingsBackupRestore,
                    label = "Routines",
                    isSelected = selectedTab == "routines",
                    onClick = {
                        selectedTab = "routines"
                        navController.navigate("routines")
                    }
                )
                BottomNavItem(
                    icon = Icons.Default.Lightbulb,
                    label = "Ideas",
                    isSelected = selectedTab == "ideas",
                    onClick = {
                        selectedTab = "ideas"
                        navController.navigate("ideas")
                    }
                )
                BottomNavItem(
                    icon = Icons.Default.Settings,
                    label = "Settings",
                    isSelected = selectedTab == "settings",
                    onClick = {
                        selectedTab = "settings"
                        navController.navigate("settings")
                    }
                )
            }
        }

        // Floating Action Button
        FloatingActionButton(
            onClick = { showDialog = true },
            containerColor = Color(0xFF2596BE),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 80.dp, end = 16.dp)
                .clip(RoundedCornerShape(32.dp))
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
        }

        // Show Add Dialog
        if (showDialog) {
            AddRoutineDialog(
                onDismiss = { showDialog = false },
                onSave = { name, time, recurrence ->
                    dbHelper.insertRoutine(name, time, recurrence)
                    routines = dbHelper.getAllRoutines()
                    showDialog = false
                }
            )
        }
    }
}

@Composable
fun AddRoutineDialog(
    onDismiss: () -> Unit,
    onSave: (String, String, String) -> Unit
) {
    var taskName by remember { mutableStateOf("") }
    var selectedTime by remember { mutableStateOf("") }
    var selectedRecurrence by remember { mutableStateOf("Everyday") }
    val recurrenceOptions = listOf("Everyday", "Week Days", "Weekend", "Week", "Month", "Year", "Day")

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add Routine") },
        text = {
            Column {
                OutlinedTextField(
                    value = taskName,
                    onValueChange = { taskName = it },
                    label = { Text("Task Name") }
                )
                Spacer(Modifier.height(8.dp))
                TimePickerView { selectedTime = it }
                Spacer(Modifier.height(8.dp))
                DropdownMenuView(recurrenceOptions, selectedRecurrence) {
                    selectedRecurrence = it
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (taskName.isNotBlank() && selectedTime.isNotBlank()) {
                        onSave(taskName, selectedTime, selectedRecurrence)
                    }
                }
            ) {
                Text("Save")
            }
        },
        dismissButton = {
            OutlinedButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Composable
fun TimePickerView(onTimeSelected: (String) -> Unit) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    val timePickerDialog = remember {
        TimePickerDialog(
            context,
            { _, hour: Int, minute: Int ->
                val amPm = if (hour < 12) "AM" else "PM"
                val hourFormatted = if (hour % 12 == 0) 12 else hour % 12
                val timeString = String.format("%02d:%02d %s", hourFormatted, minute, amPm)
                onTimeSelected(timeString)
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            false
        )
    }

    Button(onClick = { timePickerDialog.show() }) {
        Text("Pick Time")
    }
}

@Composable
fun DropdownMenuView(
    options: List<String>,
    selected: String,
    onSelect: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = selected,
            onValueChange = {},
            label = { Text("Recurrence") },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(Color.Transparent)
                .clickable { expanded = true }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach {
                DropdownMenuItem(
                    text = { Text(it) },
                    onClick = {
                        onSelect(it)
                        expanded = false
                    }
                )
            }
        }
    }
}
