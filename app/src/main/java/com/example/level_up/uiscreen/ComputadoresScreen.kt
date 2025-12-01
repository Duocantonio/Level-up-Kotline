package com.example.level_up.uiscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Casino
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Laptop
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Mouse
import androidx.compose.material.icons.filled.VideogameAsset
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment // Importación necesaria para Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
// Importación necesaria para Modifier.paint
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.level_up.R
import com.example.level_up.ui.theme.LevelUpTheme
import com.example.level_up.viewmodels.CarritoViewModel
import com.example.level_up.viewmodels.Producto
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComputadoresScreen(navController: NavController, modifier: Modifier = Modifier, carritoViewModel: CarritoViewModel) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // **NOTA IMPORTANTE:**
    // Asegúrate de que tienes un recurso de imagen llamado 'bg_texture' en tu carpeta res/drawable.
    // Si la imagen se llama diferente, ajusta el ID aquí.
    val backgroundPainter = painterResource(id = R.drawable.fondo_pagina)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(modifier = Modifier.height(16.dp))
                Text("Menú", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(16.dp))
                Divider(modifier = Modifier.padding(horizontal = 16.dp))

                NavigationDrawerItem(
                    label = { Text("Perfil") },
                    selected = false,
                    onClick = { scope.launch { drawerState.close() }; navController.navigate("profile") },
                    icon = { Icon(Icons.Default.AccountCircle, contentDescription = "Perfil") }
                )
                NavigationDrawerItem(
                    label = { Text("Activar/Desactivar") },
                    selected = false,
                    onClick = { scope.launch { drawerState.close() }; navController.navigate("main") },
                    icon = { Icon(Icons.Default.Check, contentDescription = "Activar/Desactivar") }
                )

                Divider(modifier = Modifier.padding(16.dp))
                Text("Categorías", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(horizontal = 16.dp))

                // Enlaces de categorías
                NavigationDrawerItem(
                    label = { Text("Juegos de Mesa") },
                    selected = false,
                    onClick = { scope.launch { drawerState.close() }; navController.navigate("juegos_de_mesa") },
                    icon = { Icon(Icons.Default.Casino, contentDescription = "Juegos de Mesa") }
                )
                NavigationDrawerItem(
                    label = { Text("Periféricos") },
                    selected = false,
                    onClick = { scope.launch { drawerState.close() }; navController.navigate("perifericos") },
                    icon = { Icon(Icons.Default.Mouse, contentDescription = "Periféricos") }
                )
                NavigationDrawerItem(
                    label = { Text("Computadores") },
                    selected = true,
                    onClick = { scope.launch { drawerState.close() }; navController.navigate("computadores") },
                    icon = { Icon(Icons.Default.Laptop, contentDescription = "Computadores") }
                )
                NavigationDrawerItem(
                    label = { Text("Consolas") },
                    selected = false,
                    onClick = { scope.launch { drawerState.close() }; navController.navigate("consolas") },
                    icon = { Icon(Icons.Default.VideogameAsset, contentDescription = "Consolas") }
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Computadores") },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Abrir menú")
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                )
            }
        ) { innerPadding ->
            // --- INICIO DE CAMBIOS VISUALES ---
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    // 1. Aplicamos el color de fondo del tema
                    .background(MaterialTheme.colorScheme.background)
                    // 2. Aplicamos la imagen de fondo con .paint()
                    .paint(
                        painter = backgroundPainter,
                        contentScale = ContentScale.Crop, // Escala para cubrir todo el LazyColumn
                        alignment = Alignment.Center,
                        alpha = 0.4f // Reducir la opacidad
                    )
                    .padding(innerPadding),
                // --- FIN DE CAMBIOS VISUALES ---
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // ... (El resto de tus items de Card permanecen sin cambios)
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column {
                            Image(
                                painter = painterResource(id = R.drawable.pc_gamer_1),
                                contentDescription = "Torre Gamer 1",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(1f).clip(RoundedCornerShape(8.dp)),
                                contentScale = ContentScale.Crop
                            )

                            Column(
                                modifier = Modifier.padding(16.dp),
                            ) {
                                Text(
                                    text = "Computador Gamer Basico",
                                    style = MaterialTheme.typography.headlineSmall,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Un computador ideal para iniciarte en el mundo del gaming, con componentes de calidad y precio accesible.",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Spacer(modifier = Modifier.height(16.dp))

                                Text(
                                    text = "$599.900",
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.align(Alignment.End)
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                Button(
                                    onClick = { val torre1  = Producto(
                                        id = 1,
                                        nombre = "Pc gamer 1",
                                        precio = 599900,
                                        urlImagen = "pc_gamer_1"
                                    )
                                        carritoViewModel.agregarAlCarrito(torre1) },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(50.dp)
                                ) {
                                    Text("Añadir al Carrito")
                                }
                            }
                        }
                    }
                }

                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column {
                            Image(
                                painter = painterResource(id = R.drawable.pc_gamer_2),
                                contentDescription = "Torre Gamer 2",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(1f).clip(RoundedCornerShape(8.dp)),
                                contentScale = ContentScale.Crop
                            )

                            Column(
                                modifier = Modifier.padding(16.dp),
                            ) {
                                Text(
                                    text = "Computador Gamer Avanzado",
                                    style = MaterialTheme.typography.headlineSmall,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Un computador potente para juegos AAA, con una tarjeta gráfica de alta gama y un procesador rápido.",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Spacer(modifier = Modifier.height(16.dp))

                                Text(
                                    text = "$1.299.900",
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.align(Alignment.End)
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                Button(
                                    onClick = { val torre2  = Producto(
                                        id = 2,
                                        nombre = "Pc gamer 2",
                                        precio = 1299900,
                                        urlImagen = "pc_gamer_2"
                                    )
                                        carritoViewModel.agregarAlCarrito(torre2) },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(50.dp)
                                ) {
                                    Text("Añadir al Carrito")
                                }
                            }
                        }
                    }
                }

                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column {
                            Image(
                                painter = painterResource(id = R.drawable.laptop_gamer),
                                contentDescription = "Laptop Gamer",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(1f).clip(RoundedCornerShape(8.dp)),
                                contentScale = ContentScale.Crop
                            )

                            Column(
                                modifier = Modifier.padding(16.dp),
                            ) {
                                Text(
                                    text = "Laptop Gamer",
                                    style = MaterialTheme.typography.headlineSmall,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Una laptop diseñada para gamers, con una pantalla de alta frecuencia de actualización y un diseño portátil.",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Spacer(modifier = Modifier.height(16.dp))

                                Text(
                                    text = "$999.900",
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.align(Alignment.End)
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                Button(
                                    onClick = { val laptop  = Producto(
                                        id = 3,
                                        nombre = "laptop",
                                        precio = 999900,
                                        urlImagen = "laptop_gamer"
                                    )
                                        carritoViewModel.agregarAlCarrito(laptop) },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(50.dp)
                                ) {
                                    Text("Añadir al Carrito")
                                }
                            }
                        }
                    }
                }

                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column {
                            Image(
                                painter = painterResource(id = R.drawable.pc_streaming),
                                contentDescription = "PC Streaming",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(1f).clip(RoundedCornerShape(8.dp)),
                                contentScale = ContentScale.Crop
                            )

                            Column(
                                modifier = Modifier.padding(16.dp),
                            ) {
                                Text(
                                    text = "PC Streaming",
                                    style = MaterialTheme.typography.headlineSmall,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Un computador optimizado para streaming, con capacidad para manejar múltiples tareas simultáneamente.",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Spacer(modifier = Modifier.height(16.dp))

                                Text(
                                    text = "$1.499.900",
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.align(Alignment.End)
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                Button(
                                    onClick = { val torre3  = Producto(
                                        id = 4,
                                        nombre = "Pc gamer 2",
                                        precio = 1499900,
                                        urlImagen = "pc_streaming"
                                    )
                                        carritoViewModel.agregarAlCarrito(torre3) },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(50.dp)
                                ) {
                                    Text("Añadir al Carrito")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}