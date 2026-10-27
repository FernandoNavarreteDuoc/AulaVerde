package com.example.aulaverde

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aulaverde.data.DatabaseProvider
import com.example.aulaverde.repository.ProfesorRepository
import com.example.aulaverde.ui.BienvenidaScreen
import com.example.aulaverde.ui.LoginScreen
import com.example.aulaverde.ui.RegistroScreen
import com.example.aulaverde.ui.theme.AulaVerdeTheme
import com.example.aulaverde.viewmodel.AuthViewModel
import com.example.aulaverde.ui.PerfilScreen
import com.example.aulaverde.ui.EditarPerfilScreen

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
                        val profesor = authViewModel.profesorAutenticado

                        if (profesor != null) {
                            LaunchedEffect(profesor.id) {
                                pantallaActual = "perfil"
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

                    "perfil" -> {
                        val profesor = authViewModel.profesorAutenticado

                        if (profesor != null) {
                            PerfilScreen(
                                profesor = profesor,
                                onEditarPerfil = {
                                    authViewModel.limpiarErrorEditarPerfil()
                                    pantallaActual = "editarPerfil"
                                },
                                onCerrarSesion = {
                                    authViewModel.cerrarSesion()
                                    pantallaActual = "bienvenida"
                                }
                            )
                        } else {
                            LaunchedEffect(Unit) {
                                pantallaActual = "bienvenida"
                            }
                        }
                    }

                    "editarPerfil" -> {
                        val profesor = authViewModel.profesorAutenticado

                        if (profesor != null) {
                            EditarPerfilScreen(
                                profesor = profesor,
                                viewModel = authViewModel,
                                onGuardarClick = { nombre, correo ->
                                    authViewModel.actualizarPerfil(
                                        nuevoNombre = nombre,
                                        nuevoCorreo = correo,
                                        onExito = {
                                            pantallaActual = "perfil"
                                        }
                                    )
                                },
                                onVolverClick = {
                                    if (!authViewModel.guardandoPerfil) {
                                        authViewModel.limpiarErrorEditarPerfil()
                                        pantallaActual = "perfil"
                                    }
                                }
                            )
                        } else {
                            LaunchedEffect(Unit) {
                                pantallaActual = "bienvenida"
                            }
                        }
                    }
                }
            }
        }
    }
}
