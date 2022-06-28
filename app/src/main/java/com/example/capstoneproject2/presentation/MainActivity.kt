package com.example.capstoneproject2.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.capstoneproject2.presentation.navigation.NavGraph
import com.example.capstoneproject2.presentation.navigation.Screen.MainScreen
import com.example.capstoneproject2.presentation.screens.AuthenticationScreen.LoginScreen.LoginScreenViewModel
import com.example.capstoneproject2.presentation.theme.CapstoneProject2Theme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    private val viewModel by viewModels<LoginScreenViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CapstoneProject2Theme {
                // A surface container using the 'background' color from the theme
                //    Surface(
                //      modifier = Modifier.fillMaxSize(),
                //     color = MaterialTheme.colors.background
                //  ) {

                //    }
                // }

                navController = rememberAnimatedNavController()
                NavGraph(
                    navController = navController
                )
                checkAuthStatus()
                getAuthState()
            }
        }
    }
        private fun checkAuthStatus() {
            if (viewModel.isUserAuthenticated) {
                navController.navigate(MainScreen.route)
            }
        }

        private fun getAuthState() = viewModel.getAuthState()
}


//One_Navigation
