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

                    "login" -> LoginScreen(
                        onVolverClick = {
                            pantallaActual = "bienvenida"
                        }
                    )

                    "registro" -> RegistroScreen(
                        onVolverClick = {
                            pantallaActual = "bienvenida"
                        },
                        viewModel = authViewModel
                    )
                }
            }
        }
    }
}
