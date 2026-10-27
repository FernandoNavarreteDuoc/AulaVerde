package com.example.aulaverde.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(
    nombreProfesor: String,
    onPerfilClick: () -> Unit,
    onDocumentosClick: () -> Unit
) {
    val verde = Color(0xFF174C37)
    val fondo = Color(0xFFE8F1EA)

    var mostrarContenido by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        mostrarContenido = true
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(fondo),
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visible = mostrarContenido,
            enter = fadeIn(
                animationSpec = tween(durationMillis = 700)
            ) + slideInVertically(
                animationSpec = tween(durationMillis = 700),
                initialOffsetY = { it / 4 }
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "¡Bienvenido, $nombreProfesor!",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = verde
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Selecciona una herramienta para comenzar",
                    color = verde
                )

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = onDocumentosClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = verde
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Gestionar documentos")
                }

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedButton(
                    onClick = onPerfilClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "Mi perfil",
                        color = verde
                    )
                }
            }
        }
    }
}