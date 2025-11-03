sealed class Screen(val route: String) {

    data object Main : Screen("main_screen")
    data object Home : Screen("home_page")
    data object Profile : Screen("profile_page")
    data object Camera : Screen("camera_page")
    data object Setitings : Screen("settings_page")
    data object Register : Screen("registro")
    data object Resume : Screen("resumen")
    data object Product : Screen("productos")
    data object Cart : Screen("carrito")
    data object Login : Screen("login")


    // Rutas de Categorías
    data object JuegosDeMesa : Screen("juegos_de_mesa")
    data object Perifericos : Screen("perifericos")
    data object Computadores : Screen("computadores")
    data object Consolas : Screen("consolas")
}
