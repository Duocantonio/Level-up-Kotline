package com.example.level_up.navigation


data class UsuarioUiState (
    val nombre: String ="",
    val correo : String ="",
    val clave : String ="",
    val direccion: String = "",
    val aceptarTerminos : Boolean =false,
    val errores: UsuarioErrores = UsuarioErrores()
)

data class UsuarioErrores(
    val nombre: String?= null,
    val correo: String?= null,
    val clave : String?=null,
    val direccion: String?= null

)
