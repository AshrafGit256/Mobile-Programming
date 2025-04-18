package com.example.smartapp.viewmodel

import android.app.Application
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartapp.data.PreferencesManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SettingsViewModel(application: Application) : AndroidViewModel(application) {

    private val prefs = PreferencesManager(application)

    private val _userName = MutableStateFlow(prefs.getUserName())
    val userName: StateFlow<String> = _userName

    private val _userEmail = MutableStateFlow(prefs.getUserEmail())
    val userEmail: StateFlow<String> = _userEmail

    private val _alarmEnabled = MutableStateFlow(prefs.isAlarmEnabled())
    val alarmEnabled: StateFlow<Boolean> = _alarmEnabled

    private val _notificationsEnabled = MutableStateFlow(prefs.isNotificationsEnabled())
    val notificationsEnabled: StateFlow<Boolean> = _notificationsEnabled

    private val _appColor = MutableStateFlow(Color(prefs.getAppColor()))
    val appColor: StateFlow<Color> = _appColor

    fun updateUserInfo(name: String, email: String) {
        prefs.saveUserInfo(name, email)
        _userName.value = name
        _userEmail.value = email
    }

    fun toggleAlarm(enabled: Boolean) {
        prefs.setAlarmEnabled(enabled)
        _alarmEnabled.value = enabled
    }

    fun toggleNotifications(enabled: Boolean) {
        prefs.setNotificationsEnabled(enabled)
        _notificationsEnabled.value = enabled
    }

    fun setAppColor(color: Color) {
        prefs.saveAppColor(color.hashCode())
        _appColor.value = color
    }
}