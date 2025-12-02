package com.example.level_up.uiscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.level_up.R
import com.example.level_up.viewmodels.CarritoViewModel
import com.example.level_up.viewmodels.Producto
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JuegosDeMesaScreen(navController: NavController, modifier: Modifier = Modifier, carritoViewModel: CarritoViewModel) {
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
                    selected = true, // Marcado como seleccionado
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
            Scaffold(
                containerColor = Color.Transparent,
                topBar = {
                    TopAppBar(
                        title = { Text("Juegos de Mesa", color = Color.Cyan) },
                        navigationIcon = {
                            IconButton(onClick = { scope.launch { drawerState.open() } }) {
                                Icon(Icons.Default.Menu, contentDescription = "Abrir menú", tint = Color.Cyan)
                            }
                        },
                        actions = {
                            IconButton(onClick = { navController.navigate(Screen.Cart.route) }) {
                                Icon(Icons.Default.ShoppingCart, contentDescription = "Carrito", tint = Color.Cyan)
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
                    )
                }
            ) { innerPadding ->
                LazyColumn(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = Color.Gray.copy(alpha = 0.2f)),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                        ) {
                            Column {
                                Image(
                                    painter = painterResource(id = R.drawable.catan),
                                    contentDescription = "Caja del juego Catan",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .aspectRatio(1f)
                                        .clip(RoundedCornerShape(8.dp)),
                                    contentScale = ContentScale.Crop
                                )
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text("Catan", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, color = Color.White)
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text("Un juego de estrategia y comercio donde los jugadores compiten por colonizar una isla, construir caminos y comerciar recursos.", style = MaterialTheme.typography.bodyMedium, color = Color.White.copy(alpha = 0.8f))
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Text("$29.900", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = Color.Cyan, modifier = Modifier.align(Alignment.End))
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Button(
                                        onClick = { val catan = Producto(9, "catan", 29900, "catan"); carritoViewModel.agregarAlCarrito(catan) },
                                        modifier = Modifier.fillMaxWidth().height(50.dp),
                                        colors = ButtonDefaults.buttonColors(containerColor = Color.Cyan)
                                    ) {
                                        Text("Añadir al Carrito", color = Color.Black)
                                    }
                                }
                            }
                        }
                    }
                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = Color.Gray.copy(alpha = 0.2f)),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                        ) {
                            Column {
                                Image(
                                    painter = painterResource(id = R.drawable.monopoly),
                                    contentDescription = "Caja del juego monopoly",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .aspectRatio(1f)
                                        .clip(RoundedCornerShape(8.dp)),
                                    contentScale = ContentScale.Crop
                                )
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text("Monopoly Deadpool", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, color = Color.White)
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text("Los jugadores pueden disfrutar del juego clásico con el humor característico del personaje de cómic de Marvel, moviéndose por el tablero para contratar mercenarios y adquirir juegos. El objetivo es ser el último jugador con recursos tras la bancarrota de los demás.", style = MaterialTheme.typography.bodyMedium, color = Color.White.copy(alpha = 0.8f))
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Text("$39.900", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = Color.Cyan, modifier = Modifier.align(Alignment.End))
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Button(
                                        onClick = { val monopoly = Producto(10, "Monopoly Deadpool", 39900, "monopoly"); carritoViewModel.agregarAlCarrito(monopoly) },
                                        modifier = Modifier.fillMaxWidth().height(50.dp),
                                        colors = ButtonDefaults.buttonColors(containerColor = Color.Cyan)
                                    ) {
                                        Text("Añadir al Carrito", color = Color.Black)
                                    }
                                }
                            }
                        }
                    }
                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = Color.Gray.copy(alpha = 0.2f)),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                        ) {
                            Column {
                                Image(
                                    painter = painterResource(id = R.drawable.dixit),
                                    contentDescription = "Caja del juego dixit",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .aspectRatio(1f)
                                        .clip(RoundedCornerShape(8.dp)),
                                    contentScale = ContentScale.Crop
                                )
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text("Dixit", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, color = Color.White)
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text("Un juego de cartas ilustradas donde los jugadores usan su imaginación para contar historias y adivinar las cartas de los demás.", style = MaterialTheme.typography.bodyMedium, color = Color.White.copy(alpha = 0.8f))
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Text("$24.990", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = Color.Cyan, modifier = Modifier.align(Alignment.End))
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Button(
                                        onClick = { val dixit = Producto(11, "dixit", 24990, "dixit"); carritoViewModel.agregarAlCarrito(dixit) },
                                        modifier = Modifier.fillMaxWidth().height(50.dp),
                                        colors = ButtonDefaults.buttonColors(containerColor = Color.Cyan)
                                    ) {
                                        Text("Añadir al Carrito", color = Color.Black)
                                    }
                                }
                            }
                        }
                    }
                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = Color.Gray.copy(alpha = 0.2f)),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                        ) {
                            Column {
                                Image(
                                    painter = painterResource(id = R.drawable.risk),
                                    contentDescription = "Caja del juego risk",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .aspectRatio(1f)
                                        .clip(RoundedCornerShape(8.dp)),
                                    contentScale = ContentScale.Crop
                                )
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text("Risk", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, color = Color.White)
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text("Un juego de estrategia militar donde los jugadores luchan por el control del mundo mediante la conquista de territorios y la gestión de ejércitos.", style = MaterialTheme.typography.bodyMedium, color = Color.White.copy(alpha = 0.8f))
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Text("$34.990", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = Color.Cyan, modifier = Modifier.align(Alignment.End))
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Button(
                                        onClick = { val risk = Producto(12, "risk", 34990, "risk"); carritoViewModel.agregarAlCarrito(risk) },
                                        modifier = Modifier.fillMaxWidth().height(50.dp),
                                        colors = ButtonDefaults.buttonColors(containerColor = Color.Cyan)
                                    ) {
                                        Text("Añadir al Carrito", color = Color.Black)
                                    }
                                }
                            }
                        }
                    }
                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = Color.Gray.copy(alpha = 0.2f)),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                        ) {
                            Column {
                                Image(
                                    painter = painterResource(id = R.drawable.carcassonne),
                                    contentDescription = "Caja del juego carcassonne",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .aspectRatio(1f)
                                        .clip(RoundedCornerShape(8.dp)),
                                    contentScale = ContentScale.Crop
                                )
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text("Carcassonne", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, color = Color.White)
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text("Un juego de colocación de losetas donde los jugadores construyen un paisaje medieval con ciudades, caminos y campos, y compiten por el control de estas áreas.", style = MaterialTheme.typography.bodyMedium, color = Color.White.copy(alpha = 0.8f))
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Text("$29.990", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = Color.Cyan, modifier = Modifier.align(Alignment.End))
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Button(
                                        onClick = { val carca = Producto(13, "carcassonne", 29990, "carcassonne"); carritoViewModel.agregarAlCarrito(carca) },
                                        modifier = Modifier.fillMaxWidth().height(50.dp),
                                        colors = ButtonDefaults.buttonColors(containerColor = Color.Cyan)
                                    ) {
                                        Text("Añadir al Carrito", color = Color.Black)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}