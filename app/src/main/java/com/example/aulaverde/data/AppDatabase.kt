package com.example.aulaverde.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Profesor::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun profesorDao(): ProfesorDao
}
