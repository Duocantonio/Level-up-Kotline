package com.example.level_up.navigation

import Screen

sealed class NavigationEvent {

    data class NavigateTo(
        val route: Screen,
        val popUpToRoute: Screen? = null,
        val inclusive: Boolean = false,
        val singleTop: Boolean = true
    ) : NavigationEvent()

    object PopBackStack : NavigationEvent()

    object NavigateUp : NavigationEvent()
}
