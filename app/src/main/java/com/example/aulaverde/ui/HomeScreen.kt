package com.example.aulaverde.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
            .background(fondo)
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
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(24.dp))

                // Bienvenida personalizada
                Text(
                    text = "¡Bienvenido, $nombreProfesor!",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = verde,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Tu espacio para organizar y gestionar documentos docentes.",
                    fontSize = 14.sp,
                    color = verde,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Tutorial de uso
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 3.dp
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(18.dp)
                    ) {

                        Text(
                            text = "¿Cómo usar AulaVerde?",
                            fontSize = 19.sp,
                            fontWeight = FontWeight.Bold,
                            color = verde
                        )

                        HorizontalDivider(
                            color = Color(0xFFE0E8E1)
                        )

                        TutorialItem(
                            numero = "1",
                            titulo = "Gestiona tus documentos",
                            descripcion = "En la sección Documentos puedes seleccionar archivos PDF o Word, agregarlos a la lista, buscarlos y eliminarlos."
                        )

                        HorizontalDivider(
                            color = Color(0xFFE0E8E1)
                        )

                        TutorialItem(
                            numero = "2",
                            titulo = "Administra tu perfil",
                            descripcion = "Consulta tus datos personales y modifica tu nombre o correo electrónico cuando lo necesites."
                        )

                        HorizontalDivider(
                            color = Color(0xFFE0E8E1)
                        )

                        TutorialItem(
                            numero = "3",
                            titulo = "Navega por la aplicación",
                            descripcion = "Utiliza la barra inferior para cambiar entre Inicio, Documentos y Perfil."
                        )
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    text = "AulaVerde · Herramientas para docentes",
                    fontSize = 12.sp,
                    color = verde,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Composable
fun TutorialItem(
    numero: String,
    titulo: String,
    descripcion: String
) {
    val verde = Color(0xFF174C37)

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {

        Surface(
            shape = RoundedCornerShape(10.dp),
            color = Color(0xFFE3F0E8)
        ) {
            Text(
                text = numero,
                modifier = Modifier.padding(
                    horizontal = 14.dp,
                    vertical = 10.dp
                ),
                color = verde,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = titulo,
                fontWeight = FontWeight.Bold,
                color = verde,
                fontSize = 15.sp
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = descripcion,
                fontSize = 13.sp,
                color = Color.DarkGray,
                lineHeight = 19.sp
            )
        }
    }
}