package com.example.aulaverde.repository

import com.example.aulaverde.data.Profesor
import com.example.aulaverde.data.ProfesorDao

class ProfesorRepository(
    private val profesorDao: ProfesorDao
) {

    suspend fun registrarProfesor(profesor: Profesor): Long {
        return profesorDao.insertarProfesor(profesor)
    }

    suspend fun buscarPorCorreo(correo: String): Profesor? {
        return profesorDao.buscarPorCorreo(correo)
    }

    suspend fun buscarPorId(id: Int): Profesor? {
        return profesorDao.buscarPorId(id)
    }

    suspend fun actualizarProfesor(profesor: Profesor) {
        profesorDao.actualizarProfesor(profesor)
    }
}
