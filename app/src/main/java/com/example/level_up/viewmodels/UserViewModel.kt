package com.example.level_up.viewmodels

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.level_up.data.local.User
import com.example.level_up.data.repository.UserRepository
import com.example.level_up.navigation.UsuarioErrores
import com.example.level_up.navigation.UsuarioUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow // <-- Importante
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {


    private val _estado = MutableStateFlow(UsuarioUiState())
    val estado: StateFlow<UsuarioUiState> = _estado.asStateFlow() // <-- Usa asStateFlow()

    var mostrarDialogo by mutableStateOf(false)
        private set

    fun onNombreChange(nombreNuevo: String) {
        _estado.update { it.copy(nombre = nombreNuevo, errores = it.errores.copy(nombre = null)) }
    }

    fun onCorreoChange(correoNuevo: String) {
        _estado.update { it.copy(correo = correoNuevo, errores = it.errores.copy(correo = null)) }
    }

    fun onClaveChange(claveNuevo: String) {
        _estado.update { it.copy(clave = claveNuevo, errores = it.errores.copy(clave = null)) }
    }

    fun onDireccionChange(direccionNueva: String) {
        _estado.update { it.copy(direccion = direccionNueva, errores = it.errores.copy(direccion = null)) }
    }

    fun onAceptarTerminos(valor: Boolean) {
        _estado.update { it.copy(aceptarTerminos = valor) }
    }

    fun cambiarMostrarDialogo(valor: Boolean) {
        mostrarDialogo = valor
    }

    fun validarFormulario(): Boolean {
        val estadoActual = _estado.value
        val errores = UsuarioErrores(
            nombre = if (estadoActual.nombre.isBlank()) "Campo obligatorio" else null,
            correo = if (!Patterns.EMAIL_ADDRESS.matcher(estadoActual.correo).matches()) "El correo debe ser valido" else null,
            clave = if (estadoActual.clave.length < 6) "La contraseña debe tener al menos 6 caracteres" else null,
            direccion = if (estadoActual.direccion.isBlank()) "El campo es obligatorio" else null,
        )

        val hayErrores = listOfNotNull(
            errores.nombre,
            errores.clave,
            errores.correo,
            errores.direccion
        ).isNotEmpty()

        val terminosAceptados = estadoActual.aceptarTerminos

        _estado.update { it.copy(errores = errores) }

        return !hayErrores && terminosAceptados
    }

    val users: Flow<List<User>> = repository.users

    fun registrarUsuario() {
        val estadoActual = _estado.value
        viewModelScope.launch {
            repository.insert(
                User(
                    nombre = estadoActual.nombre,
                    correo = estadoActual.correo,
                    clave = estadoActual.clave,
                    direccion = estadoActual.direccion
                )
            )
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

    fun verificarSesionActiva(onResultado: (Boolean) -> Unit) {
        viewModelScope.launch {
            val usuarioActivo = repository.obtenerUsuarioActivo()
            onResultado(usuarioActivo != null)
        }
    }

    fun iniciarSesion(correo: String) {
        viewModelScope.launch {
            repository.actualizarEstadoSesion(correo, true)
        }
    }

    fun cerrarSesion(correo: String) {
        viewModelScope.launch {
            repository.actualizarEstadoSesion(correo, false)
        }
    }

    // --- Añade esta función ---
    fun cargarDatosUsuarioActivo() {
        viewModelScope.launch {
            val usuarioActivo = repository.obtenerUsuarioActivo()
            if (usuarioActivo != null) {
                _estado.update {
                    it.copy(
                        nombre = usuarioActivo.nombre,
                        correo = usuarioActivo.correo,
                        clave = usuarioActivo.clave,
                        direccion = usuarioActivo.direccion
                        // No reseteamos los errores ni los términos aquí
                    )
                }
            }
        }
    }

    fun actualizarDatosUsuario() {
        viewModelScope.launch {
            val usuarioActivo = repository.obtenerUsuarioActivo()
            if (usuarioActivo != null) {
                val estadoActual = _estado.value
                val usuarioActualizado = usuarioActivo.copy(
                    nombre = estadoActual.nombre,
                    correo = estadoActual.correo,
                    clave = estadoActual.clave,
                    direccion = estadoActual.direccion
                )
                repository.update(usuarioActualizado)
            }
        }
    }


}