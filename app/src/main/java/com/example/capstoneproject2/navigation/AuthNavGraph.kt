package com.example.capstoneproject2.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.paging.ExperimentalPagingApi
import com.example.capstoneproject2.presentation.Authentication.presentation.LoginScreen.LoginScreen
import kotlinx.coroutines.InternalCoroutinesApi


@OptIn(InternalCoroutinesApi::class, ExperimentalPagingApi::class)
fun NavGraphBuilder.authNavGraph(navController: NavHostController){
    navigation(
        route =Graph.AUTHENTICATION,
        startDestination =AuthScreen.Login.route
    ){
        composable(route = AuthScreen.Login.route){
            LoginScreen(
                onClick = {
                    navController.popBackStack()
                    navController.navigate(Graph.HOME)
                },
                onSignUpClick = {
                    navController.navigate(AuthScreen.SignUp.route)
                },
                onForgotClick = {
                    navController.navigate(AuthScreen.Forgot.route)
                }
            )
           /* composable(route = AuthScreen.SignUp.route) {
                ScreenContent(name = AuthScreen.SignUp.route) {}
            }
            composable(route = AuthScreen.Forgot.route) {
                ScreenContent(name = AuthScreen.Forgot.route) {}
            }*/
        }
    }
}

//TODO Make MainScreen a GRAPH
