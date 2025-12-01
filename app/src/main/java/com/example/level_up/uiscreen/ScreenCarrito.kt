package com.example.level_up.uiscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.level_up.viewmodels.CarritoViewModel
import com.example.level_up.viewmodels.Producto
import com.example.level_up.viewmodels.ItemCarrito

@Composable
fun CarritoScreen(viewModel: CarritoViewModel = viewModel()) {

    val productos by viewModel.todosLosProductos.collectAsState()
    val carrito by viewModel.itemsDelCarrito.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F7))
            .padding(16.dp)
    ) {

        Text(
            text = " Lista de Productos",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
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

        Divider(thickness = 2.dp, color = Color.Gray, modifier = Modifier.padding(vertical = 8.dp))

        Text(
            text = "Carrito",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        if (carrito.isEmpty()) {
            Text(
                text = "Tu carrito está vacío",
                modifier = Modifier.padding(top = 8.dp),
                color = Color.Gray
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
                text = " Total: $${total}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 8.dp)
            )

            Button(
                onClick = { /* Lógica para finalizar la compra */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            ) {
                Text("Finalizar compra")
            }
        }
    }
}

@Composable
fun ProductoItem(producto: Producto, onAgregar: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column {
                Text(producto.nombre, fontWeight = FontWeight.Bold)
                Text("Precio: $${producto.precio}")
            }

            Button(onClick = onAgregar) {
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
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column {
                Text(item.producto.nombre, fontWeight = FontWeight.Bold)
                Text("Precio: $${item.producto.precio}")
                Text("Cantidad: ${item.cantidad}")
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onDisminuir) {
                    Icon(Icons.Default.Remove, contentDescription = "Disminuir")
                }
                IconButton(onClick = onAumentar) {
                    Icon(Icons.Default.Add, contentDescription = "Aumentar")
                }
                IconButton(onClick = onEliminar) {
                    Icon(Icons.Default.Delete, contentDescription = "Eliminar")
                }
            }
        }
    }
}
