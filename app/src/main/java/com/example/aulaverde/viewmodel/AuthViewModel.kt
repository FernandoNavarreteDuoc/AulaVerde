package com.example.aulaverde.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aulaverde.data.Profesor
import com.example.aulaverde.repository.ProfesorRepository
import com.example.aulaverde.security.PasswordUtils
import com.example.aulaverde.validation.AuthValidator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthViewModel : ViewModel() {

    var nombre by mutableStateOf("")
        private set

    var correo by mutableStateOf("")
        private set

    var contrasena by mutableStateOf("")
        private set

    var confirmarContrasena by mutableStateOf("")
        private set

    var errorNombre by mutableStateOf<String?>(null)
        private set

    var errorCorreo by mutableStateOf<String?>(null)
        private set

    var errorContrasena by mutableStateOf<String?>(null)
        private set

    var errorConfirmacion by mutableStateOf<String?>(null)
        private set

    var mensajeRegistro by mutableStateOf<String?>(null)
        private set

    var registrando by mutableStateOf(false)
        private set

    private var repository: ProfesorRepository? = null

    fun configurarRepository(
        profesorRepository: ProfesorRepository
    ) {
        repository = profesorRepository
    }

    fun cambiarNombre(valor: String) {
        nombre = valor
        errorNombre = null
        mensajeRegistro = null
    }

    fun cambiarCorreo(valor: String) {
        correo = valor
        errorCorreo = null
        mensajeRegistro = null
    }

    fun cambiarContrasena(valor: String) {
        contrasena = valor
        errorContrasena = null
        mensajeRegistro = null
    }

    fun cambiarConfirmacion(valor: String) {
        confirmarContrasena = valor
        errorConfirmacion = null
        mensajeRegistro = null
    }

    fun validarRegistro(): Boolean {
        errorNombre = AuthValidator.validarNombre(nombre)
        errorCorreo = AuthValidator.validarCorreo(correo)
        errorContrasena = AuthValidator.validarContrasena(contrasena)
        errorConfirmacion = AuthValidator.validarConfirmacion(
            contrasena,
            confirmarContrasena
        )

        return errorNombre == null &&
                errorCorreo == null &&
                errorContrasena == null &&
                errorConfirmacion == null
    }

    fun registrarProfesor() {
        if (registrando || !validarRegistro()) return

        val profesorRepository = repository ?: run {
            mensajeRegistro = "No se pudo acceder a la base de datos"
            return
        }

        val nombreRegistro = nombre.trim()
        val correoRegistro = correo.trim().lowercase()
        val contrasenaRegistro = contrasena

        registrando = true
        mensajeRegistro = null

        viewModelScope.launch {
            try {
                val resultado = withContext(Dispatchers.IO) {
                    val existente = profesorRepository
                        .buscarPorCorreo(correoRegistro)

                    if (existente != null) {
                        false
                    } else {
                        val hash = PasswordUtils.generarHash(
                            contrasenaRegistro
                        )

                        val profesor = Profesor(
                            nombre = nombreRegistro,
                            correo = correoRegistro,
                            passwordHash = hash
                        )

                        profesorRepository.registrarProfesor(profesor)
                        true
                    }
                }

                if (resultado) {
                    mensajeRegistro = "Profesor registrado correctamente"
                    limpiarFormulario()
                } else {
                    errorCorreo = "Este correo ya está registrado"
                }

            } catch (e: Exception) {
                mensajeRegistro = "No se pudo completar el registro"
            } finally {
                registrando = false
            }
        }
    }

    private fun limpiarFormulario() {
        nombre = ""
        correo = ""
        contrasena = ""
        confirmarContrasena = ""
    }
}
