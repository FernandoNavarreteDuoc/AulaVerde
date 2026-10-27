package com.example.aulaverde.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ProfesorDao {

    @Insert
    suspend fun insertarProfesor(profesor: Profesor): Long

    @Query("SELECT * FROM profesores WHERE correo = :correo LIMIT 1")
    suspend fun buscarPorCorreo(correo: String): Profesor?

    @Query("SELECT * FROM profesores WHERE id = :id LIMIT 1")
    suspend fun buscarPorId(id: Int): Profesor?

    @Update
    suspend fun actualizarProfesor(profesor: Profesor)
}
