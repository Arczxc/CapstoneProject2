package com.example.capstoneproject2.presentation.screens.MainScreen.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.paging.ExperimentalPagingApi
import com.example.capstoneproject2.navigation.*
import com.example.capstoneproject2.presentation.MainScreen.presentation.screen.CartScreen.CartScreen
import com.example.capstoneproject2.presentation.MainScreen.presentation.screen.CartScreen.NextScreen.Gcash
import com.example.capstoneproject2.presentation.MainScreen.presentation.screen.HomeScreen.HomeScreen
import com.example.capstoneproject2.presentation.MainScreen.presentation.screen.HomeScreen.NextScreen.ProductDetailsScreen
import com.example.capstoneproject2.presentation.MainScreen.presentation.screen.ProfileScreen.ProfileScreen
import com.example.capstoneproject2.presentation.MainScreen.presentation.screen.TicketScreen.NextScreen.ContactUs
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
                onClick = {
                    navController.navigate(Graph.DETAILS)
                }
            )
        }
        composable(route = BottomBarScreen.Ticket.route){
            TicketScreen(
                onClick = {
                    navController.navigate(Graph.TICKET)
                }
            )
        }
        composable(route = BottomBarScreen.Cart.route){
            CartScreen(
                onClick = {
                navController.navigate(Graph.CART)
            }
            )
        }
        composable(route = BottomBarScreen.Profile.route){
            ProfileScreen(
                navigateToAuthScreen = {
                    navController.popBackStack()
                    navController.navigate(Graph.HOME)
                }
            )
        }

        detailsNavGraph(navController = navController)
        ticketNavGraph(navController = navController)
        cartNavGraph(navController = navController)
    }
}

fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.DETAILS,
        startDestination = DetailsScreen.Information.route
    ) {
        composable(route = DetailsScreen.Information.route) {
            ProductDetailsScreen() {

            }
        }
        //composable(route = DetailsScreen.Overview.route) {
         //   ScreenContent(name = DetailsScreen.Overview.route) {
       //         navController.popBackStack(
        //            route = DetailsScreen.Information.route,
       //             inclusive = false
        //        )
        //    }
        //}
    }
}

fun NavGraphBuilder.ticketNavGraph(navController: NavHostController){
    navigation(
        route = Graph.TICKET,
        startDestination = TicketScreens.ContactUs.route
    ){
        composable(route = TicketScreens.ContactUs.route){
            ContactUs()
        }

    }
}

fun NavGraphBuilder.cartNavGraph(navController: NavHostController){
    navigation(
        route = Graph.CART,
        startDestination = Cart.Gcash.route
    ){
        composable(route = Cart.Gcash.route){
            Gcash()
        }

    }
}



