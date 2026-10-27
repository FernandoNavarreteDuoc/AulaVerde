package com.example.aulaverde

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aulaverde.data.DatabaseProvider
import com.example.aulaverde.repository.ProfesorRepository
import com.example.aulaverde.ui.*
import com.example.aulaverde.ui.theme.AulaVerdeTheme
import com.example.aulaverde.viewmodel.AuthViewModel


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = DatabaseProvider.obtenerDatabase(
            applicationContext
        )

        val profesorRepository = ProfesorRepository(
            database.profesorDao()
        )

        setContent {
            AulaVerdeTheme {
                val authViewModel: AuthViewModel = viewModel()

                LaunchedEffect(authViewModel) {
                    authViewModel.configurarRepository(
                        profesorRepository
                    )
                }

                var pantallaActual by remember {
                    mutableStateOf("bienvenida")
                }

                val profesor = authViewModel.profesorAutenticado

                val cerrarSesion = {
                    authViewModel.cerrarSesion()
                    pantallaActual = "bienvenida"
                }

                val pantallasConBarras = listOf(
                    "home",
                    "documentos",
                    "perfil",
                    "editarPerfil"
                )

                if (
                    profesor != null &&
                    pantallaActual in pantallasConBarras
                ) {
                    Scaffold(
                        topBar = {
                            AulaVerdeTopBar(
                                onPerfilClick = {
                                    if (!authViewModel.guardandoPerfil) {
                                        pantallaActual = "perfil"
                                    }
                                },
                                onCerrarSesion = {
                                    if (!authViewModel.guardandoPerfil) {
                                        cerrarSesion()
                                    }
                                }
                            )
                        },
                        bottomBar = {
                            AulaVerdeBottomBar(
                                pantallaActual = pantallaActual,
                                onInicioClick = {
                                    if (!authViewModel.guardandoPerfil) {
                                        pantallaActual = "home"
                                    }
                                },
                                onDocumentosClick = {
                                    if (!authViewModel.guardandoPerfil) {
                                        pantallaActual = "documentos"
                                    }
                                },
                                onPerfilClick = {
                                    if (!authViewModel.guardandoPerfil) {
                                        pantallaActual = "perfil"
                                    }
                                }
                            )
                        }
                    ) { paddingValues ->

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(paddingValues)
                        ) {
                            when (pantallaActual) {

                                "home" -> HomeScreen(
                                    nombreProfesor = profesor.nombre,
                                    onPerfilClick = {
                                        pantallaActual = "perfil"
                                    },
                                    onDocumentosClick = {
                                        pantallaActual = "documentos"
                                    }
                                )

                                "documentos" -> DocumentManagementScreen()

                                "perfil" -> PerfilScreen(
                                    profesor = profesor,
                                    onEditarPerfil = {
                                        authViewModel.limpiarErrorEditarPerfil()
                                        pantallaActual = "editarPerfil"
                                    }
                                )

                                "editarPerfil" -> EditarPerfilScreen(
                                    profesor = profesor,
                                    viewModel = authViewModel,
                                    onGuardarClick = { nombre, correo, onGuardado ->
                                        authViewModel.actualizarPerfil(
                                            nuevoNombre = nombre,
                                            nuevoCorreo = correo,
                                            onExito = {
                                                onGuardado()
                                                pantallaActual = "perfil"
                                            }
                                        )
                                    },
                                    onVolverClick = {
                                        if (!authViewModel.guardandoPerfil) {
                                            authViewModel
                                                .limpiarErrorEditarPerfil()
                                            pantallaActual = "perfil"
                                        }
                                    }
                                )
                            }
                        }
                    }
                } else {
                    when (pantallaActual) {

                        "bienvenida" -> BienvenidaScreen(
                            onLoginClick = {
                                pantallaActual = "login"
                            },
                            onRegistroClick = {
                                pantallaActual = "registro"
                            }
                        )

                        "login" -> {
                            if (profesor != null) {
                                LaunchedEffect(profesor.id) {
                                    pantallaActual = "home"
                                }
                            }

                            LoginScreen(
                                onVolverClick = {
                                    pantallaActual = "bienvenida"
                                },
                                viewModel = authViewModel
                            )
                        }

                        "registro" -> RegistroScreen(
                            onVolverClick = {
                                pantallaActual = "bienvenida"
                            },
                            viewModel = authViewModel
                        )

                        else -> {
                            LaunchedEffect(pantallaActual) {
                                pantallaActual = "bienvenida"
                            }
                        }
                    }
                }
            }
        }
    }
}
