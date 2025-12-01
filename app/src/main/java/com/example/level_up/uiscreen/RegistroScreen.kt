package com.example.level_up.uiscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.level_up.api.model.Usuario
import com.example.level_up.api.viewModel.PostViewModel

@Composable
fun RegistroScreen(
    navController: NavController,
    viewModel: PostViewModel
) {

    val usuarioCreado = viewModel.createUsuario.collectAsState()

    var nombre by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var clave1 by remember { mutableStateOf("") }
    var clave2 by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }

    var mensajeError by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            isError = nombre.length <= 3,
            supportingText = {
                if (nombre.length <= 3)
                    Text("Debe tener más de 3 caracteres", color = MaterialTheme.colorScheme.error)
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            isError = !email.contains("@"),
            supportingText = {
                if (!email.contains("@"))
                    Text("El email debe contener @", color = MaterialTheme.colorScheme.error)
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = clave1,
            onValueChange = { clave1 = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = clave2,
            onValueChange = { clave2 = it },
            label = { Text("Repetir Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            isError = clave1 != clave2,
            supportingText = {
                if (clave1 != clave2)
                    Text("Las contraseñas no coinciden", color = MaterialTheme.colorScheme.error)
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = edad,
            onValueChange = { edad = it },
            label = { Text("Edad") },
            isError = (edad.toIntOrNull() ?: 0) < 18,
            supportingText = {
                if ((edad.toIntOrNull() ?: 0) < 18)
                    Text("Debe ser mayor de edad", color = MaterialTheme.colorScheme.error)
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = direccion,
            onValueChange = { direccion = it },
            label = { Text("Dirección") },
            isError = direccion.length <= 3,
            supportingText = {
                if (direccion.length <= 3)
                    Text("La dirección debe tener más de 3 caracteres", color = MaterialTheme.colorScheme.error)
            },
            modifier = Modifier.fillMaxWidth()
        )

        if (mensajeError.isNotEmpty()) {
            Text(
                text = mensajeError,
                color = MaterialTheme.colorScheme.error
            )
        }

        Button(
            onClick = {

                if (nombre.isEmpty() || email.isEmpty() || edad.isEmpty() ||
                    direccion.isEmpty() || clave1.isEmpty() || clave2.isEmpty()
                ) {
                    mensajeError = "Todos los campos son obligatorios"
                    return@Button
                }

                if (clave1 != clave2) {
                    mensajeError = "Las contraseñas no coinciden"
                    return@Button
                }

                if ((edad.toIntOrNull() ?: 0) < 18) {
                    mensajeError = "Debes ser mayor de edad"
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

                nombre = ""
                email = ""
                edad = ""
                clave1 = ""
                clave2 = ""
                direccion = ""

                navController.navigate("home_page")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrar")
        }
    }
}
