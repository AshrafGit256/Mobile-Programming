package com.example.smartapp.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartapp.room.Routine
import com.example.smartapp.room.RoutineDatabase
import kotlinx.coroutines.launch

class RoutineViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = RoutineDatabase.getDatabase(application).routineDao()

    var routines = mutableStateOf<List<Routine>>(emptyList())
        private set

    init {
        loadRoutines()
    }

    fun loadRoutines() {
        viewModelScope.launch {
            routines.value = dao.getAllRoutines()
        }
    }

    fun addRoutine(routine: Routine) {
        viewModelScope.launch {
            dao.insertRoutine(routine)
            loadRoutines()
        }
    }
}
