package com.example.level_up.api.repository

import com.example.level_up.api.model.Usuario
import com.example.level_up.api.remote.ApiService
import com.example.level_up.api.remote.RetrofitInstance

open class UsuarioRepository{

    open suspend fun getUsuario(): List<Usuario> {
        return RetrofitInstance.api.getUsuario()
    }

    open suspend fun createUsuario(usuario: Usuario): Usuario {
        return RetrofitInstance.api.createUsuario(usuario)
    }

    open suspend fun updateUsuario(id: Int, usuario: Usuario): Usuario {
        return RetrofitInstance.api.updateUsuario(id, usuario)
    }




}