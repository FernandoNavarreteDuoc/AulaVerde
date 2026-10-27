package com.example.aulaverde.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aulaverde.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    onVolverClick: () -> Unit,
    viewModel: AuthViewModel
) {

    var mostrarContrasena by remember { mutableStateOf(false) }

    val verde = Color(0xFF174C37)
    val fondo = Color(0xFFE8F1EA)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(fondo)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Iniciar sesión",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = verde
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Bienvenido a AulaVerde",
            fontSize = 17.sp,
            color = verde
        )

        Spacer(modifier = Modifier.height(48.dp))

        OutlinedTextField(
            value = viewModel.correoLogin,
            onValueChange = { viewModel.cambiarCorreoLogin(it) },
            label = { Text("Correo electrónico") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedBorderColor = verde,
                focusedLabelColor = verde
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = viewModel.contrasenaLogin,
            onValueChange = { viewModel.cambiarContrasenaLogin(it) },
            label = { Text("Contraseña") },
            visualTransformation = if (mostrarContrasena) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            trailingIcon = {
                TextButton(
                    onClick = {
                        mostrarContrasena = !mostrarContrasena
                    }
                ) {
                    Text(
                        text = if (mostrarContrasena) {
                            "Ocultar"
                        } else {
                            "Ver"
                        },
                        color = verde
                    )
                }
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedBorderColor = verde,
                focusedLabelColor = verde
            )
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                viewModel.iniciarSesion()
            },
            enabled = !viewModel.iniciandoSesion,
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = verde
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = if (viewModel.iniciandoSesion) {
                    "Ingresando..."
                } else {
                    "Ingresar"
                },
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        viewModel.errorLogin?.let { error ->
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = error,
                color = Color.Red
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = onVolverClick) {
            Text(
                text = "Volver a bienvenida",
                color = verde
            )
        }
    }
}