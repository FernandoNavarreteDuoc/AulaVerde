package com.example.aulaverde.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aulaverde.data.Profesor

@Composable
fun PerfilScreen(
    profesor: Profesor,
    onEditarPerfil: () -> Unit,
    onCerrarSesion: () -> Unit
) {
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
            text = "Mi perfil",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = verde
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Nombre: ${profesor.nombre}",
            fontSize = 18.sp,
            color = verde
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Correo: ${profesor.correo}",
            fontSize = 16.sp,
            color = verde
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onEditarPerfil,
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = verde
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Editar perfil")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onCerrarSesion,
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = verde
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Cerrar sesión")
        }
    }
}
