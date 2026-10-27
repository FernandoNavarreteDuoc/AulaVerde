package com.example.aulaverde.security

import android.util.Base64
import java.security.SecureRandom
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec
import java.security.MessageDigest

object PasswordUtils {

    private const val ITERACIONES = 600_000
    private const val LONGITUD_CLAVE = 256
    private const val LONGITUD_SAL = 16

    fun generarHash(contrasena: String): String {
        val sal = ByteArray(LONGITUD_SAL)
        SecureRandom().nextBytes(sal)

        val hash = derivarClave(contrasena, sal)

        val salTexto = Base64.encodeToString(sal, Base64.NO_WRAP)
        val hashTexto = Base64.encodeToString(hash, Base64.NO_WRAP)

        return "$ITERACIONES:$salTexto:$hashTexto"
    }

    fun verificarContrasena(
        contrasena: String,
        hashGuardado: String
    ): Boolean {
        return try {
            val partes = hashGuardado.split(":")
            if (partes.size != 3) return false

            val iteraciones = partes[0].toInt()
            if (iteraciones != ITERACIONES) return false

            val sal = Base64.decode(partes[1], Base64.NO_WRAP)
            val hashOriginal = Base64.decode(partes[2], Base64.NO_WRAP)

            if (sal.size != LONGITUD_SAL ||
                hashOriginal.size != LONGITUD_CLAVE / 8
            ) return false

            val hashNuevo = derivarClave(contrasena, sal)

            MessageDigest.isEqual(hashOriginal, hashNuevo)
        } catch (e: Exception) {
            false
        }
    }

    private fun derivarClave(
        contrasena: String,
        sal: ByteArray
    ): ByteArray {
        val especificacion = PBEKeySpec(
            contrasena.toCharArray(),
            sal,
            ITERACIONES,
            LONGITUD_CLAVE
        )

        return try {
            val fabrica = SecretKeyFactory.getInstance(
                "PBKDF2WithHmacSHA256"
            )
            fabrica.generateSecret(especificacion).encoded
        } finally {
            especificacion.clearPassword()
        }
    }
}
