package com.example.level_up.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.level_up.navigation.LoginUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onCorreoChange(correo: String) {
        _uiState.update { it.copy(correo = correo) }
    }

    fun onClaveChange(clave: String) {
        _uiState.update { it.copy(clave = clave) }
    }

    fun login(onLoginSuccess: () -> Unit) {
        viewModelScope.launch {
            if (uiState.value.correo == "admin" && uiState.value.clave == "admin") {
                onLoginSuccess()
            } else {
                _uiState.update {
                    it.copy(error = "Credenciales incorrectas")
                }
            }
        }
    }
}
