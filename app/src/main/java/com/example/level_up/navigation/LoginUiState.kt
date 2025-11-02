package com.example.level_up.navigation

data class LoginUiState(
    val correo : String="",
    val clave : String="",
    val error : String? = null
)