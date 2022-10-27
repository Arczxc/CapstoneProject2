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

sealed class AuthScreen(val route: String) {
    object Login : AuthScreen(route = "LOGIN")
    object SignUp : AuthScreen(route = "SIGN_UP")
    object Forgot : AuthScreen(route = "FORGOT")
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

sealed class HomeScreen(
    val route: String,
){

    object Search: HomeScreen(
        route = "search"
    )

    object Details: HomeScreen(
        route = "details"
    )

}

sealed class DetailsScreen(val route: String) {
    object Information : DetailsScreen(route = "INFORMATION")
    object Overview : DetailsScreen(route = "OVERVIEW")
}

sealed class TicketScreens(val route:String){
    object ContactUs: TicketScreens(route = "CONTACTUS")
}

sealed class Cart(val route: String){
    object Gcash: Cart(route = "GCASH")
}