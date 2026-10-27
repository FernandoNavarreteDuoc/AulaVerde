package com.example.aulaverde

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.aulaverde.ui.BienvenidaScreen
import com.example.aulaverde.ui.theme.AulaVerdeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AulaVerdeTheme {
                BienvenidaScreen(
                    onLoginClick = {},
                    onRegistroClick = {}
                )
            }
        }
    }
}