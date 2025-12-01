package com.example.level_up

import com.example.level_up.viewmodels.CarritoViewModel
import com.example.level_up.viewmodels.Producto
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain

@OptIn(ExperimentalCoroutinesApi::class)
class CarritoViewModelTest : StringSpec({

    val testDispatcher = StandardTestDispatcher()

    "agregar producto nuevo lo agrega con cantidad 1" {
        val viewModel = CarritoViewModel()
        val producto = Producto(10, "Producto Test", 5000)

        runTest(testDispatcher) { // Pasamos el dispatcher aquí también por seguridad
            viewModel.agregarAlCarrito(producto)
            advanceUntilIdle()
        }

        val items = viewModel.itemsDelCarrito.value

        items.size shouldBe 1
        items.first().producto.id shouldBe producto.id
        items.first().cantidad shouldBe 1
    }

    "agregar mismo producto incrementa cantidad" {
        val viewModel = CarritoViewModel()
        val producto = Producto(10, "Producto Test", 5000)

        runTest(testDispatcher) {
            viewModel.agregarAlCarrito(producto)
            viewModel.agregarAlCarrito(producto)
            advanceUntilIdle() // Espera a que ambos terminen
        }

        val items = viewModel.itemsDelCarrito.value

        items.size shouldBe 1
        items.first().cantidad shouldBe 2
    }

    "eliminar producto lo quita del carrito" {
        val viewModel = CarritoViewModel()
        val producto = Producto(10, "Producto Test", 5000)

        runTest(testDispatcher) {
            viewModel.agregarAlCarrito(producto)
            advanceUntilIdle()

            viewModel.eliminarDelCarrito(producto.id)
            advanceUntilIdle()
        }

        viewModel.itemsDelCarrito.value.size shouldBe 0
    }

    "cambiar cantidad actualiza correctamente" {
        val viewModel = CarritoViewModel()
        val producto = Producto(10, "Producto Test", 5000)

        runTest(testDispatcher) {
            viewModel.agregarAlCarrito(producto)
            advanceUntilIdle()

            viewModel.cambiarCantidad(producto.id, 5)
            advanceUntilIdle()
        }

        val item = viewModel.itemsDelCarrito.value.first()
        item.cantidad shouldBe 5
    }

    "cambiar cantidad a 0 elimina producto" {
        val viewModel = CarritoViewModel()
        val producto = Producto(10, "Producto Test", 5000)

        runTest(testDispatcher) {
            viewModel.agregarAlCarrito(producto)
            advanceUntilIdle()

            // Al poner 0, internamente llama a eliminarDelCarrito
            viewModel.cambiarCantidad(producto.id, 0)
            advanceUntilIdle()
        }

        viewModel.itemsDelCarrito.value.size shouldBe 0
    }
})