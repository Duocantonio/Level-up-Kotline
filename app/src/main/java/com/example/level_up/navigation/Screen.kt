package com.example.level_up.navigation

sealed class Screen (val route: String) {

    data object Main : Screen("main_screen")

    data object Home : Screen("home_page")

    data object Profile : Screen("profile_page")

    data object Setitings : Screen("settings_page")
    data object Register : Screen("registrar")
    data object Resume : Screen("resumen")
    object Camera : Screen("camera")

    data class Detail (val itemId: String) : Screen("detail_page/{itemId}"){
        fun buildRoute(): String {
            return route.replace("{itemId}", itemId)
        }
    }
//D:
}