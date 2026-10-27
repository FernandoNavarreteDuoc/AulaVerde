package com.example.aulaverde.validation

import android.util.Patterns

object AuthValidator {

    fun validarNombre(nombre: String): String? {
        if (nombre.isBlank()) {
            return "Ingresa tu nombre completo"
        }

        if (nombre.trim().length < 3) {
            return "El nombre debe tener al menos 3 caracteres"
        }

        return null
    }

    fun validarCorreo(correo: String): String? {
        if (correo.isBlank()) {
            return "Ingresa tu correo electrónico"
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            return "Ingresa un correo electrónico válido"
        }

        return null
    }

    fun validarContrasena(contrasena: String): String? {
        if (contrasena.isBlank()) {
            return "Ingresa una contraseña"
        }

        if (contrasena.length < 8) {
            return "La contraseña debe tener al menos 8 caracteres"
        }

        return null
    }

    fun validarConfirmacion(
        contrasena: String,
        confirmacion: String
    ): String? {
        if (confirmacion.isBlank()) {
            return "Confirma tu contraseña"
        }

        if (contrasena != confirmacion) {
            return "Las contraseñas no coinciden"
        }

        return null
    }
}