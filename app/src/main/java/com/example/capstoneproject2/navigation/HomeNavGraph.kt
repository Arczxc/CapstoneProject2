package com.example.capstoneproject2.presentation.screens.MainScreen.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.ExperimentalPagingApi
import com.example.capstoneproject2.navigation.BottomBarScreen
import com.example.capstoneproject2.navigation.Graph
import com.example.capstoneproject2.presentation.MainScreen.presentation.screen.CartScreen.CartScreen
import com.example.capstoneproject2.presentation.MainScreen.presentation.screen.HomeScreen.HomeScreen
import com.example.capstoneproject2.presentation.MainScreen.presentation.screen.ProfileScreen.ProfileScreen
import com.example.capstoneproject2.presentation.MainScreen.presentation.screen.TicketScreen.TicketScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@ExperimentalPagingApi
@ExperimentalCoroutinesApi
@Composable
fun HomeNavGraph (navController: NavHostController){
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomBarScreen.Home.route
    ){
        composable(route = BottomBarScreen.Home.route){
            HomeScreen(
                navController = navController
            )
        }
        composable(route = BottomBarScreen.Ticket.route){
            TicketScreen()
        }
        composable(route = BottomBarScreen.Cart.route){
            CartScreen()
        }
        composable(route = BottomBarScreen.Profile.route){
            ProfileScreen(
                navigateToAuthScreen = {
                    navController.popBackStack()
                    navController.navigate(Graph.HOME)
                }
            )
        }
    }
}

