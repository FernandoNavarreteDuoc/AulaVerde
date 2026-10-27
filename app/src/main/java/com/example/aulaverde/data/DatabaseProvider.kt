package com.example.aulaverde.data

import android.content.Context
import androidx.room.Room

object DatabaseProvider {

    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun obtenerDatabase(context: Context): AppDatabase {
        return INSTANCE ?: synchronized(this) {
            val database = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "aulaverde_database"
            ).build()

            INSTANCE = database
            database
        }
    }
}
