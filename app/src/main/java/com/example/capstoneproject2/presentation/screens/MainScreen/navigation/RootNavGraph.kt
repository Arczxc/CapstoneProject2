package com.example.capstoneproject2.presentation.screens.MainScreen.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.capstoneproject2.presentation.screens.MainScreen.MainScreen
import com.example.capstoneproject2.presentation.screens.MainScreen.screen.HomeScreen.HomeScreen

@Composable
fun RootNavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTHENTICATION
    ){
        authNavGraph(navController = navController)
        composable(route = Graph.HOME) {
            HomeScreen()
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val HOME = "home_graph"
    const val DETAILS = "details_graph"
}