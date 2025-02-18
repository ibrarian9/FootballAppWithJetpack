package com.app.footballappwithjetpack.ui.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object About : Screen("about")
    data object FavoriteClub : Screen("favorite")
    data object DetailClub : Screen("home/{clubId}") {
        fun createRoute(clubId: Int) = "home/$clubId"
    }
}