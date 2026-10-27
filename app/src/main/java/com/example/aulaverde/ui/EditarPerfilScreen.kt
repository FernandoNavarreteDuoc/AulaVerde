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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aulaverde.data.Profesor
import com.example.aulaverde.viewmodel.AuthViewModel
import androidx.compose.ui.platform.LocalView
import android.view.HapticFeedbackConstants

@Composable
fun EditarPerfilScreen(
    profesor: Profesor,
    viewModel: AuthViewModel,
    onGuardarClick: (String, String, () -> Unit) -> Unit,
    onVolverClick: () -> Unit
) {
    var nombre by remember(profesor.id) {
        mutableStateOf(profesor.nombre)
    }

    var correo by remember(profesor.id) {
        mutableStateOf(profesor.correo)
    }

    val view = LocalView.current
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
            text = "Editar perfil",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = verde
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre completo") },
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
            value = correo,
            onValueChange = { correo = it },
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

        viewModel.errorEditarPerfil?.let { error ->
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = error,
                color = MaterialTheme.colorScheme.error
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                onGuardarClick(nombre, correo) {
                    view.performHapticFeedback(
                        HapticFeedbackConstants.VIRTUAL_KEY
                    )
                }
            },
            enabled = !viewModel.guardandoPerfil,
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = verde
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = if (viewModel.guardandoPerfil) {
                    "Guardando..."
                } else {
                    "Guardar cambios"
                },
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = onVolverClick) {
            Text(
                text = "Volver a mi perfil",
                color = verde
            )
        }
    }
}
