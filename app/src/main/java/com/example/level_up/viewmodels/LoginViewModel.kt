package com.example.level_up.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.level_up.data.repository.UserRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class LoginUiState(
    val correo: String = "",
    val clave: String = "",
    val error: String? = null
)

class LoginViewModel(private val repository: UserRepository) : ViewModel() {

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
            val correo = uiState.value.correo
            val clave = uiState.value.clave

            val usuario = repository.verificarCredenciales(correo, clave)

            if (usuario != null) {
                repository.actualizarEstadoSesion(correo, true)
                onLoginSuccess()
            } else {
                _uiState.update { it.copy(error = "Correo o contraseña incorrectos") }
            }
        }
    }
}
