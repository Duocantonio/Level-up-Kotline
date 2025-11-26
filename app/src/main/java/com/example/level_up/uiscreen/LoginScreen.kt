package com.example.level_up.uiscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.level_up.R
import com.example.level_up.viewmodels.LoginViewModel

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel,
    onLoginSuccess: () -> Unit,
    onRegisterClick: () -> Unit
) {
    val uiState by loginViewModel.uiState.collectAsState()

    // Usamos un Box para superponer el contenido sobre el fondo con degradado.
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(Color.Black, Color.Red),
                    radius = 10000f // Un radio grande para expandir el centro negro
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Logo de la aplicación con efecto neón.
            NeonLogo()
            
            Spacer(modifier = Modifier.height(10.dp))

            // Título de la pantalla.
            Text("Iniciar Sesión",
                style = MaterialTheme.typography.headlineLarge,
                color = Color.Cyan)
            Spacer(modifier = Modifier.height(32.dp))

            // Campo de texto para el correo.
            OutlinedTextField(
                value = uiState.correo,
                onValueChange = { loginViewModel.onCorreoChange(it) },
                label = { Text("Correo Electrónico") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.Green,
                    unfocusedTextColor = Color.Cyan,
                    cursorColor = Color.White,
                    focusedBorderColor = Color.Cyan,
                    unfocusedBorderColor = Color.Magenta,
                    focusedLabelColor = Color.Cyan,
                    unfocusedLabelColor = Color.Magenta,
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Campo de texto para la contraseña con icono de visibilidad.
            var passwordVisible by remember { mutableStateOf(false) }
            OutlinedTextField(
                value = uiState.clave,
                onValueChange = { loginViewModel.onClaveChange(it) },
                label = { Text("Contraseña") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, contentDescription = null, tint = Color.Gray)
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.Green,
                    unfocusedTextColor = Color.Cyan,
                    cursorColor = Color.White,
                    focusedBorderColor = Color.Cyan,
                    unfocusedBorderColor = Color.Magenta,
                    focusedLabelColor = Color.Cyan,
                    unfocusedLabelColor = Color.Magenta,
                )
            )

            // Muestra un mensaje de error si existe.
            uiState.error?.let {
                Spacer(modifier = Modifier.height(16.dp))
                Text(it, color = MaterialTheme.colorScheme.error)
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Botón para iniciar sesión.
            Button(
                onClick = { loginViewModel.login(onLoginSuccess) },
                modifier = Modifier.fillMaxWidth(),
                enabled = uiState.correo.isNotBlank() && uiState.clave.isNotBlank()
            ) {
                Text("Entrar")
            }

            // Botón para navegar a la pantalla de registro.
            TextButton(onClick = onRegisterClick) {
                Text("¿No tienes cuenta? Regístrate", color = Color.Cyan)
            }
        }
    }
}

@Composable
fun NeonLogo() {
    val logoSize = 300.dp
    val glowOuterSize = 400.dp

    val glowRadius = glowOuterSize.value / 2

    Box(
        modifier = Modifier
            .size(glowOuterSize)
            .background(Color.Transparent), // Cambio aquí: de Color.Black a Color.Transparent
        contentAlignment = Alignment.Center
    ) {
        // CAPA DEL RESPLANDOR
        Box(
            modifier = Modifier
                .size(glowOuterSize)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Color.Cyan.copy(alpha = 0.8f),
                            Color.Cyan.copy(alpha = 0.4f),
                            Color.Transparent
                        ),
                        radius = glowRadius
                    )
                )
        )

        // CAPA DE LA IMAGEN
        Image(
            // **IMPORTANTE**: Asegúrate de que R.drawable.logo exista
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo de Level-Up",
            modifier = Modifier.size(logoSize)
        )
    }
}
