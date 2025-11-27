package com.example.level_up.uiscreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.level_up.api.model.Usuario
import com.example.level_up.api.viewModel.PostViewModel
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

    Scaffold(
        bottomBar = {
            NavigationBar {
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
                        }
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

            Text("Hola ${usuarioActivo?.nombre} Este es tu perfil", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))

            if (usuarioActivo != null) {

                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {

                        Text(
                            "Datos del Usuario (ID: ${usuarioActivo.id})",
                            style = MaterialTheme.typography.titleMedium
                        )

                        Divider(modifier = Modifier.padding(vertical = 8.dp))

                        Text("Nombre: ${usuarioActivo.nombre}")
                        Text("Email: ${usuarioActivo.email}")
                        Text("Edad: ${usuarioActivo.edad}")
                        Text("Dirección: ${usuarioActivo.direccion}")
                        Text("Clave: ********")
                    }
                }

                Spacer(Modifier.height(20.dp))

                Button(onClick = { mostrarDialogo = true }) {
                    Text("Editar Perfil")
                }

            } else {
                CircularProgressIndicator()
                Text("Cargando usuario...", modifier = Modifier.padding(top = 8.dp))
            }

            Button(onClick = {
                postViewModel.fetchUsuarios()
                mainViewModel.navigateTo(Screen.Login)
            }
            ) {
                Text("Cerrar Sesión")
            }

            Spacer(Modifier.height(16.dp))

            OutlinedButton(onClick = { navController.navigate(Screen.Home.route) }) {
                Text("Volver al inicio")
            }
        }
    }

    if (mostrarDialogo && usuarioActivo != null) {
        EditUsuarioDialog(
            usuario = usuarioActivo,
            onDismiss = { },
            onSave = { usuarioEditado ->
                val idParaApi = usuarioEditado.id?.toInt() ?: 0
                postViewModel.updateUsuario(idParaApi, usuarioEditado)
            }
        )
    }
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

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Editar Usuario") },
        text = {
            Column {
                TextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre") }
                )
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") }
                )
                TextField(
                    value = edad,
                    onValueChange = { edad = it },
                    label = { Text("Edad") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                TextField(
                    value = direccion,
                    onValueChange = { direccion = it },
                    label = { Text("Dirección") }
                )

                if (errorEdicion.isNotEmpty()) {
                    Text(
                        text = errorEdicion,
                        color = Color.Red,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        },
        confirmButton = {
            Button(onClick = {
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
            }) {
                Text("Guardar")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) { Text("Cancelar") }
        }
    )
}
