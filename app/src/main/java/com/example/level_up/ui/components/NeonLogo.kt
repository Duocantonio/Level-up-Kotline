package com.example.level_up.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.level_up.R

@Composable
fun NeonLogo() {
    val logoSize = 200.dp
    val glowOuterSize = 150.dp

    val glowRadius = glowOuterSize.value / 2

    Box(
        modifier = Modifier
            .size(glowOuterSize)
            .background(Color.Transparent),
        contentAlignment = Alignment.Center
    ) {
        // CAPA DEL RESPLANDOR
        Box(
            modifier = Modifier
                .size(glowOuterSize)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Color.Cyan.copy(alpha = 0.8f),
                            Color.Cyan.copy(alpha = 0.4f),
                            Color.Transparent
                        ),
                        radius = glowRadius
                    )
                )
        )

        // CAPA DE LA IMAGEN
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo de Level-Up",
            modifier = Modifier.size(logoSize)
        )
    }
}
