package com.example.capstoneproject2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.ExperimentalPagingApi
import com.example.capstoneproject2.navigation.RootNavGraph
import com.example.capstoneproject2.presentation.Authentication.presentation.LoginScreen.LoginScreenViewModel
import com.example.capstoneproject2.presentation.screens.MainScreen.MainScreen
import com.example.capstoneproject2.theme.CapstoneProject2Theme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

@AndroidEntryPoint
@InternalCoroutinesApi
@ExperimentalPagingApi
@ExperimentalCoroutinesApi
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
                RootNavGraph(navController = rememberNavController())
                checkAuthStatus()
                getAuthState()
            }
        }
    }
    @Composable
        private fun checkAuthStatus() {
            if (viewModel.isUserAuthenticated) {
                MainScreen()
            }
        }

        private fun getAuthState() = viewModel.getAuthState()
}

