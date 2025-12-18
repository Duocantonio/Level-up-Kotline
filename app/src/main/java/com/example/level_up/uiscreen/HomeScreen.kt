package com.example.level_up.uiscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.level_up.R
import com.example.level_up.ui.components.NeonLogo
import com.example.level_up.viewmodels.MainViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: MainViewModel = viewModel()
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.background(Color.Black.copy(alpha = 0.8f))
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Text("Menú", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(16.dp), color = Color.Cyan)
                Divider(modifier = Modifier.padding(horizontal = 16.dp), color = Color.Cyan.copy(alpha = 0.5f))

                val navDrawerColors = NavigationDrawerItemDefaults.colors(
                    selectedIconColor = Color.Cyan,
                    unselectedIconColor = Color.White,
                    selectedTextColor = Color.Cyan,
                    unselectedTextColor = Color.White,
                    unselectedBadgeColor = Color.Magenta
                )

                // Enlaces principales
                NavigationDrawerItem(
                    label = { Text("Perfil") },
                    selected = false,
                    onClick = { scope.launch { drawerState.close() }; navController.navigate(Screen.Profile.route) },
                    icon = { Icon(Icons.Default.AccountCircle, contentDescription = "Perfil") },
                    colors = navDrawerColors
                )
                NavigationDrawerItem(
                    label = { Text("Activar/Desactivar") },
                    selected = false,
                    onClick = { scope.launch { drawerState.close() }; navController.navigate(Screen.Main.route) },
                    icon = { Icon(Icons.Default.Check, contentDescription = "Activar/Desactivar") },
                    colors = navDrawerColors
                )

                Divider(modifier = Modifier.padding(16.dp), color = Color.Cyan.copy(alpha = 0.5f))
                Text("Categorías", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(horizontal = 16.dp), color = Color.Cyan)

                // Enlaces de categorías
                NavigationDrawerItem(
                    label = { Text("Juegos de Mesa") },
                    selected = false,
                    onClick = { scope.launch { drawerState.close() }; navController.navigate(Screen.JuegosDeMesa.route) },
                    icon = { Icon(Icons.Default.Casino, contentDescription = "Juegos de Mesa") },
                    colors = navDrawerColors
                )
                NavigationDrawerItem(
                    label = { Text("Periféricos") },
                    selected = false,
                    onClick = { scope.launch { drawerState.close() }; navController.navigate(Screen.Perifericos.route) },
                    icon = { Icon(Icons.Default.Mouse, contentDescription = "Periféricos") },
                    colors = navDrawerColors
                )
                NavigationDrawerItem(
                    label = { Text("Computadores") },
                    selected = false,
                    onClick = { scope.launch { drawerState.close() }; navController.navigate(Screen.Computadores.route) },
                    icon = { Icon(Icons.Default.Laptop, contentDescription = "Computadores") },
                    colors = navDrawerColors
                )
                NavigationDrawerItem(
                    label = { Text("Consolas") },
                    selected = false,
                    onClick = { scope.launch { drawerState.close() }; navController.navigate(Screen.Consolas.route) },
                    icon = { Icon(Icons.Default.VideogameAsset, contentDescription = "Consolas") },
                    colors = navDrawerColors
                )
            }
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // 1. Imagen de fondo
            Image(
                painter = painterResource(id = R.drawable.fondo_pagina),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            // 2. Capa oscura para mejorar la legibilidad
            Box(modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.6f)))

            // 3. Contenido de la pantalla
            Scaffold(
                containerColor = Color.Transparent, // Fondo transparente para mostrar la imagen de fondo
                topBar = {
                    TopAppBar(
                        title = { Text("Página de Inicio", color = Color.Cyan) },
                        navigationIcon = {
                            IconButton(onClick = { scope.launch { drawerState.open() } }) {
                                Icon(Icons.Default.Menu, contentDescription = "Abrir menú", tint = Color.Cyan)
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color.Transparent // Barra superior transparente
                        )
                    )
                }
            ) { innerPadding ->
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                        .padding(horizontal = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
                ) {
                    NeonLogo() // Logo con efecto neón

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "¡Bienvenido a Level-Up!",
                        style = MaterialTheme.typography.headlineMedium,
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )

                    Text(
                        text = "Usa el menú o los botones para navegar por la aplicación.",
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    val buttonModifier = Modifier.fillMaxWidth().height(50.dp)
                    val neonButtonColors = ButtonDefaults.buttonColors(
                        containerColor = Color.Cyan,
                        contentColor = Color.Black
                    )

                    // Botones con el nuevo estilo neón
                    Button(onClick = { navController.navigate(Screen.Cart.route) }, modifier = buttonModifier, colors = neonButtonColors) {
                        Text("Ir al Carrito")
                    }
                    Button(onClick = { navController.navigate(Screen.Setitings.route) }, modifier = buttonModifier, colors = neonButtonColors) {
                        Text("Ir a Configuración")
                    }
                    Button(onClick = { navController.navigate(Screen.Register.route) }, modifier = buttonModifier, colors = neonButtonColors) {
                        Text("Ir al Registro")
                    }
                    Button(onClick = { navController.navigate(Screen.Camera.route) }, modifier = buttonModifier, colors = neonButtonColors) {
                        Text("Ir a Cámara")
                    }

                }
            }
        }
    }
}
