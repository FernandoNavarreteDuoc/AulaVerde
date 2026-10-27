package com.example.aulaverde

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.example.aulaverde.ui.BienvenidaScreen
import com.example.aulaverde.ui.LoginScreen
import com.example.aulaverde.ui.theme.AulaVerdeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AulaVerdeTheme {

                var pantallaActual by remember {
                    mutableStateOf("bienvenida")
                }

                when (pantallaActual) {

                    "bienvenida" -> {
                        BienvenidaScreen(
                            onLoginClick = {
                                pantallaActual = "login"
                            },
                            onRegistroClick = {
                                // Lo implementaremos después
                            }
                        )
                    }

                    "login" -> {
                        LoginScreen(
                            onVolverClick = {
                                pantallaActual = "bienvenida"
                            }
                        )
                    }
                }
            }
        }
    }
}