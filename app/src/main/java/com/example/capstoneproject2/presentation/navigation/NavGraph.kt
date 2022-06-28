package com.example.capstoneproject2.presentation.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.capstoneproject2.presentation.navigation.Screen.AuthScreen
import com.example.capstoneproject2.presentation.navigation.Screen.MainScreen
import com.example.capstoneproject2.presentation.screens.AuthenticationScreen.LoginScreen.LoginScreen
import com.example.capstoneproject2.presentation.screens.MainScreen.MainScreen
import com.example.capstoneproject2.presentation.screens.MainScreen.components.BottomBarScreen
import com.example.capstoneproject2.presentation.screens.MainScreen.screen.ProfileScreen.ProfileScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavGraph(
    navController: NavHostController
){
    AnimatedNavHost(
        navController = navController,
        startDestination = AuthScreen.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ){
        composable(
            route = Screen.AuthScreen.route
        ) {
            LoginScreen(
                navigateToMainScreen = {
                    navController.navigate(MainScreen.route)
                }
            )
        }
        composable(
            route = Screen.MainScreen.route
        ) {
            MainScreen()
        }

    }
}
