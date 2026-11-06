package com.example.aulaverde.ui

import android.graphics.BitmapFactory
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aulaverde.data.Profesor
import java.io.File

@Composable
fun PerfilScreen(
    profesor: Profesor,
    onEditarPerfil: () -> Unit
) {
    val verde = Color(0xFF174C37)
    val fondo = Color(0xFFE8F1EA)
    val context = LocalContext.current

    // Archivo privado para guardar la foto del profesor
    val fotoFile = remember(profesor.correo) {
        val identificador = profesor.correo.hashCode().toUInt()
        File(context.filesDir, "perfil_$identificador.jpg")
    }

    var fotoVersion by remember(profesor.correo) {
        mutableIntStateOf(0)
    }

    var errorFoto by remember {
        mutableStateOf<String?>(null)
    }

    // Recuperar la foto guardada
    val fotoBitmap = remember(fotoFile.absolutePath, fotoVersion) {
        try {
            if (fotoFile.exists()) {
                val opciones = BitmapFactory.Options().apply {
                    inJustDecodeBounds = true
                }

                BitmapFactory.decodeFile(
                    fotoFile.absolutePath,
                    opciones
                )

                val mayorDimension = maxOf(
                    opciones.outWidth,
                    opciones.outHeight
                )

                var escala = 1

                while (mayorDimension / escala > 512) {
                    escala *= 2
                }

                BitmapFactory.decodeFile(
                    fotoFile.absolutePath,
                    BitmapFactory.Options().apply {
                        inSampleSize = escala
                    }
                )?.asImageBitmap()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    // Abrir galería y guardar la foto seleccionada
    val selectorFoto = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->

        uri?.let {
            try {
                context.contentResolver.openInputStream(it)?.use { entrada ->
                    fotoFile.outputStream().use { salida ->
                        entrada.copyTo(salida)
                    }
                } ?: throw IllegalStateException(
                    "No se pudo leer la imagen"
                )

                fotoVersion++
                errorFoto = null

            } catch (e: Exception) {
                errorFoto = "No se pudo guardar la fotografía"
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(fondo),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // Foto de perfil circular
            Box(
                modifier = Modifier
                    .size(130.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFD5E8DA))
                    .border(
                        width = 3.dp,
                        color = verde,
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (fotoBitmap != null) {
                    Image(
                        bitmap = fotoBitmap,
                        contentDescription = "Foto de perfil",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Text(
                        text = profesor.nombre
                            .trim()
                            .take(1)
                            .uppercase(),
                        fontSize = 55.sp,
                        fontWeight = FontWeight.Bold,
                        color = verde
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón para cambiar la foto
            OutlinedButton(
                onClick = {
                    selectorFoto.launch("image/*")
                },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = verde
                )
            ) {
                Text("Cambiar foto")
            }

            if (errorFoto != null) {
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = errorFoto ?: "",
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 12.sp
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Título
            Text(
                text = "Mi perfil",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = verde
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Nombre del profesor
            Text(
                text = "Nombre: ${profesor.nombre}",
                fontSize = 18.sp,
                color = verde,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Correo del profesor
            Text(
                text = "Correo: ${profesor.correo}",
                fontSize = 16.sp,
                color = verde,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Botón para editar perfil
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
        }
    }
}