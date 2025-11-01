package com.example.level_up

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.level_up.navigation.NavigationEvent
import com.example.level_up.navigation.Screen
import com.example.level_up.ui.theme.LevelUpTheme
import com.example.level_up.uiscreen.ComputadoresScreen
import com.example.level_up.uiscreen.ConsolasScreen
import com.example.level_up.uiscreen.HomeScreen
import com.example.level_up.uiscreen.JuegosDeMesaScreen
import com.example.level_up.uiscreen.PerifericosScreen
import com.example.level_up.uiscreen.ProductosScreen
import com.example.level_up.uiscreen.ProfileScreen
import com.example.level_up.uiscreen.RegistroScreen
import com.example.level_up.uiscreen.ResumenScreen
import com.example.level_up.uiscreen.SettingsScreen
import com.example.level_up.viewmodels.MainViewModel
import com.example.level_up.viewmodels.UsuarioViewModel
import kotlinx.coroutines.flow.collectLatest

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LevelUpTheme {
                val viewModel: MainViewModel = viewModel()
                val navController = rememberNavController()
                val usuarioViewModel: UsuarioViewModel= viewModel()

                // Escucha los eventos de navegación emitidos por el ViewModel
                LaunchedEffect(key1 = Unit) {
                    viewModel.navigationEvent.collectLatest { event ->
                        when (event) {
                            // Caso para navegar a una pantalla específica
                            is NavigationEvent.NavigateTo -> {
                                navController.navigate(event.route.route) {
                                    event.popUpToRoute?.let { popUpToRoute ->
                                        popUpTo(popUpToRoute.route) {
                                            inclusive = event.inclusive
                                        }
                                    }
                                    launchSingleTop = event.singleTop
                                }
                            }

                            // Caso para volver a la pantalla anterior
                            is NavigationEvent.PopBackStack -> {
                                navController.popBackStack()
                            }

                            // Caso para "navegar hacia arriba" en la jerarquía
                            is NavigationEvent.NavigateUp -> {
                                navController.navigateUp()
                            }
                        }
                    }
                }

                // NavHost es el contenedor que dibuja la pantalla actual
                NavHost(
                    navController = navController,
                    startDestination = Screen.Home.route // Define la pantalla inicial
                ) {
                    composable(route = Screen.Home.route) {
                        HomeScreen(navController = navController, viewModel = viewModel)
                    }
                    composable(route = Screen.Profile.route) {
                        ProfileScreen(
                            navController = navController,
                            mainViewModel = viewModel,         // para navegación
                            usuarioViewModel = usuarioViewModel // para datos del usuario
                        )
                    }
                    composable(route = Screen.Setitings.route) {
                        SettingsScreen(navController = navController, viewModel = viewModel)
                    }
                    composable(route = Screen.Register.route){
                        RegistroScreen(navController, usuarioViewModel)
                    }
                    composable(route = Screen.Resume.route){
                        ResumenScreen(usuarioViewModel)
                    }
                    composable(route = Screen.Product.route) {
                        ProductosScreen()
                    }

                    // Rutas de Categorías
                    composable(route = Screen.JuegosDeMesa.route) { JuegosDeMesaScreen() }
                    composable(route = Screen.Perifericos.route) { PerifericosScreen() }
                    composable(route = Screen.Computadores.route) { ComputadoresScreen() }
                    composable(route = Screen.Consolas.route) { ConsolasScreen() }

                }
            }
        }
    }
}