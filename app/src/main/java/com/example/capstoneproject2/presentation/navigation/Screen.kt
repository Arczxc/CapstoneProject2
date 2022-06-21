package com.example.capstoneproject2.presentation.navigation

import com.example.capstoneproject2.core.Constants.AUTH_SCREEN
import com.example.capstoneproject2.core.Constants.MAIN_SCREEN


sealed class Screen(val route: String) {
    object AuthScreen: Screen(AUTH_SCREEN)
    object MainScreen: Screen(MAIN_SCREEN)
}