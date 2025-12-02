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
fun PerifericosScreen(navController: NavController, modifier: Modifier = Modifier, carritoViewModel: CarritoViewModel) {
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
                    selected = true,
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
                        title = { Text("Periféricos", color = Color.Cyan) },
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
                                    painter = painterResource(id = R.drawable.raton_logi),
                                    contentDescription = "Raton Logitec",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .aspectRatio(1f)
                                        .clip(RoundedCornerShape(8.dp)),
                                    contentScale = ContentScale.Crop
                                )
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text("Raton Logitec G502", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, color = Color.White)
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text("Un ratón ergonómico con alta precisión y múltiples botones programables para una experiencia de juego personalizada.", style = MaterialTheme.typography.bodyMedium, color = Color.White.copy(alpha = 0.8f))
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Text("$59.900", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = Color.Cyan, modifier = Modifier.align(Alignment.End))
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Button(
                                        onClick = { val raton_logi = Producto(14, "Raton Logitec G502", 59900, "raton_logi"); carritoViewModel.agregarAlCarrito(raton_logi) },
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
                                    painter = painterResource(id = R.drawable.teclado_kurama),
                                    contentDescription = "Teclado Kurama Redragon",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .aspectRatio(1f)
                                        .clip(RoundedCornerShape(8.dp)),
                                    contentScale = ContentScale.Crop
                                )
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text("Teclado Kumara", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, color = Color.White)
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text("Un teclado mecánico con retroiluminación RGB, teclas duraderas y respuesta táctil para mejorar tu rendimiento en juegos.", style = MaterialTheme.typography.bodyMedium, color = Color.White.copy(alpha = 0.8f))
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Text("$79.900", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = Color.Cyan, modifier = Modifier.align(Alignment.End))
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Button(
                                        onClick = { val teclado_kurama = Producto(15, "Teclado Kumara", 79900, "teclado_kurama"); carritoViewModel.agregarAlCarrito(teclado_kurama) },
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
                                    painter = painterResource(id = R.drawable.razer_kraken),
                                    contentDescription = "Auriculares Razer Kraken",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .aspectRatio(1f)
                                        .clip(RoundedCornerShape(8.dp)),
                                    contentScale = ContentScale.Crop
                                )
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text("Auriculares Razer Kraken", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, color = Color.White)
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text("Auriculares con sonido envolvente, micrófono ajustable y comodidad para largas sesiones de juego.", style = MaterialTheme.typography.bodyMedium, color = Color.White.copy(alpha = 0.8f))
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Text("$89.900", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = Color.Cyan, modifier = Modifier.align(Alignment.End))
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Button(
                                        onClick = { val razerauri = Producto(16, "Auriculares Razer Kraken", 89900, "razer_kraken"); carritoViewModel.agregarAlCarrito(razerauri) },
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
                                    painter = painterResource(id = R.drawable.lg_4k_180hz),
                                    contentDescription = "Monitor LG 4K",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .aspectRatio(1f)
                                        .clip(RoundedCornerShape(8.dp)),
                                    contentScale = ContentScale.Crop
                                )
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text("Monitor LG 4K", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, color = Color.White)
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text("Un monitor de alta resolución con una frecuencia de actualización de 180Hz para una experiencia visual fluida y nítida.", style = MaterialTheme.typography.bodyMedium, color = Color.White.copy(alpha = 0.8f))
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Text("$399.900", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = Color.Cyan, modifier = Modifier.align(Alignment.End))
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Button(
                                        onClick = { val monitor = Producto(17, "Monitor LG 4K", 399900, "lg_4k_180hz"); carritoViewModel.agregarAlCarrito(monitor) },
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
                                    painter = painterResource(id = R.drawable.webcam_hd),
                                    contentDescription = "Webcam HD",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .aspectRatio(1f)
                                        .clip(RoundedCornerShape(8.dp)),
                                    contentScale = ContentScale.Crop
                                )
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text("Webcam HD", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, color = Color.White)
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text("Una webcam de alta definición ideal para streaming y videollamadas con calidad clara y nítida.", style = MaterialTheme.typography.bodyMedium, color = Color.White.copy(alpha = 0.8f))
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Text("$49.900", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = Color.Cyan, modifier = Modifier.align(Alignment.End))
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Button(
                                        onClick = { val webcam = Producto(18, "Webcam HD", 49900, "webcam_hd"); carritoViewModel.agregarAlCarrito(webcam) },
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
                                    painter = painterResource(id = R.drawable.alfombrilla_razer_xxl),
                                    contentDescription = "Alfombrilla Razer XXL",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .aspectRatio(1f)
                                        .clip(RoundedCornerShape(8.dp)),
                                    contentScale = ContentScale.Crop
                                )
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text("Alfombrilla Razer XXL", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, color = Color.White)
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text("Una alfombrilla de gran tamaño con superficie suave y base antideslizante para mejorar la precisión del ratón.", style = MaterialTheme.typography.bodyMedium, color = Color.White.copy(alpha = 0.8f))
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Text("$29.900", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = Color.Cyan, modifier = Modifier.align(Alignment.End))
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Button(
                                        onClick = { val alfombrilla = Producto(19, "Alfombrilla Razer XXL", 29900, "alfombrilla_razer_xxl"); carritoViewModel.agregarAlCarrito(alfombrilla) },
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
                                    painter = painterResource(id = R.drawable.mando_xbox_series_x),
                                    contentDescription = "Mando Xbox Series X",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .aspectRatio(1f)
                                        .clip(RoundedCornerShape(8.dp)),
                                    contentScale = ContentScale.Crop
                                )
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text("Mando Xbox Series X", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, color = Color.White)
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text("Un mando ergonómico con conectividad inalámbrica y compatibilidad con múltiples dispositivos para una experiencia de juego versátil.", style = MaterialTheme.typography.bodyMedium, color = Color.White.copy(alpha = 0.8f))
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Text("$59.900", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = Color.Cyan, modifier = Modifier.align(Alignment.End))
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Button(
                                        onClick = { val mando_series_x = Producto(20, "Mando Xbox Series X", 59900, "mando_xbox_series_x"); carritoViewModel.agregarAlCarrito(mando_series_x) },
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