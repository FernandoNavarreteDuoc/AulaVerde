package com.example.aulaverde.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.aulaverde.validation.AuthValidator

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

    fun cambiarNombre(valor: String) {
        nombre = valor
        errorNombre = null
    }

    fun cambiarCorreo(valor: String) {
        correo = valor
        errorCorreo = null
    }

    fun cambiarContrasena(valor: String) {
        contrasena = valor
        errorContrasena = null
    }

    fun cambiarConfirmacion(valor: String) {
        confirmarContrasena = valor
        errorConfirmacion = null
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
}