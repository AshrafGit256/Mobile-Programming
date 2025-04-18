package com.example.smartapp.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues

class RoutineDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, "routines.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE routines (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT,
                time TEXT,
                recurrence TEXT
            )
        """)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS routines")
        onCreate(db)
    }

    fun insertRoutine(name: String, time: String, recurrence: String): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("name", name)
            put("time", time)
            put("recurrence", recurrence)
        }
        return db.insert("routines", null, values)
    }

    fun getAllRoutines(): List<Routine> {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM routines", null)
        val routines = mutableListOf<Routine>()
        while (cursor.moveToNext()) {
            val id = cursor.getInt(0)
            val name = cursor.getString(1)
            val time = cursor.getString(2)
            val recurrence = cursor.getString(3)
            routines.add(Routine(id, name, time, recurrence))
        }
        cursor.close()
        return routines
    }
}

data class Routine(val id: Int, val name: String, val time: String, val recurrence: String)

