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
import com.example.level_up.navigation.Screen
import com.example.level_up.viewmodels.MainViewModel
import com.example.level_up.viewmodels.UsuarioViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    mainViewModel: MainViewModel = viewModel(),
    usuarioViewModel: UsuarioViewModel = viewModel()
) {
    // 🔹 Obtenemos el estado actual del usuario desde el ViewModel
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

            // 🔹 Mostramos datos directamente del ViewModel
            Text("Nombre: ${estado.nombre}")
            Text("Correo: ${estado.correo}")
            Text("Clave: ${estado.clave}")
            Text("Dirección: ${estado.direccion}")
            Text("Términos aceptados: ${estado.aceptarTerminos}")
        }
    }
}
