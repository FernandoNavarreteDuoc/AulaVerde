package com.example.aulaverde.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val Verde = Color(0xFF174C37)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AulaVerdeTopBar(
    onPerfilClick: () -> Unit,
    onCerrarSesion: () -> Unit
) {
    TopAppBar(
        title = {
            Text("AulaVerde", color = Color.White)
        },
        actions = {
            IconButton(onClick = onPerfilClick) {
                Icon(
                    Icons.Default.AccountCircle,
                    contentDescription = "Mi perfil",
                    tint = Color.White
                )
            }

            TextButton(onClick = onCerrarSesion) {
                Text("Salir", color = Color.White)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Verde
        )
    )
}

@Composable
fun AulaVerdeBottomBar(
    pantallaActual: String,
    onInicioClick: () -> Unit,
    onDocumentosClick: () -> Unit,
    onPerfilClick: () -> Unit
) {
    NavigationBar(containerColor = Verde) {

        NavigationBarItem(
            selected = pantallaActual == "home",
            onClick = onInicioClick,
            icon = {
                Icon(Icons.Default.Home, contentDescription = "Inicio")
            },
            label = { Text("Inicio") },
            colors = coloresNavegacion()
        )

        NavigationBarItem(
            selected = pantallaActual == "documentos",
            onClick = onDocumentosClick,
            icon = {
                Icon(Icons.Default.List, contentDescription = "Documentos")
            },
            label = { Text("Documentos") },
            colors = coloresNavegacion()
        )

        NavigationBarItem(
            selected = pantallaActual == "perfil" ||
                    pantallaActual == "editarPerfil",
            onClick = onPerfilClick,
            icon = {
                Icon(
                    Icons.Default.AccountCircle,
                    contentDescription = "Perfil"
                )
            },
            label = { Text("Perfil") },
            colors = coloresNavegacion()
        )
    }
}

@Composable
private fun coloresNavegacion() =
    NavigationBarItemDefaults.colors(
        selectedIconColor = Verde,
        selectedTextColor = Color.White,
        unselectedIconColor = Color.White,
        unselectedTextColor = Color.White,
        indicatorColor = Color(0xFFE8F1EA)
    )