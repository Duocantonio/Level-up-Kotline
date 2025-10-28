package com.example.level_up.viewmodels

import android.util.Patterns
import androidx.lifecycle.ViewModel
import com.example.level_up.navigation.UsuarioErrores
import com.example.level_up.navigation.UsuarioUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class UsuarioViewModel : ViewModel (){
    private val _estado = MutableStateFlow(UsuarioUiState())

    val estado : StateFlow<UsuarioUiState> = _estado

    fun onNombreChange(nombreNuevo: String) {
        _estado.update { it.copy(nombre = nombreNuevo, errores = it.errores.copy(nombre = null)) }

    }

    fun onCorreoChange(correoNuevo: String){
        _estado.update { it.copy(correo = correoNuevo, errores = it.errores.copy(correo = null)) }
    }


    fun onClaveChange(claveNuevo: String) {
        _estado.update { it.copy(clave = claveNuevo, errores = it.errores.copy(clave= null)) }
    }

    fun onDireccionChange(direccionNueva: String){
        _estado.update { it.copy(direccion = direccionNueva, errores = it.errores.copy(direccion = null)) }
    }

    fun onAceptarTerminos(valor: Boolean){
        _estado.update { it.copy(aceptarTerminos = valor) }
    }


    fun validarFormulario(): Boolean{
        val estadoActual= _estado.value
        val errores= UsuarioErrores(
            nombre= if(estadoActual.nombre.isBlank())"Campo obligatorio" else null,
            correo = if(!Patterns.EMAIL_ADDRESS.matcher(estadoActual.correo).matches())"El correo debe ser valido" else null,
            clave = if(estadoActual.clave.length<6)"La contraseña debe tener al menos 6 caracteres " else null,
            direccion = if(estadoActual.direccion.isBlank())"El campo es obligatorio" else null,

            )


        val hayErrores= listOfNotNull(
            errores.nombre,
            errores.clave,
            errores.correo,
            errores.direccion
        ).isNotEmpty()

        _estado.update { it.copy(errores=errores) }

        return if(hayErrores) false
        else true
    }
}