package com.example.smartapp.data

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.ui.graphics.Color
import androidx.core.content.edit

class PreferencesManager(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences("settings_prefs", Context.MODE_PRIVATE)

    companion object {
        const val KEY_NAME = "user_name"
        const val KEY_EMAIL = "user_email"
        const val KEY_APP_COLOR = "app_color"
        const val KEY_ALARM_ENABLED = "alarm_enabled"
        const val KEY_NOTIFICATIONS_ENABLED = "notifications_enabled"
    }

    fun saveUserInfo(name: String, email: String) {
        prefs.edit {
            putString(KEY_NAME, name)
            putString(KEY_EMAIL, email)
        }
    }

    fun getUserName(): String = prefs.getString(KEY_NAME, "John Doe") ?: "John Doe"
    fun getUserEmail(): String = prefs.getString(KEY_EMAIL, "john@someorg.com") ?: "john@someorg.com"

    fun saveAppColor(color: Int) {
        prefs.edit {
            putInt(KEY_APP_COLOR, color)
        }
    }

    fun getAppColor(): Int = prefs.getInt(KEY_APP_COLOR, Color.Yellow.hashCode()) // default Yellow

    fun setAlarmEnabled(enabled: Boolean) {
        prefs.edit { putBoolean(KEY_ALARM_ENABLED, enabled) }
    }

    fun isAlarmEnabled(): Boolean = prefs.getBoolean(KEY_ALARM_ENABLED, false)

    fun setNotificationsEnabled(enabled: Boolean) {
        prefs.edit { putBoolean(KEY_NOTIFICATIONS_ENABLED, enabled) }
    }

    fun isNotificationsEnabled(): Boolean = prefs.getBoolean(KEY_NOTIFICATIONS_ENABLED, false)
}