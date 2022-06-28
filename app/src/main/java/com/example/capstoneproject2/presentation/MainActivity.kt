package com.example.capstoneproject2.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.capstoneproject2.presentation.navigation.Screen.MainScreen
import com.example.capstoneproject2.presentation.screens.AuthenticationScreen.LoginScreen.LoginScreenViewModel
import com.example.capstoneproject2.presentation.screens.MainScreen.navigation.Graph
import com.example.capstoneproject2.presentation.screens.MainScreen.navigation.RootNavGraph
import com.example.capstoneproject2.presentation.theme.CapstoneProject2Theme
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
                RootNavGraph(navController = navController)

                checkAuthStatus()
                getAuthState()
            }
        }
    }
        private fun checkAuthStatus() {
            if (viewModel.isUserAuthenticated) {
                navController.navigate(Graph.HOME)
            }
        }

        private fun getAuthState() = viewModel.getAuthState()
}

