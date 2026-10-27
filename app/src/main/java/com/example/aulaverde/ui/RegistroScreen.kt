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
import com.example.aulaverde.viewmodel.AuthViewModel

@Composable
fun RegistroScreen(
    onVolverClick: () -> Unit,
    viewModel: AuthViewModel
) {
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
            value = viewModel.nombre,
            onValueChange = viewModel::cambiarNombre,
            label = { Text("Nombre completo") },
            isError = viewModel.errorNombre != null,
            supportingText = {
                viewModel.errorNombre?.let {
                    Text(it)
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
            value = viewModel.correo,
            onValueChange = viewModel::cambiarCorreo,
            label = { Text("Correo electrónico") },
            isError = viewModel.errorCorreo != null,
            supportingText = {
                viewModel.errorCorreo?.let {
                    Text(it)
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
            value = viewModel.contrasena,
            onValueChange = viewModel::cambiarContrasena,
            label = { Text("Contraseña") },
            isError = viewModel.errorContrasena != null,
            supportingText = {
                viewModel.errorContrasena?.let {
                    Text(it)
                }
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
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
            value = viewModel.confirmarContrasena,
            onValueChange = viewModel::cambiarConfirmacion,
            label = { Text("Confirmar contraseña") },
            isError = viewModel.errorConfirmacion != null,
            supportingText = {
                viewModel.errorConfirmacion?.let {
                    Text(it)
                }
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
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

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                viewModel.registrarProfesor()
            },
            enabled = !viewModel.registrando,
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = verde
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                if (viewModel.registrando) "Registrando..."
                else "Registrarse"
            )
        }
        viewModel.mensajeRegistro?.let { mensaje ->
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = mensaje,
                color = verde
            )
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