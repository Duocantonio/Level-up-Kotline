package com.example.level_up.uiscreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.level_up.viewmodels.MainViewModel
import com.example.level_up.viewmodels.UsuarioViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    mainViewModel: MainViewModel = viewModel(),
    usuarioViewModel: UsuarioViewModel = viewModel()
) {
    val estado by usuarioViewModel.estado.collectAsState()

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
                            if (screen != Screen.Profile) {
                                mainViewModel.navigateTo(screen)
                            }
                        },
                        label = {
                            Text(
                                when (screen) {
                                    Screen.Home -> "Inicio"
                                    Screen.Profile -> "Perfil"
                                    else -> screen.route
                                }
                            )
                        },
                        icon = {
                            Icon(
                                imageVector = if (screen == Screen.Home)
                                    Icons.Default.Home
                                else Icons.Default.Person,
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
            Text("Bienvenido al Perfil", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))

            Text("Datos del Usuario", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))

            Text("Nombre: ${estado.nombre}")
            Text("Correo: ${estado.correo}")
            Text("Clave: ${"*".repeat(estado.clave.length)}")
            Text("Dirección: ${estado.direccion}")
            Text("Términos aceptados: ${estado.aceptarTerminos}")

            Spacer(Modifier.height(20.dp))

            Button(onClick = { usuarioViewModel.cambiarMostrarDialogo(true) }) {
                Text("Editar Perfil")
            }
        }
    }

    if (usuarioViewModel.mostrarDialogo) {
        var nombre by remember { mutableStateOf(estado.nombre) }
        var correo by remember { mutableStateOf(estado.correo) }
        var clave by remember { mutableStateOf(estado.clave) }
        var direccion by remember { mutableStateOf(estado.direccion) }

        AlertDialog(
            onDismissRequest = { usuarioViewModel.cambiarMostrarDialogo(false) },
            confirmButton = {
                TextButton(onClick = {
                    usuarioViewModel.onNombreChange(nombre)
                    usuarioViewModel.onCorreoChange(correo)
                    usuarioViewModel.onClaveChange(clave)
                    usuarioViewModel.onDireccionChange(direccion)

                    if (usuarioViewModel.validarFormulario()) {
                        usuarioViewModel.cambiarMostrarDialogo(false)
                    }
                }) {
                    Text("Guardar")
                }
            },
            dismissButton = {
                TextButton(onClick = { usuarioViewModel.cambiarMostrarDialogo(false) }) {
                    Text("Cancelar")
                }
            },
            title = { Text("Editar perfil") },
            text = {
                Column {
                    TextField(
                        value = nombre,
                        onValueChange = { nombre = it },
                        label = { Text("Nombre") },
                        isError = estado.errores.nombre != null
                    )
                    estado.errores.nombre?.let { Text(it, color = MaterialTheme.colorScheme.error) }

                    Spacer(Modifier.height(8.dp))

                    TextField(
                        value = correo,
                        onValueChange = { correo = it },
                        label = { Text("Correo") },
                        isError = estado.errores.correo != null
                    )
                    estado.errores.correo?.let { Text(it, color = MaterialTheme.colorScheme.error) }

                    Spacer(Modifier.height(8.dp))

                    TextField(
                        value = clave,
                        onValueChange = { clave = it },
                        label = { Text("Clave") },
                        isError = estado.errores.clave != null
                    )
                    estado.errores.clave?.let { Text(it, color = MaterialTheme.colorScheme.error) }

                    Spacer(Modifier.height(8.dp))

                    TextField(
                        value = direccion,
                        onValueChange = { direccion = it },
                        label = { Text("Dirección") },
                        isError = estado.errores.direccion != null
                    )
                    estado.errores.direccion?.let { Text(it, color = MaterialTheme.colorScheme.error) }
                }
            }
        )
    }
}
