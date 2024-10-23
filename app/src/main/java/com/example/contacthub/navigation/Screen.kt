package com.example.contacthub.navigation

sealed class Screen(val route: String) {
    object homeScreen: Screen("home_screen")
    object addScreen: Screen("add_screen")
}