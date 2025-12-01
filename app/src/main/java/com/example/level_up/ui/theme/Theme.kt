package com.example.level_up.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

// Define tus colores Neón
val NeonGreen = Color(0xFF39FF14) // Un verde neón brillante
val NeonPink = Color(0xFFF100FF)  // Un rosa neón vibrante
val NeonBlue = Color(0xFF00FFFF)  // Un azul cian neón
val DarkPurpleBackground = Color(0xFF1A0033) // Fondo Morado Oscuro (100% opaco)

// NUEVO COLOR: Morado para superficie con 80% de opacidad (CC en hexadecimal)
// Este es el color que usamos para las Cards y la TopAppBar.
val TransparentPurpleSurface = Color(0xCC330099)


private val DarkColorScheme = darkColorScheme(
    primary = NeonBlue,
    onPrimary = Color.Black,
    // Usamos el color transparente para la TopAppBar (primaryContainer)
    primaryContainer = TransparentPurpleSurface,
    onPrimaryContainer = Color.White,
    background = DarkPurpleBackground,
    onBackground = NeonGreen,
    // Usamos el color transparente para las Cards (surface)
    surface = TransparentPurpleSurface,
    onSurface = NeonPink
)

private val LightColorScheme = lightColorScheme(
    // Colores Primarios
    primary = NeonGreen,
    onPrimary = Color.Black,
    // Usamos el color transparente para la TopAppBar (primaryContainer)
    primaryContainer = TransparentPurpleSurface,
    onPrimaryContainer = Color.White,

    // Colores de Fondo
    background = DarkPurpleBackground,
    onBackground = Color.White,

    // Colores de Superficie (Cards)
    // Usamos el color transparente para las Cards (surface)
    surface = TransparentPurpleSurface,
    onSurface = NeonPink,
)

@Composable
fun LevelUpTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        // Usamos la ruta completa para resolver el error de ambigüedad
        typography = com.example.level_up.ui.theme.Typography,
        content = content
    )
}