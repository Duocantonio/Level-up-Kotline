package com.example.level_up.uiscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.level_up.R
import com.example.level_up.ui.theme.LevelUpTheme
import androidx.compose.ui.draw.paint // Importación necesaria
import androidx.compose.ui.graphics.painter.Painter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComputadoresScreen(modifier: Modifier = Modifier) {

    // 1. Define el Painter para la imagen de fondo
    // ¡Asegúrate de cambiar 'fondo_tecnologico' por el nombre de tu recurso de imagen!
    val backgroundPainter: Painter = painterResource(id = R.drawable.fondo_pagina)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Computadores") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                // 2. Aplicamos el color de fondo del tema (DarkPurpleBackground)
                .background(MaterialTheme.colorScheme.background)
                // 3. Aplicamos la imagen de fondo con .paint()
                .paint(
                    painter = backgroundPainter,
                    contentScale = ContentScale.Crop, // Escala para cubrir todo el LazyColumn
                    alignment = Alignment.Center,
                    alpha = 0.4f // Reducir la opacidad a 0.4 para asegurar que los textos neón se vean bien
                )
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // El resto de la estructura de items se mantiene igual
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column {
                        Image(
                            painter = painterResource(id = R.drawable.pc_gamer_1),
                            contentDescription = "Torre Gamer 1",
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f).clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )

                        Column(
                            modifier = Modifier.padding(16.dp),
                        ) {
                            Text(
                                text = "Computador Gamer Basico",
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Un computador ideal para iniciarte en el mundo del gaming, con componentes de calidad y precio accesible.",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Spacer(modifier = Modifier.height(16.dp))

                            Text(
                                text = "$599.900",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.align(Alignment.End)
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            Button(
                                onClick = { /* TODO: Lógica para añadir al carrito */ },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp)
                            ) {
                                Text("Añadir al Carrito")
                            }
                        }
                    }
                }
            }

            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column {
                        Image(
                            painter = painterResource(id = R.drawable.pc_gamer_2),
                            contentDescription = "Torre Gamer 2",
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f).clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )

                        Column(
                            modifier = Modifier.padding(16.dp),
                        ) {
                            Text(
                                text = "Computador Gamer Avanzado",
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Un computador potente para juegos AAA, con una tarjeta gráfica de alta gama y un procesador rápido.",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Spacer(modifier = Modifier.height(16.dp))

                            Text(
                                text = "$1.299.900",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.align(Alignment.End)
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            Button(
                                onClick = { /* TODO: Lógica para añadir al carrito */ },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp)
                            ) {
                                Text("Añadir al Carrito")
                            }
                        }
                    }
                }
            }

            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column {
                        Image(
                            painter = painterResource(id = R.drawable.laptop_gamer),
                            contentDescription = "Laptop Gamer",
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f).clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )

                        Column(
                            modifier = Modifier.padding(16.dp),
                        ) {
                            Text(
                                text = "Laptop Gamer",
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Una laptop diseñada para gamers, con una pantalla de alta frecuencia de actualización y un diseño portátil.",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Spacer(modifier = Modifier.height(16.dp))

                            Text(
                                text = "$999.900",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.align(Alignment.End)
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            Button(
                                onClick = { /* TODO: Lógica para añadir al carrito */ },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp)
                            ) {
                                Text("Añadir al Carrito")
                            }
                        }
                    }
                }
            }

            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column {
                        Image(
                            painter = painterResource(id = R.drawable.pc_streaming),
                            contentDescription = "PC Streaming",
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f).clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )

                        Column(
                            modifier = Modifier.padding(16.dp),
                        ) {
                            Text(
                                text = "PC Streaming",
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Un computador optimizado para streaming, con capacidad para manejar múltiples tareas simultáneamente.",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Spacer(modifier = Modifier.height(16.dp))

                            Text(
                                text = "$1.499.900",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.align(Alignment.End)
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            Button(
                                onClick = { /* TODO: Lógica para añadir al carrito */ },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp)
                            ) {
                                Text("Añadir al Carrito")
                            }
                        }
                    }
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ComputadesScreenPreview() {
    LevelUpTheme {
        ComputadoresScreen()
    }
}