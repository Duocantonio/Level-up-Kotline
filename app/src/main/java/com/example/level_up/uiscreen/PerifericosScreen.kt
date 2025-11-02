package com.example.level_up.uiscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.level_up.R
import com.example.level_up.ui.theme.LevelUpTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerifericosScreen(navController: NavController, modifier: Modifier = Modifier) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(modifier = Modifier.height(16.dp))
                Text("Menú", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(16.dp))
                Divider(modifier = Modifier.padding(horizontal = 16.dp))

                // Enlaces principales
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
                    selected = true,
                    onClick = { scope.launch { drawerState.close() }; navController.navigate("perifericos") },
                    icon = { Icon(Icons.Default.Mouse, contentDescription = "Periféricos") }
                )
                NavigationDrawerItem(
                    label = { Text("Computadores") },
                    selected = false,
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
                    title = { Text("Periféricos") },
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
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .background(Color(0xFFE0F7FA))
                    .padding(innerPadding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column {
                            Image(
                                painter = painterResource(id = R.drawable.raton_logi),
                                contentDescription = "Raton Logitec",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(1f).clip(RoundedCornerShape(8.dp)),
                                contentScale = ContentScale.Crop
                            )

                            Column(
                                modifier = Modifier.padding(16.dp),
                            ) {
                                Text(
                                    text = "Raton Logitec G502",
                                    style = MaterialTheme.typography.headlineSmall,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Un ratón ergonómico con alta precisión y múltiples botones programables para una experiencia de juego personalizada.",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Spacer(modifier = Modifier.height(16.dp))

                                Text(
                                    text = "$59.900",
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.align(Alignment.End)
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                Button(
                                    onClick = { /* TODO: Lógica para añadir al carrito */ },
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
                                painter = painterResource(id = R.drawable.teclado_kurama),
                                contentDescription = "Teclado Kurama Redragon",
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
                                    text = "Un teclado mecánico con retroiluminación RGB, teclas duraderas y respuesta táctil para mejorar tu rendimiento en juegos.",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Spacer(modifier = Modifier.height(16.dp))

                                Text(
                                    text = "$79.900",
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.align(Alignment.End)
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                Button(
                                    onClick = { /* TODO: Lógica para añadir al carrito */ },
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
                                painter = painterResource(id = R.drawable.razer_kraken),
                                contentDescription = "Auriculares Razer Kraken",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(1f).clip(RoundedCornerShape(8.dp)),
                                contentScale = ContentScale.Crop
                            )

                            Column(
                                modifier = Modifier.padding(16.dp),
                            ) {
                                Text(
                                    text = "Auriculares Razer Kraken",
                                    style = MaterialTheme.typography.headlineSmall,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Auriculares con sonido envolvente, micrófono ajustable y comodidad para largas sesiones de juego.",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Spacer(modifier = Modifier.height(16.dp))

                                Text(
                                    text = "$89.900",
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.align(Alignment.End)
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                Button(
                                    onClick = { /* TODO: Lógica para añadir al carrito */ },
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
                                painter = painterResource(id = R.drawable.lg_4k_180hz),
                                contentDescription = "Monitor LG 4K",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(1f).clip(RoundedCornerShape(8.dp)),
                                contentScale = ContentScale.Crop
                            )

                            Column(
                                modifier = Modifier.padding(16.dp),
                            ) {
                                Text(
                                    text = "Monitor LG 4K",
                                    style = MaterialTheme.typography.headlineSmall,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Un monitor de alta resolución con una frecuencia de actualización de 180Hz para una experiencia visual fluida y nítida.",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Spacer(modifier = Modifier.height(16.dp))

                                Text(
                                    text = "$399.900",
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.align(Alignment.End)
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                Button(
                                    onClick = { /* TODO: Lógica para añadir al carrito */ },
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
                                painter = painterResource(id = R.drawable.webcam_hd),
                                contentDescription = "Webcam HD",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(1f).clip(RoundedCornerShape(8.dp)),
                                contentScale = ContentScale.Crop
                            )

                            Column(
                                modifier = Modifier.padding(16.dp),
                            ) {
                                Text(
                                    text = "Webcam HD",
                                    style = MaterialTheme.typography.headlineSmall,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Una webcam de alta definición ideal para streaming y videollamadas con calidad clara y nítida.",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Spacer(modifier = Modifier.height(16.dp))

                                Text(
                                    text = "$49.900",
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.align(Alignment.End)
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                Button(
                                    onClick = { /* TODO: Lógica para añadir al carrito */ },
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
                                painter = painterResource(id = R.drawable.alfombrilla_razer_xxl),
                                contentDescription = "Alfombrilla Razer XXL",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(1f).clip(RoundedCornerShape(8.dp)),
                                contentScale = ContentScale.Crop
                            )

                            Column(
                                modifier = Modifier.padding(16.dp),
                            ) {
                                Text(
                                    text = "Alfombrilla Razer XXL",
                                    style = MaterialTheme.typography.headlineSmall,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Una alfombrilla de gran tamaño con superficie suave y base antideslizante para mejorar la precisión del ratón.",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Spacer(modifier = Modifier.height(16.dp))

                                Text(
                                    text = "$29.900",
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.align(Alignment.End)
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                Button(
                                    onClick = { /* TODO: Lógica para añadir al carrito */ },
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
                                painter = painterResource(id = R.drawable.mando_xbox_series_x),
                                contentDescription = "Mando Xbox Series X",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(1f).clip(RoundedCornerShape(8.dp)),
                                contentScale = ContentScale.Crop
                            )

                            Column(
                                modifier = Modifier.padding(16.dp),
                            ) {
                                Text(
                                    text = "Mando Xbox Series X",
                                    style = MaterialTheme.typography.headlineSmall,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Un mando ergonómico con conectividad inalámbrica y compatibilidad con múltiples dispositivos para una experiencia de juego versátil.",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Spacer(modifier = Modifier.height(16.dp))

                                Text(
                                    text = "$59.900",
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.align(Alignment.End)
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                Button(
                                    onClick = { /* TODO: Lógica para añadir al carrito */ },
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
@Preview(showBackground = true)
@Composable
fun PerifericosScreenPreview() {
    LevelUpTheme {
        PerifericosScreen(navController = rememberNavController())
    }
}