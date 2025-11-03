
package com.example.level_up.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class Producto(
    val id: Int,
    val nombre: String,
    val precio: Int,
    val urlImagen: String = ""
)

data class ItemCarrito(
    val producto: Producto,
    val cantidad: Int
)


class CarritoViewModel : ViewModel() {

    private val _todosLosProductos = MutableStateFlow<List<Producto>>(emptyList())
    val todosLosProductos: StateFlow<List<Producto>> = _todosLosProductos.asStateFlow()

    private val _itemsDelCarrito = MutableStateFlow<List<ItemCarrito>>(emptyList())
    val itemsDelCarrito: StateFlow<List<ItemCarrito>> = _itemsDelCarrito.asStateFlow()

    init {
        _todosLosProductos.value = listOf(
            Producto(1, "Teclado Gamer Pro", 89999),
            Producto(2, "Mouse Óptico RGB", 45550),
            Producto(3, "Monitor Curvo 27\"", 320000),
            Producto(4, "Audífonos 7.1", 110000)
        )
    }


    fun agregarAlCarrito(producto: Producto) {
        viewModelScope.launch {
            val itemExistente = _itemsDelCarrito.value.find { it.producto.id == producto.id }

            if (itemExistente != null) {
                _itemsDelCarrito.update { listaActual ->
                    listaActual.map { item ->
                        if (item.producto.id == producto.id) {
                            item.copy(cantidad = item.cantidad + 1) // Suma 1
                        } else {
                            item
                        }
                    }
                }
            } else {
                _itemsDelCarrito.update { listaActual ->
                    listaActual + ItemCarrito(producto = producto, cantidad = 1)
                }
            }
        }
    }

    fun eliminarDelCarrito(productoId: Int) {
        viewModelScope.launch {
            _itemsDelCarrito.update { listaActual ->
                listaActual.filter { it.producto.id != productoId }
            }
        }
    }

    fun cambiarCantidad(productoId: Int, nuevaCantidad: Int) {
        viewModelScope.launch {
            if (nuevaCantidad > 0) {
                _itemsDelCarrito.update { listaActual ->
                    listaActual.map { item ->
                        if (item.producto.id == productoId) {
                            item.copy(cantidad = nuevaCantidad)
                        } else {
                            item
                        }
                    }
                }
            } else {
                eliminarDelCarrito(productoId)
            }
        }
    }
}