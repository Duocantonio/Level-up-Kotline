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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.level_up.R
import com.example.level_up.navigation.Screen
import com.example.level_up.ui.theme.LevelUpTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerifericosScreen(modifier: Modifier = Modifier) {
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
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column {
                        Image(
                            painter = painterResource(id = R.drawable.raton_logi),
                            contentDescription = "Raton Logitec",
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f).clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )

                        Column(
                            modifier = Modifier.padding(16.dp),
                        ) {
                            Text(
                                text = "Raton Logitec G502",
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Un ratón ergonómico con alta precisión y múltiples botones programables para una experiencia de juego personalizada.",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Spacer(modifier = Modifier.height(16.dp))

                            Text(
                                text = "$59.900",
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
                            painter = painterResource(id = R.drawable.teclado_kurama),
                            contentDescription = "Teclado Kurama Redragon",
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
                                text = "Un teclado mecánico con retroiluminación RGB, teclas duraderas y respuesta táctil para mejorar tu rendimiento en juegos.",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Spacer(modifier = Modifier.height(16.dp))

                            Text(
                                text = "$79.900",
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
                            painter = painterResource(id = R.drawable.razer_kraken),
                            contentDescription = "Auriculares Razer Kraken",
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f).clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )

                        Column(
                            modifier = Modifier.padding(16.dp),
                        ) {
                            Text(
                                text = "Auriculares Razer Kraken",
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Auriculares con sonido envolvente, micrófono ajustable y comodidad para largas sesiones de juego.",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Spacer(modifier = Modifier.height(16.dp))

                            Text(
                                text = "$89.900",
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
                            painter = painterResource(id = R.drawable.lg_4k_180hz),
                            contentDescription = "Monitor LG 4K",
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f).clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )

                        Column(
                            modifier = Modifier.padding(16.dp),
                        ) {
                            Text(
                                text = "Monitor LG 4K",
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Un monitor de alta resolución con una frecuencia de actualización de 180Hz para una experiencia visual fluida y nítida.",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Spacer(modifier = Modifier.height(16.dp))

                            Text(
                                text = "$399.900",
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
                            painter = painterResource(id = R.drawable.webcam_hd),
                            contentDescription = "Webcam HD",
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f).clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )

                        Column(
                            modifier = Modifier.padding(16.dp),
                        ) {
                            Text(
                                text = "Webcam HD",
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Una webcam de alta definición ideal para streaming y videollamadas con calidad clara y nítida.",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Spacer(modifier = Modifier.height(16.dp))

                            Text(
                                text = "$49.900",
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
                            painter = painterResource(id = R.drawable.alfombrilla_razer_xxl),
                            contentDescription = "Alfombrilla Razer XXL",
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f).clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )

                        Column(
                            modifier = Modifier.padding(16.dp),
                        ) {
                            Text(
                                text = "Alfombrilla Razer XXL",
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Una alfombrilla de gran tamaño con superficie suave y base antideslizante para mejorar la precisión del ratón.",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Spacer(modifier = Modifier.height(16.dp))

                            Text(
                                text = "$29.900",
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
                            painter = painterResource(id = R.drawable.mando_xbox_series_x),
                            contentDescription = "Mando Xbox Series X",
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f).clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )

                        Column(
                            modifier = Modifier.padding(16.dp),
                        ) {
                            Text(
                                text = "Mando Xbox Series X",
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Un mando ergonómico con conectividad inalámbrica y compatibilidad con múltiples dispositivos para una experiencia de juego versátil.",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Spacer(modifier = Modifier.height(16.dp))

                            Text(
                                text = "$59.900",
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
fun PerifericosScreenPreview() {
    LevelUpTheme {
        JuegosDeMesaScreen()
    }
}