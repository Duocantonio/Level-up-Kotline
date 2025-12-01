package com.example.level_up

import com.example.level_up.api.model.Usuario
import com.example.level_up.viewmodels.LoginViewModel
import com.example.level_up.api.repository.UsuarioRepository
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest


@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest : StringSpec({

    "Se logea de forma correcta" {

        val usuarioFake = listOf(
            Usuario(
                id = 1,
                nombre = "PruebaLogin",
                edad = 20,
                email = "correo@porfavorfunciona.com",
                clave1 = "1234",
                clave2 = "1234",
                direccion = "Chile"
            )
        )

        val mockRepo = mockk<UsuarioRepository>()
        coEvery { mockRepo.getUsuario() } returns usuarioFake

        val viewModel = LoginViewModel(mockRepo)

        viewModel.onCorreoChange("correo@porfavorfunciona.com")
        viewModel.onClaveChange("1234")

        var loginEjecutado = false

        runTest {
            viewModel.login { loginEjecutado = true }
            advanceUntilIdle()
        }
        loginEjecutado shouldBe true
        viewModel.uiState.value.error shouldBe null
        viewModel.uiState.value.loading shouldBe false
    }
})
