package com.example.smartapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.smartapp.viewmodel.SettingsViewModel

@Composable
fun UserSettings(navController: NavController, viewModel: SettingsViewModel = viewModel()) {
    val userName by viewModel.userName.collectAsState()
    val userEmail by viewModel.userEmail.collectAsState()
    val appColor by viewModel.appColor.collectAsState()
    val alarmEnabled by viewModel.alarmEnabled.collectAsState()
    val notificationsEnabled by viewModel.notificationsEnabled.collectAsState()

    UserSettingsContent(
        navController = navController,
        userName = userName,
        userEmail = userEmail,
        appColor = appColor,
        alarmEnabled = alarmEnabled,
        notificationsEnabled = notificationsEnabled,
        onUserInfoChanged = { name, email -> viewModel.updateUserInfo(name, email) },
        onAppColorChanged = viewModel::setAppColor,
        onAlarmToggle = viewModel::toggleAlarm,
        onNotificationsToggle = viewModel::toggleNotifications
    )
}

@Composable
fun UserSettingsContent(
    navController: NavController,
    userName: String,
    userEmail: String,
    appColor: Color,
    alarmEnabled: Boolean,
    notificationsEnabled: Boolean,
    onUserInfoChanged: (String, String) -> Unit,
    onAppColorChanged: (Color) -> Unit,
    onAlarmToggle: (Boolean) -> Unit,
    onNotificationsToggle: (Boolean) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        EditUserDialog(
            currentName = userName,
            currentEmail = userEmail,
            onDismiss = { showDialog = false },
            onConfirm = { name, email ->
                onUserInfoChanged(name, email)
                showDialog = false
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Top header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(elevation = 5.dp)
                .height(60.dp)
                .background(Color(0xFFFFEB3B))
                .padding(16.dp)
        ) {
            Text(
                text = "My Smart Home",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        // Section Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .background(Color(0xFFE0E0E0))
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "User Settings",
                color = Color.DarkGray,
                fontSize = 16.sp
            )
        }

        // User Info Row (clickable)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(50.dp)
                .clickable { showDialog = true },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "User",
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(appColor)
                    .padding(10.dp),
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(text = userName, fontSize = 17.sp, fontWeight = FontWeight.Bold)
                Text(text = userEmail, fontSize = 15.sp, color = Color.Gray)
            }
        }

        // App Settings Section
        SectionHeader("App Settings")

        SettingRow("App Color") {
            Icon(
                imageVector = Icons.Default.CheckBox,
                contentDescription = "Color Option",
                tint = appColor,
                modifier = Modifier.clickable {
                    val lightPurple = Color(0xFFD1C4E9)
                    val defaultColor = Color(0xFFFFC107) // your default yellow
                    val newColor = if (appColor == lightPurple) defaultColor else lightPurple
                    onAppColorChanged(newColor)
                }
            )
        }


        SettingRow("Auto Arm Security Alarm") {
            Switch(
                checked = alarmEnabled,
                onCheckedChange = onAlarmToggle,
                colors = SwitchDefaults.colors(checkedThumbColor = appColor)
            )
        }

        SettingRow("App Notifications") {
            Switch(
                checked = notificationsEnabled,
                onCheckedChange = onNotificationsToggle,
                colors = SwitchDefaults.colors(checkedThumbColor = appColor)
            )
        }

        SectionHeader("Voice")
        SettingRow("Voice Assistants") {
            Icon(
                imageVector = Icons.Default.Mic,
                contentDescription = "Voice",
                tint = appColor
            )
        }

        SectionHeader("Permissions")
        SettingRow("Notifications & Permissions") {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Permissions",
                tint = appColor
            )
        }

        Spacer(modifier = Modifier.weight(1f))
        BottomNavigationBar(appColor)
    }
}

@Composable
fun EditUserDialog(
    currentName: String,
    currentEmail: String,
    onDismiss: () -> Unit,
    onConfirm: (String, String) -> Unit
) {
    var name by remember { mutableStateOf(currentName) }
    var email by remember { mutableStateOf(currentEmail) }
    var error by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Edit User Info") },
        text = {
            Column {
                OutlinedTextField(
                    value = name,
                    onValueChange = {
                        name = it
                        error = ""
                    },
                    label = { Text("Name") },
                    isError = name.isBlank()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = email,
                    onValueChange = {
                        email = it
                        error = ""
                    },
                    label = { Text("Email") },
                    isError = !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
                )
                if (error.isNotEmpty()) {
                    Text(
                        text = error,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        },
        confirmButton = {
            TextButton(onClick = {
                if (name.isBlank()) {
                    error = "Name cannot be empty"
                } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    error = "Enter a valid email"
                } else {
                    onConfirm(name.trim(), email.trim())
                }
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Composable
fun SectionHeader(title: String) {
    Text(
        text = title,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        color = Color.DarkGray,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFE0E0E0))
            .padding(horizontal = 16.dp, vertical = 8.dp)
    )
}

@Composable
fun SettingRow(title: String, trailingContent: @Composable () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title, fontSize = 16.sp)
        trailingContent()
    }
}

@Composable
fun BottomNavigationBar(selectedColor: Color) {
    val selectedTab = remember { mutableStateOf("settings") }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomNavItem("Favorites", Icons.Default.Star, selectedTab.value == "favorites", selectedColor) {
            selectedTab.value = "favorites"
        }
        BottomNavItem("Things", Icons.Default.Dashboard, selectedTab.value == "things", selectedColor) {
            selectedTab.value = "things"
        }
        BottomNavItem("Routines", Icons.Default.SettingsBackupRestore, selectedTab.value == "routines", selectedColor) {
            selectedTab.value = "routines"
        }
        BottomNavItem("Ideas", Icons.Default.Lightbulb, selectedTab.value == "ideas", selectedColor) {
            selectedTab.value = "ideas"
        }
        BottomNavItem("Settings", Icons.Default.Settings, selectedTab.value == "settings", selectedColor) {
            selectedTab.value = "settings"
        }
    }
}

@Composable
fun BottomNavItem(
    label: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    isSelected: Boolean,
    selectedColor: Color,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = if (isSelected) selectedColor else Color.Gray,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = label,
            fontSize = 12.sp,
            color = if (isSelected) selectedColor else Color.Gray
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    val dummyNavController = rememberNavController()
    UserSettingsContent(
        navController = dummyNavController,
        userName = "Jane Doe",
        userEmail = "jane@example.com",
        appColor = Color(0xFFFFC107),
        alarmEnabled = true,
        notificationsEnabled = false,
        onUserInfoChanged = { _, _ -> },
        onAppColorChanged = {},
        onAlarmToggle = {},
        onNotificationsToggle = {}
    )
}