package com.example.level_up.uiscreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.level_up.data.local.User
import com.example.level_up.viewmodel.UserViewModel
import kotlinx.coroutines.launch

@Composable
fun UserScreen(viewModel: UserViewModel) {
    val users by viewModel.users.collectAsState(initial = emptyList())
    val scope = rememberCoroutineScope()

    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var clave by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }

    var showEditDialog by remember { mutableStateOf(false) }
    var userToEdit by remember { mutableStateOf<User?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Gestión de Usuarios", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(16.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") })
            TextField(value = correo, onValueChange = { correo = it }, label = { Text("Correo") })
            TextField(value = clave, onValueChange = { clave = it }, label = { Text("Clave") })
            TextField(value = direccion, onValueChange = { direccion = it }, label = { Text("Dirección") })

            Button(
                onClick = {
                    if (nombre.isNotBlank() && correo.isNotBlank() && clave.isNotBlank()) {
                        viewModel.addUser(nombre, correo, clave, direccion)
                        nombre = ""
                        correo = ""
                        clave = ""
                        direccion = ""
                    }
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Agregar usuario")
            }
        }

        Spacer(Modifier.height(24.dp))


        Spacer(Modifier.height(12.dp))

        if (users.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("No hay usuarios registrados.")
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(users) { user ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(12.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text("${user.nombre}, style = MaterialTheme.typography.titleMedium")
                                Text("${user.correo}")
                                Text("${user.clave}")
                                Text("${user.direccion}")
                            }

                            Row {
                                IconButton(onClick = {
                                    userToEdit = user
                                    showEditDialog = true
                                }) {
                                    Icon(Icons.Default.Edit, contentDescription = "Editar usuario")
                                }
                                IconButton(onClick = {
                                    scope.launch { viewModel.deleteUser(user) }
                                }) {
                                    Icon(Icons.Default.Delete, contentDescription = "Eliminar usuario")
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    if (showEditDialog && userToEdit != null) {
        EditUserDialog(
            user = userToEdit!!,
            onDismiss = { showEditDialog = false },
            onConfirm = { nuevoNombre, nuevoCorreo, nuevaClave, nuevaDireccion ->
                viewModel.updateUser(userToEdit!!, nuevoNombre, nuevoCorreo, nuevaClave, nuevaDireccion)
                showEditDialog = false
            }
        )
    }

}


@Composable
fun EditUserDialog(
    user: User,
    onDismiss: () -> Unit,
    onConfirm: (String, String, String, String) -> Unit
) {
    var nombre by remember { mutableStateOf(user.nombre) }
    var correo by remember { mutableStateOf(user.correo) }
    var clave by remember { mutableStateOf(user.clave) }
    var direccion by remember { mutableStateOf(user.direccion) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Editar usuario") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                TextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") })
                TextField(value = correo, onValueChange = { correo = it }, label = { Text("Correo") })
                TextField(value = clave, onValueChange = { clave = it }, label = { Text("Clave") })
                TextField(value = direccion, onValueChange = { direccion = it }, label = { Text("Dirección") })
            }
        },
        confirmButton = {
            TextButton(onClick = { onConfirm(nombre, correo, clave, direccion) }) {
                Text("Guardar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}

