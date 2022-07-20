package com.example.capstoneproject2.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.SupportAgent
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.capstoneproject2.core.Constants.AUTH_SCREEN
import com.example.capstoneproject2.core.Constants.MAIN_SCREEN


sealed class Screen(val route: String) {
    object AuthScreen: Screen(AUTH_SCREEN)
    object MainScreen: Screen(MAIN_SCREEN)
}

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector,
){
    object Home : BottomBarScreen(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home,
    )

    object Ticket : BottomBarScreen(
        route = "ticket",
        title = "Ticket",
        icon = Icons.Default.SupportAgent,
    )

    object Cart : BottomBarScreen(
        route = "cart",
        title = "Cart",
        icon = Icons.Default.ShoppingCart,
    )

    object Profile : BottomBarScreen(
        route = "profile",
        title = "Profile",
        icon = Icons.Default.Person,
    )

}
