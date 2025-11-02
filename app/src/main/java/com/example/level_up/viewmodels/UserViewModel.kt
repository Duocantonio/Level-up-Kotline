package com.example.level_up.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.level_up.data.local.User
import com.example.level_up.data.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    val users: Flow<List<User>> = repository.users

    fun addUser(nombre: String, correo: String, clave: String, direccion: String) {
        viewModelScope.launch {
            repository.insert(User(nombre = nombre, correo = correo, clave = clave, direccion = direccion))
        }
    }

    fun updateUser(user: User, nuevoNombre: String, nuevoCorreo: String, nuevaClave: String, nuevaDireccion: String) {
        viewModelScope.launch {
            val updatedUser = user.copy(
                nombre = nuevoNombre,
                correo = nuevoCorreo,
                clave = nuevaClave,
                direccion = nuevaDireccion
            )
            repository.update(updatedUser)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch {
            repository.delete(user)
        }
    }
}
