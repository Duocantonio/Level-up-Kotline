package com.example.level_up.uiscreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.level_up.api.model.Usuario
import com.example.level_up.api.viewModel.PostViewModel
// IMPORTACIÓN AÑADIDA
import com.example.level_up.viewmodels.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    mainViewModel: MainViewModel,
    postViewModel: PostViewModel
) {
    val listaUsuarios by postViewModel.postList.collectAsState()
    val usuarioActivo = listaUsuarios.firstOrNull()
    var mostrarDialogo by remember { mutableStateOf(false) }
    val items = listOf(Screen.Home, Screen.Profile)
    var selectedItem by remember { mutableStateOf(1) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(Color.Black, Color(0xFF800000)), // Negro a Rojo Oscuro
                    radius = 3000f
                )
            )
    ) {
        Scaffold(
            containerColor = Color.Transparent,
            bottomBar = {
                NavigationBar(containerColor = Color.Black.copy(alpha = 0.8f)) {
                    items.forEachIndexed { index, screen ->
                        NavigationBarItem(
                            selected = selectedItem == index,
                            onClick = {
                                selectedItem = index
                                if (screen != Screen.Profile) mainViewModel.navigateTo(screen)
                            },
                            label = { Text(if (screen == Screen.Home) "Inicio" else "Perfil") },
                            icon = {
                                Icon(
                                    imageVector = if (screen == Screen.Home) Icons.Default.Home else Icons.Default.Person,
                                    contentDescription = null
                                )
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Color.Cyan,
                                unselectedIconColor = Color.White,
                                selectedTextColor = Color.Cyan,
                                unselectedTextColor = Color.White,
                                indicatorColor = Color.Cyan.copy(alpha = 0.1f)
                            )
                        )
                    }
                }
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                if (usuarioActivo != null) {
                    Text("Hola ${usuarioActivo.nombre}", style = MaterialTheme.typography.headlineMedium, color = Color.Cyan)
                    Spacer(modifier = Modifier.height(24.dp))

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.Gray.copy(alpha = 0.2f))
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("Datos del Usuario", style = MaterialTheme.typography.titleLarge, color = Color.White, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.height(16.dp))
                            ProfileDataItem("ID:", usuarioActivo.id.toString()?: "N/A")
                            ProfileDataItem("Nombre:", usuarioActivo.nombre)
                            ProfileDataItem("Email:", usuarioActivo.email)
                            ProfileDataItem("Edad:", usuarioActivo.edad.toString())
                            ProfileDataItem("Dirección:", usuarioActivo.direccion)
                        }
                    }

                    Spacer(Modifier.height(24.dp))

                    val neonButtonColors = ButtonDefaults.buttonColors(
                        containerColor = Color.Cyan,
                        contentColor = Color.Black
                    )

                    Button(
                        onClick = { mostrarDialogo = true },
                        colors = neonButtonColors,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Editar Perfil")
                    }
                    Spacer(Modifier.height(8.dp))
                    Button(
                        onClick = {
                            postViewModel.fetchUsuarios()
                            mainViewModel.navigateTo(Screen.Login)
                        },
                        colors = neonButtonColors,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Cerrar Sesión")
                    }
                    Spacer(Modifier.height(8.dp))
                    OutlinedButton(
                        onClick = { navController.navigate(Screen.Home.route) },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Cyan),
                        border = BorderStroke(1.dp, Color.Cyan)
                    ) {
                        Text("Volver al inicio")
                    }
                } else {
                    CircularProgressIndicator(color = Color.Cyan)
                    Text("Cargando usuario...", modifier = Modifier.padding(top = 8.dp), color = Color.White)
                }
            }
        }
    }

    if (mostrarDialogo && usuarioActivo != null) {
        EditUsuarioDialog(
            usuario = usuarioActivo,
            onDismiss = { mostrarDialogo = false },
            onSave = {
                val idParaApi = it.id?.toInt() ?: 0
                postViewModel.updateUsuario(idParaApi, it)
                mostrarDialogo = false
            }
        )
    }
}

@Composable
private fun ProfileDataItem(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            fontWeight = FontWeight.Bold,
            color = Color.Cyan,
            modifier = Modifier.width(100.dp)
        )
        Text(text = value, color = Color.White)
    }
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun EditUsuarioDialog(
    usuario: Usuario,
    onDismiss: () -> Unit,
    onSave: (Usuario) -> Unit
) {
    var nombre by remember { mutableStateOf(usuario.nombre) }
    var email by remember { mutableStateOf(usuario.email) }
    var edad by remember { mutableStateOf(usuario.edad.toString()) }
    var direccion by remember { mutableStateOf(usuario.direccion) }
    var errorEdicion by remember { mutableStateOf("") }

    val textFieldColors = OutlinedTextFieldDefaults.colors(
        focusedTextColor = Color.White,
        unfocusedTextColor = Color.White,
        cursorColor = Color.White,
        focusedBorderColor = Color.Cyan,
        unfocusedBorderColor = Color.Magenta,
        focusedLabelColor = Color.Cyan,
        unfocusedLabelColor = Color.Magenta,
        errorBorderColor = Color.Red,
        errorLabelColor = Color.Red,
        errorSupportingTextColor = Color.Red
    )

    AlertDialog(
        containerColor = Color.Black.copy(alpha = 0.9f),
        onDismissRequest = onDismiss,
        title = { Text("Editar Usuario", color = Color.Cyan) },
        text = {
            Column {
                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre") },
                    colors = textFieldColors
                )
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    colors = textFieldColors
                )
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    value = edad,
                    onValueChange = { edad = it },
                    label = { Text("Edad") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = textFieldColors
                )
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    value = direccion,
                    onValueChange = { direccion = it },
                    label = { Text("Dirección") },
                    colors = textFieldColors
                )

                if (errorEdicion.isNotEmpty()) {
                    Text(
                        text = errorEdicion,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(top = 8.dp),
                        fontSize = 12.sp
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (email.isEmpty() || nombre.isEmpty() || edad.isEmpty() || direccion.isEmpty()) {
                        errorEdicion = "Todos los campos son obligatorios"
                        return@Button
                    }
                    if (nombre.length <= 3) {
                        errorEdicion = "El nombre debe tener más de 3 caracteres"
                        return@Button
                    }
                    if (!email.contains("@")) {
                        errorEdicion = "El email no es válido (falta @)"
                        return@Button
                    }

                    val actualizado = usuario.copy(
                        nombre = nombre,
                        email = email,
                        edad = edad.toIntOrNull() ?: 0,
                        direccion = direccion
                    )
                    onSave(actualizado)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Cyan)
            ) {
                Text("Guardar", color = Color.Black)
            }
        },
        dismissButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Magenta)
            ) { Text("Cancelar", color = Color.White) }
        }
    )
}
