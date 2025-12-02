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
fun ComputadoresScreen(navController: NavController, modifier: Modifier = Modifier, carritoViewModel: CarritoViewModel) {
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
                    selected = true,
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
                        title = { Text("Computadores", color = Color.Cyan) },
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
                                    painter = painterResource(id = R.drawable.pc_gamer_1),
                                    contentDescription = "Torre Gamer 1",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .aspectRatio(1f)
                                        .clip(RoundedCornerShape(8.dp)),
                                    contentScale = ContentScale.Crop
                                )
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text("Computador Gamer Básico", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, color = Color.White)
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text("Un computador ideal para iniciarte en el mundo del gaming, con componentes de calidad y precio accesible.", style = MaterialTheme.typography.bodyMedium, color = Color.White.copy(alpha = 0.8f))
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Text("$599.900", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = Color.Cyan, modifier = Modifier.align(Alignment.End))
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Button(
                                        onClick = { val torre1 = Producto(1, "Computador Gamer Básico", 599900, "pc_gamer_1"); carritoViewModel.agregarAlCarrito(torre1) },
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
                                    painter = painterResource(id = R.drawable.pc_gamer_2),
                                    contentDescription = "Torre Gamer 2",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .aspectRatio(1f)
                                        .clip(RoundedCornerShape(8.dp)),
                                    contentScale = ContentScale.Crop
                                )
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text("Computador Gamer Avanzado", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, color = Color.White)
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text("Un computador potente para juegos AAA, con una tarjeta gráfica de alta gama y un procesador rápido.", style = MaterialTheme.typography.bodyMedium, color = Color.White.copy(alpha = 0.8f))
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Text("$1.299.900", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = Color.Cyan, modifier = Modifier.align(Alignment.End))
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Button(
                                        onClick = { val torre2 = Producto(2, "Computador Gamer Avanzado", 1299900, "pc_gamer_2"); carritoViewModel.agregarAlCarrito(torre2) },
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
                                    painter = painterResource(id = R.drawable.laptop_gamer),
                                    contentDescription = "Laptop Gamer",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .aspectRatio(1f)
                                        .clip(RoundedCornerShape(8.dp)),
                                    contentScale = ContentScale.Crop
                                )
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text("Laptop Gamer", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, color = Color.White)
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text("Una laptop diseñada para gamers, con una pantalla de alta frecuencia de actualización y un diseño portátil.", style = MaterialTheme.typography.bodyMedium, color = Color.White.copy(alpha = 0.8f))
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Text("$999.900", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = Color.Cyan, modifier = Modifier.align(Alignment.End))
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Button(
                                        onClick = { val laptop = Producto(3, "Laptop Gamer", 999900, "laptop_gamer"); carritoViewModel.agregarAlCarrito(laptop) },
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
                                    painter = painterResource(id = R.drawable.pc_streaming),
                                    contentDescription = "PC Streaming",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .aspectRatio(1f)
                                        .clip(RoundedCornerShape(8.dp)),
                                    contentScale = ContentScale.Crop
                                )
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text("PC Streaming", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, color = Color.White)
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text("Un computador optimizado para streaming, con capacidad para manejar múltiples tareas simultáneamente.", style = MaterialTheme.typography.bodyMedium, color = Color.White.copy(alpha = 0.8f))
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Text("$1.499.900", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = Color.Cyan, modifier = Modifier.align(Alignment.End))
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Button(
                                        onClick = { val torre3 = Producto(4, "PC Streaming", 1499900, "pc_streaming"); carritoViewModel.agregarAlCarrito(torre3) },
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