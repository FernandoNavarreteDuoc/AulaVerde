package com.example.aulaverde.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BienvenidaScreen(
    onLoginClick: () -> Unit,
    onRegistroClick: () -> Unit
) {
    val verde = Color(0xFF174C37)
    val blanco = Color.White

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(verde)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "AulaVerde",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = blanco
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Gestión académica\npara docentes",
            fontSize = 18.sp,
            color = blanco,
            textAlign = TextAlign.Center,
            lineHeight = 26.sp
        )

        Spacer(modifier = Modifier.height(64.dp))

        Button(
            onClick = onLoginClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = blanco,
                contentColor = verde
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Iniciar sesión",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(
            onClick = onRegistroClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            border = androidx.compose.foundation.BorderStroke(
                2.dp,
                blanco
            ),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = blanco
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Registrarse",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}