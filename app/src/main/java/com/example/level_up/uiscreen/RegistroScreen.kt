package com.example.level_up.uiscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.level_up.api.model.Usuario
import com.example.level_up.api.viewModel.PostViewModel
import com.example.level_up.ui.components.NeonLogo

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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(Color.Black, Color.Red),
                    radius = 10000f
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            NeonLogo()

            Text(
                text = "Registro",
                style = MaterialTheme.typography.headlineLarge,
                color = Color.Cyan,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            val textFieldColors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.Cyan,
                unfocusedTextColor = Color.Green,
                cursorColor = Color.Cyan,
                focusedBorderColor = Color.Green,
                unfocusedBorderColor = Color.Cyan,
                focusedLabelColor = Color.Green,
                unfocusedLabelColor = Color.Cyan,
                errorBorderColor = Color.Red,
                errorLabelColor = Color.Red,
                errorSupportingTextColor = Color.Red
            )

            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
                isError = nombre.length <= 3 && nombre.isNotEmpty(),
                supportingText = {
                    if (nombre.length <= 3 && nombre.isNotEmpty())
                        Text("Debe tener más de 3 caracteres")
                },
                modifier = Modifier.fillMaxWidth(),
                colors = textFieldColors,
                singleLine = true
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                isError = !email.contains("@") && email.isNotEmpty(),
                supportingText = {
                    if (!email.contains("@") && email.isNotEmpty())
                        Text("El email debe contener @")
                },
                modifier = Modifier.fillMaxWidth(),
                colors = textFieldColors,
                singleLine = true
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = clave1,
                onValueChange = { clave1 = it },
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                colors = textFieldColors,
                singleLine = true
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = clave2,
                onValueChange = { clave2 = it },
                label = { Text("Repetir Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                isError = clave1 != clave2,
                supportingText = {
                    if (clave1 != clave2)
                        Text("Las contraseñas no coinciden")
                },
                modifier = Modifier.fillMaxWidth(),
                colors = textFieldColors,
                singleLine = true
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = edad,
                onValueChange = { edad = it },
                label = { Text("Edad") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                isError = (edad.toIntOrNull() ?: 0) < 18 && edad.isNotEmpty(),
                supportingText = {
                    if ((edad.toIntOrNull() ?: 0) < 18 && edad.isNotEmpty())
                        Text("Debe ser mayor de edad")
                },
                modifier = Modifier.fillMaxWidth(),
                colors = textFieldColors,
                singleLine = true
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = direccion,
                onValueChange = { direccion = it },
                label = { Text("Dirección") },
                isError = direccion.length <= 3 && direccion.isNotEmpty(),
                supportingText = {
                    if (direccion.length <= 3 && direccion.isNotEmpty())
                        Text("La dirección debe tener más de 3 caracteres")
                },
                modifier = Modifier.fillMaxWidth(),
                colors = textFieldColors,
                singleLine = true
            )
            Spacer(modifier = Modifier.height(16.dp))

            if (mensajeError.isNotEmpty()) {
                Text(
                    text = mensajeError,
                    color = MaterialTheme.colorScheme.error
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

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
                    // Limpiar
                    nombre = ""
                    email = ""
                    edad = ""
                    clave1 = ""
                    clave2 = ""
                    direccion = ""

                    // Si quieres navegar a home
                    navController.navigate(Screen.Home.route)
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Cyan)
            ) {
                Text("Registrar", color = Color.Black)
            }
        }
    }
}