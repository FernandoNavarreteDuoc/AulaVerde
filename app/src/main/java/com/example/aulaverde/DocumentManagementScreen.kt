package com.example.aulaverde

import android.net.Uri
import android.provider.OpenableColumns
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class DocumentoItem(
    val id: Int,
    val titulo: String,
    val tipo: String,
    val asignatura: String,
    val fecha: String,
    val uri: Uri? = null
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DocumentManagementScreen() {
    val greenCarabineros = Color(0xFF005A36)
    val backgroundLight = Color(0xFFF4F6F5)
    val context = LocalContext.current

    var tituloDoc by remember { mutableStateOf("") }
    var tipoDoc by remember { mutableStateOf("") }
    var asignaturaDoc by remember { mutableStateOf("") }
    var searchQuery by remember { mutableStateOf("") }
    var errorTitulo by remember { mutableStateOf(false) }
    var archivoSeleccionadoUri by remember { mutableStateOf<Uri?>(null) }
    var documentoAVisualizar by remember { mutableStateOf<DocumentoItem?>(null) }

    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->
        uri?.let {
            archivoSeleccionadoUri = it
            val cursor = context.contentResolver.query(it, null, null, null, null)
            cursor?.use { c ->
                if (c.moveToFirst()) {
                    val nameIndex = c.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                    if (nameIndex != -1) {
                        tituloDoc = c.getString(nameIndex)
                    }
                }
            }
            if (tituloDoc.isBlank()) {
                tituloDoc = "Documento_Adjunto"
            }
            errorTitulo = false
        }
    }

    val listaDocumentos = remember {
        mutableStateListOf(
            DocumentoItem(1, "Reglamento Académico 2026.pdf", "PDF", "Doctrina Institucional", "20/10/2026"),
            DocumentoItem(2, "Guía Ejercicios Tiro Táctico.docx", "Guía", "Instrucción Policial", "22/10/2026"),
            DocumentoItem(3, "Manual de Procedimientos.pdf", "PDF", "Derecho Penal", "25/10/2026")
        )
    }

    // Pantalla / Ventana Modal de Visualización de Documento
    documentoAVisualizar?.let { doc ->
        AlertDialog(
            onDismissRequest = { documentoAVisualizar = null },
            confirmButton = {
                Button(
                    onClick = { documentoAVisualizar = null },
                    colors = ButtonDefaults.buttonColors(containerColor = greenCarabineros)
                ) {
                    Text("Cerrar Visor")
                }
            },
            title = {
                Text(
                    text = "📄 ${doc.titulo}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = greenCarabineros
                )
            },
            text = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text("Detalles del Documento Docente:", fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
                    Spacer(modifier = Modifier.height(6.dp))
                    Text("• Tipo: ${doc.tipo}")
                    Text("• Asignatura: ${doc.asignatura}")
                    Text("• Fecha de Carga: ${doc.fecha}")
                    Spacer(modifier = Modifier.height(12.dp))
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9))
                    ) {
                        Text(
                            text = "Vista Previa: El documento '${doc.titulo}' está validado y almacenado correctamente en la plataforma AulaVerde.",
                            fontSize = 12.sp,
                            color = greenCarabineros,
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                }
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "GESTIÓN DE DOCUMENTOS",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = greenCarabineros)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(backgroundLight)
                .padding(16.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Subir Nuevo Documento",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = greenCarabineros
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedButton(
                            onClick = { filePickerLauncher.launch(arrayOf("*/*")) },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = greenCarabineros)
                        ) {
                            Text("📁 Seleccionar archivo", fontWeight = FontWeight.SemiBold, fontSize = 12.sp)
                        }

                        if (archivoSeleccionadoUri != null || tituloDoc.isNotBlank()) {
                            TextButton(
                                onClick = {
                                    tituloDoc = ""
                                    archivoSeleccionadoUri = null
                                    errorTitulo = false
                                }
                            ) {
                                Text("Limpiar", color = Color(0xFFC62828), fontSize = 12.sp)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = tituloDoc,
                        onValueChange = {
                            tituloDoc = it
                            errorTitulo = it.isBlank()
                        },
                        label = { Text("Nombre / Título del Archivo") },
                        isError = errorTitulo,
                        supportingText = {
                            if (errorTitulo) {
                                Text("El nombre es obligatorio", color = MaterialTheme.colorScheme.error)
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedTextField(
                            value = tipoDoc,
                            onValueChange = { tipoDoc = it },
                            label = { Text("Tipo (PDF, DOCX)") },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(8.dp),
                            singleLine = true
                        )

                        OutlinedTextField(
                            value = asignaturaDoc,
                            onValueChange = { asignaturaDoc = it },
                            label = { Text("Asignatura") },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(8.dp),
                            singleLine = true
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = {
                            if (tituloDoc.isNotBlank()) {
                                val extension = if (tituloDoc.contains(".")) "" else ".pdf"
                                listaDocumentos.add(
                                    DocumentoItem(
                                        id = (listaDocumentos.maxOfOrNull { it.id } ?: 0) + 1,
                                        titulo = tituloDoc + extension,
                                        tipo = if (tipoDoc.isNotBlank()) tipoDoc else "Documento",
                                        asignatura = if (asignaturaDoc.isNotBlank()) asignaturaDoc else "General",
                                        fecha = "27/10/2026",
                                        uri = archivoSeleccionadoUri
                                    )
                                )
                                tituloDoc = ""
                                tipoDoc = ""
                                asignaturaDoc = ""
                                archivoSeleccionadoUri = null
                                errorTitulo = false
                            } else {
                                errorTitulo = true
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = greenCarabineros),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("ALMACENAR DOCUMENTO", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(8.dp)),
                placeholder = { Text("Buscar documento...", fontSize = 13.sp) },
                singleLine = true,
                shape = RoundedCornerShape(8.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Documentos Almacenados (${listaDocumentos.size})",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = greenCarabineros
            )

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(listaDocumentos.filter { it.titulo.contains(searchQuery, ignoreCase = true) }) { doc ->
                    DocumentoCardItem(
                        documento = doc,
                        onDelete = { listaDocumentos.remove(doc) },
                        onView = { documentoAVisualizar = doc }
                    )
                }
            }
        }
    }
}

@Composable
fun DocumentoCardItem(
    documento: DocumentoItem,
    onDelete: () -> Unit,
    onView: () -> Unit
) {
    val greenCarabineros = Color(0xFF005A36)

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = documento.titulo,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "${documento.tipo} • ${documento.asignatura}",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                Text(
                    text = "Subido: ${documento.fecha}",
                    fontSize = 11.sp,
                    color = Color.Gray
                )
            }

            TextButton(onClick = onView) {
                Text("Ver", color = greenCarabineros, fontWeight = FontWeight.Bold)
            }

            TextButton(onClick = onDelete) {
                Text("Eliminar", color = Color(0xFFC62828), fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DocumentManagementScreenPreview() {
    DocumentManagementScreen()
}