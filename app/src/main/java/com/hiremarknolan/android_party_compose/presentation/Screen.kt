package com.hiremarknolan.android_party_compose.presentation

sealed class Screen(val route: String) {

    object LoginScreen: Screen("login_screen")
    object ServerScreen: Screen("server_screen")

}