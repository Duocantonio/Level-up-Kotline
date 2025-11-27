package com.example.level_up.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.level_up.api.model.Usuario
import com.example.level_up.api.repository.PostRepository
import com.example.level_up.data.repository.UserRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


data class LoginUiState(
    val correo: String = "",
    val clave: String = "",
    val error: String? = null,
    val loading: Boolean = false
)

class LoginViewModel(private val repository: PostRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    fun onCorreoChange(nuevoCorreo: String) {
        _uiState.update { it.copy(correo = nuevoCorreo) }
    }

    fun onClaveChange(nuevaClave: String) {
        _uiState.update { it.copy(clave = nuevaClave) }
    }

    fun login(onLoginSuccess: () -> Unit) {
        viewModelScope.launch {

            _uiState.update { it.copy(loading = true, error = null) }

            try {
                val correo = uiState.value.correo
                val clave = uiState.value.clave

                val usuarios = repository.getUsuario()

                val usuario = usuarios.find { it.email == correo && it.clave1 == clave }

                if (usuario != null) {
                    onLoginSuccess()
                } else {
                    _uiState.update { it.copy(error = "Correo o contraseña incorrectos") }
                }

            } catch (e: Exception) {
                _uiState.update {
                    it.copy(error = "Error de conexión: ${e.message}")
                }
            } finally {
                _uiState.update { it.copy(loading = false) }
            }
        }
    }
}
