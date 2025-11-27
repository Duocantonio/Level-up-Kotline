package com.example.level_up.uiscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.level_up.R
import com.example.level_up.ui.components.NeonLogo // Importa el nuevo componente
import com.example.level_up.viewmodels.UserViewModel

@Composable
fun RegistroScreen(
    navController: NavController,
    usuarioViewModel: UserViewModel
) {
    val estado by usuarioViewModel.estado.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {

        // 2. Capa oscura para mejorar la legibilidad
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
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState()), // Para evitar que los campos se corten en pantallas pequeñas
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                NeonLogo() // 3. Logo con efecto neón

                Text(
                    text = "Registro",
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.Cyan,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                // Campos de texto con estilo neón
                val textFieldColors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.Green,
                    unfocusedTextColor = Color.Cyan,
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
                    value = estado.nombre,
                    onValueChange = usuarioViewModel::onNombreChange,
                    label = { Text("Nombre") },
                    isError = estado.errores.nombre != null,
                    supportingText = {
                        estado.errores.nombre?.let {
                            Text(it)
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = textFieldColors,
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = estado.clave,
                    onValueChange = usuarioViewModel::onClaveChange,
                    label = { Text("Contraseña") },
                    visualTransformation = PasswordVisualTransformation(),
                    isError = estado.errores.clave != null,
                    supportingText = {
                        estado.errores.clave?.let {
                            Text(it)
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = textFieldColors,
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = estado.correo,
                    onValueChange = usuarioViewModel::onCorreoChange,
                    label = { Text("Correo") },
                    isError = estado.errores.correo != null,
                    supportingText = {
                        estado.errores.correo?.let {
                            Text(it)
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = textFieldColors,
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = estado.direccion,
                    onValueChange = usuarioViewModel::onDireccionChange,
                    label = { Text("Dirección") },
                    isError = estado.errores.direccion != null,
                    supportingText = {
                        estado.errores.direccion?.let {
                            Text(it)
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = textFieldColors,
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(16.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = estado.aceptarTerminos,
                        onCheckedChange = usuarioViewModel::onAceptarTerminos,
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color.Cyan,
                            uncheckedColor = Color.Magenta
                        )
                    )
                    Spacer(Modifier.width(8.dp))
                    Text("Acepto los términos y condiciones", color = Color.White)
                }
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        if (usuarioViewModel.validarFormulario()) {
                            usuarioViewModel.registrarUsuario()
                            navController.navigate("home_page")
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Cyan)
                ) {
                    Text("Registrar", color = Color.Black)
                }
            }
        }
    }
}

// La función NeonLogo() duplicada se ha eliminado de aquí.
