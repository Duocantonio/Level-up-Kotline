package com.example.level_up.uiscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.level_up.viewmodels.UsuarioViewModel
import org.w3c.dom.Text


@Composable
fun ResumenScreen (viewModel: UsuarioViewModel){
    val estado by viewModel.estado.collectAsState()


    Column(Modifier.padding(16.dp)){
        Text("Resumen del Usuario", style= MaterialTheme.typography.headlineMedium)
        Text("Nombre: ${estado.nombre}")
        Text("Clave: ${estado.clave}")
        Text("Correo: ${estado.correo}")
        Text("Direccion: ${estado.direccion}")
        Text("Acepto Terminos: ${estado.aceptarTerminos}")


    }
}
