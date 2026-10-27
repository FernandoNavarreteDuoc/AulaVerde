package com.example.aulaverde.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aulaverde.validation.AuthValidator

@Composable
fun RegistroScreen(
    onVolverClick: () -> Unit
) {
    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var confirmarContrasena by remember { mutableStateOf("") }

    var errorNombre by remember { mutableStateOf<String?>(null) }
    var errorCorreo by remember { mutableStateOf<String?>(null) }
    var errorContrasena by remember { mutableStateOf<String?>(null) }
    var errorConfirmacion by remember { mutableStateOf<String?>(null) }

    val verde = Color(0xFF174C37)
    val fondo = Color(0xFFE8F1EA)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(fondo)
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Registro de profesor",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = verde
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Crea tu cuenta en AulaVerde",
            color = verde
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = nombre,
            onValueChange = {
                nombre = it
                errorNombre = null
            },
            label = { Text("Nombre completo") },
            isError = errorNombre != null,
            supportingText = {
                if (errorNombre != null) {
                    Text(errorNombre!!)
                }
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedBorderColor = verde
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = correo,
            onValueChange = {
                correo = it
                errorCorreo = null
            },
            label = { Text("Correo electrónico") },
            isError = errorCorreo != null,
            supportingText = {
                if (errorCorreo != null) {
                    Text(errorCorreo!!)
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedBorderColor = verde
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = contrasena,
            onValueChange = {
                contrasena = it
                errorContrasena = null
            },
            label = { Text("Contraseña") },
            isError = errorContrasena != null,
            supportingText = {
                if (errorContrasena != null) {
                    Text(errorContrasena!!)
                }
            },
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedBorderColor = verde
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = confirmarContrasena,
            onValueChange = {
                confirmarContrasena = it
                errorConfirmacion = null
            },
            label = { Text("Confirmar contraseña") },
            isError = errorConfirmacion != null,
            supportingText = {
                if (errorConfirmacion != null) {
                    Text(errorConfirmacion!!)
                }
            },
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedBorderColor = verde
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                errorNombre = AuthValidator.validarNombre(nombre)
                errorCorreo = AuthValidator.validarCorreo(correo)
                errorContrasena = AuthValidator.validarContrasena(contrasena)
                errorConfirmacion = AuthValidator.validarConfirmacion(
                    contrasena,
                    confirmarContrasena
                )

                val formularioValido =
                    errorNombre == null &&
                            errorCorreo == null &&
                            errorContrasena == null &&
                            errorConfirmacion == null

                if (formularioValido) {
                    // Más adelante guardaremos el profesor en Room
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = verde
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Registrarse")
        }

        Spacer(modifier = Modifier.height(12.dp))

        TextButton(onClick = onVolverClick) {
            Text(
                text = "Volver a bienvenida",
                color = verde
            )
        }
    }
}