package com.example.aulaverde.data

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "profesores",
    indices = [Index(value = ["correo"], unique = true)]
)
data class Profesor(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val nombre: String,

    val correo: String,

    val passwordHash: String
)
