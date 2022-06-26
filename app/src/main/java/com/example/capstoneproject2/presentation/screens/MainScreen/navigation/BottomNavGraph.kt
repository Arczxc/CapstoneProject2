package com.example.capstoneproject2.presentation.screens.MainScreen.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.capstoneproject2.presentation.navigation.Screen
import com.example.capstoneproject2.presentation.screens.MainScreen.components.BottomBarScreen
import com.example.capstoneproject2.presentation.screens.MainScreen.screen.CartScreen.CartScreen
import com.example.capstoneproject2.presentation.screens.MainScreen.screen.HomeScreen.HomeScreen
import com.example.capstoneproject2.presentation.screens.MainScreen.screen.ProfileScreen.ProfileScreen
import com.example.capstoneproject2.presentation.screens.MainScreen.screen.TicketScreen.TicketScreen

@Composable
fun BottomNavGraph (navController: NavHostController){
    NavHost(
        navController = navController ,
        startDestination =BottomBarScreen.Home.route
    ){
        composable(route = BottomBarScreen.Home.route){
            HomeScreen()
        }
        composable(route = BottomBarScreen.Ticket.route){
            TicketScreen()
        }
        composable(route = BottomBarScreen.Cart.route){
            CartScreen()
        }
        composable(route = BottomBarScreen.Profile.route){
            ProfileScreen(
              
            )
        }
    }
}