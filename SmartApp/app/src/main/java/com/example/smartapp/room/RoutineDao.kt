package com.example.smartapp.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RoutineDao {
    @Query("SELECT * FROM routines")
    suspend fun getAllRoutines(): List<Routine>

    @Insert
    suspend fun insertRoutine(routine: Routine)
}
