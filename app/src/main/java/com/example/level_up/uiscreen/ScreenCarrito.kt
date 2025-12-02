package com.example.level_up.uiscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.level_up.viewmodels.CarritoViewModel
import com.example.level_up.viewmodels.ItemCarrito
import com.example.level_up.viewmodels.Producto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarritoScreen(viewModel: CarritoViewModel = viewModel()) {

    val productos by viewModel.todosLosProductos.collectAsState()
    val carrito by viewModel.itemsDelCarrito.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(Color.Black, Color(0xFF800000)), // Negro a Rojo Oscuro
                    radius = 3000f
                )
            )
    ) {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                TopAppBar(
                    title = { Text("Carrito de Compras", color = Color.Cyan) },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {

                Text(
                    text = "Lista de Productos Racomendados",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Cyan
                )

                Spacer(modifier = Modifier.height(12.dp))

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    items(productos) { producto ->
                        ProductoItem(producto = producto, onAgregar = { viewModel.agregarAlCarrito(producto) })
                    }
                }

                Divider(thickness = 1.dp, color = Color.Cyan.copy(alpha = 0.5f), modifier = Modifier.padding(vertical = 16.dp))

                Text(
                    text = "Carrito",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Cyan
                )

                if (carrito.isEmpty()) {
                    Text(
                        text = "Tu carrito está vacío",
                        modifier = Modifier.padding(top = 8.dp),
                        color = Color.White
                    )
                } else {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        items(carrito) { item ->
                            CarritoItem(
                                item = item,
                                onAumentar = { viewModel.cambiarCantidad(item.producto.id, item.cantidad + 1) },
                                onDisminuir = { viewModel.cambiarCantidad(item.producto.id, item.cantidad - 1) },
                                onEliminar = { viewModel.eliminarDelCarrito(item.producto.id) }
                            )
                        }
                    }

                    val total = carrito.sumOf { it.producto.precio * it.cantidad }

                    Text(
                        text = "Total: $${total}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Cyan,
                        modifier = Modifier
                            .align(Alignment.End)
                            .padding(top = 8.dp)
                    )

                    Button(
                        onClick = { /* Lógica para finalizar la compra */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Cyan)
                    ) {
                        Text("Finalizar compra", color = Color.Black)
                    }
                }
            }
        }
    }
}

@Composable
fun ProductoItem(producto: Producto, onAgregar: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.Gray.copy(alpha = 0.2f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(producto.nombre, fontWeight = FontWeight.Bold, color = Color.White)
                Text("Precio: $${producto.precio}", color = Color.White.copy(alpha = 0.8f))
            }
            Button(
                onClick = onAgregar,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Cyan,
                    contentColor = Color.Black
                )
            ) {
                Text("Agregar")
            }
        }
    }
}

@Composable
fun CarritoItem(
    item: ItemCarrito,
    onAumentar: () -> Unit,
    onDisminuir: () -> Unit,
    onEliminar: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.Gray.copy(alpha = 0.2f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val context = LocalContext.current
            val resourceId = remember(item.producto.urlImagen) {
                context.resources.getIdentifier(item.producto.urlImagen, "drawable", context.packageName)
            }

            if (resourceId != 0) {
                Image(
                    painter = painterResource(id = resourceId),
                    contentDescription = item.producto.nombre,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(16.dp))
            }

            Column(modifier = Modifier.weight(1f)) {
                Text(item.producto.nombre, fontWeight = FontWeight.Bold, color = Color.White)
                Text("Precio: $${item.producto.precio}", color = Color.White.copy(alpha = 0.8f))
                Text("Cantidad: ${item.cantidad}", color = Color.White.copy(alpha = 0.8f))
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onDisminuir) {
                    Icon(Icons.Default.Remove, contentDescription = "Disminuir", tint = Color.Cyan)
                }
                IconButton(onClick = onAumentar) {
                    Icon(Icons.Default.Add, contentDescription = "Aumentar", tint = Color.Cyan)
                }
                IconButton(onClick = onEliminar) {
                    Icon(Icons.Default.Delete, contentDescription = "Eliminar", tint = Color.Magenta)
                }
            }
        }
    }
}
