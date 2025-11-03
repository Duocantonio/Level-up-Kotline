package com.example.level_up.uiscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.level_up.api.model.Usuario
import com.example.level_up.api.viewModel.PostViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostScreen(viewModel: PostViewModel) {

    val usuarios = viewModel.postList.collectAsState().value
    var usuarioEnEdicion by remember { mutableStateOf<Usuario?>(null) }
    var mensajeError by remember { mutableStateOf("") }

    var nombre by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var clave1 by remember { mutableStateOf("") }
    var clave2 by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Listado de usuarios") })
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) { TextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") }, modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(8.dp))

            TextField(value = email, onValueChange = { email = it }, label = { Text("Email") }, modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(8.dp))

            TextField(
                value = clave1,
                onValueChange = { clave1 = it },
                label = { Text("Clave1") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))

            TextField(
                value = clave2,
                onValueChange = { clave2 = it },
                label = { Text("Clave2") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))

            TextField(
                value = edad,
                onValueChange = { edad = it },
                label = { Text("Edad") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), // Teclado numérico
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))

            TextField(value = direccion, onValueChange = { direccion = it }, label = { Text("Dirección") }, modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(12.dp))

            if (mensajeError.isNotEmpty()) {
                Text(
                    text = mensajeError,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            Button(
                onClick = {
                    if (nombre.isEmpty() || email.isEmpty() || edad.isEmpty() || direccion.isEmpty() || clave1.isEmpty() || clave2.isEmpty()) {
                        mensajeError = "Todos los campos son obligatorios"
                        return@Button
                    }

                    if (nombre.length <= 3) {
                        mensajeError = "El nombre debe tener más de 3 caracteres"
                        return@Button
                    }
                    if (direccion.length <= 3) {
                        mensajeError = "La dirección debe tener más de 3 caracteres"
                        return@Button
                    }

                    if (!email.contains("@")) {
                        mensajeError = "El email no es válido (falta @)"
                        return@Button
                    }

                    val edadVerificacon= edad.toIntOrNull()?:0

                    if (edadVerificacon<18){
                        mensajeError = "El usuario debe ser mayor de edad"
                        return@Button
                    }

                    if (clave1 != clave2) {
                        mensajeError = "Las claves no coinciden"
                        return@Button
                    }

                    mensajeError = ""
                    viewModel.createUsuario(
                        Usuario(
                            nombre = nombre,
                            email = email,
                            edad = edad.toIntOrNull() ?: 0,
                            clave1 = clave1,
                            clave2 = clave2,
                            direccion = direccion
                        )
                    )
                    // Limpiar campos tras éxito
                    nombre = ""; email = ""; edad = ""; clave1 = ""; clave2 = ""; direccion = ""
                },
                modifier = Modifier.fillMaxWidth()
            ) { Text("Crear Usuario") }

            Spacer(Modifier.height(16.dp))

            LazyColumn {
                items(usuarios) { usuario ->
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {

                            Text("Nombre: ${usuario.nombre}")
                            Text("Email: ${usuario.email}")
                            Text("Edad: ${usuario.edad}")
                            Text("Dirección: ${usuario.direccion}")

                            Spacer(Modifier.height(8.dp))

                            Button(
                                onClick = { usuarioEnEdicion = usuario },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("Editar")
                            }
                        }
                    }
                }
            }
        }
    }

    if (usuarioEnEdicion != null) {
        EditUsuarioDialog(
            usuario = usuarioEnEdicion!!,
            onDismiss = { usuarioEnEdicion = null },
            onSave = { usuarioActualizado ->
                usuarioActualizado.id?.let { viewModel.updateUsuario(it.toInt(), usuarioActualizado) }
                usuarioEnEdicion = null
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
                TextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") })
                TextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
                TextField(
                    value = edad,
                    onValueChange = { edad = it },
                    label = { Text("Edad") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                TextField(value = direccion, onValueChange = { direccion = it }, label = { Text("Dirección") })

                if (errorEdicion.isNotEmpty()) {
                    Text(text = errorEdicion, color = Color.Red, modifier = Modifier.padding(top = 8.dp))
                }
            }
        },
        confirmButton = {
            Button(onClick = {
                if (email.isEmpty()|| nombre.isEmpty() || edad.isEmpty() || direccion.isEmpty() ) {
                    errorEdicion = "Todos los campos son obligatorios"
                    return@Button
                }
                if (nombre.length <= 3) {
                    errorEdicion = "El nombre debe tener más de 3 caracteres"
                    return@Button
                }
                if(!email.contains("@")){
                    errorEdicion="El email no es válido (falta @)"
                    return@Button
                }
                val actualizado = usuario.copy(
                    nombre = nombre,
                    email = email,
                    edad = edad.toIntOrNull() ?: 0,
                    direccion = direccion
                )
                onSave(actualizado)
            }) { Text("Guardar") }
        },
        dismissButton = {
            Button(onClick = onDismiss) { Text("Cancelar") }
        }
    )
}