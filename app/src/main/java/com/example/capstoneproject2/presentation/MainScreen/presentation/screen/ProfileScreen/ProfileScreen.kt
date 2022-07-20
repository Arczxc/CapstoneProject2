package com.example.capstoneproject2.presentation.MainScreen.presentation.screen.ProfileScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.capstoneproject2.presentation.Authentication.domain.model.Response.*
import com.example.capstoneproject2.presentation.MainScreen.presentation.screen.ProfileScreen.components.ProfileDisplay
import com.example.capstoneproject2.presentation.MainScreen.presentation.screen.ProfileScreen.components.ProfileTopAppBar
import com.example.capstoneproject2.presentation.MainScreen.presentation.screen.ProfileScreen.components.SupportOptions
import com.example.capstoneproject2.presentation.components.ProgressBar


@Composable
fun ProfileScreen(navigateToAuthScreen: () -> Unit, viewModel: ProfileViewModel = hiltViewModel()){
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize())
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(bottom = 100.dp)
        ){
            ProfileTopAppBar()
            ProfileDisplay()
            SupportOptions()
        }
    }

    when(val signOutResponse = viewModel.signOutState.value) {
        is Loading -> ProgressBar()
        is Success -> {
            val signedOut = signOutResponse.data
            signedOut?.let {
                if (signedOut) {
                    LaunchedEffect(signOutResponse.data) {
                        //navController.navigate("AUTH_SCREEN")
                        navigateToAuthScreen()
                    }
                }
            }
        }
        is Failure -> signOutResponse.e?.let {
            LaunchedEffect(Unit) {
                print(it)
            }
        }
    }

    /*fun showSnackBar() = coroutineScope.launch {
        val result = scaffoldState.snackbarHostState.showSnackbar(
            message = REVOKE_ACCESS_MESSAGE,
            actionLabel = SIGN_OUT
        )
        if (result == SnackbarResult.ActionPerformed) {
            viewModel.signOut()
        }
    }

    when(val revokeAccessResponse = viewModel.revokeAccessState.value) {
        is Loading -> ProgressBar()
        is Success -> {
            val accessRevoked = revokeAccessResponse.data
            accessRevoked?.let {
                if (accessRevoked) {
                    LaunchedEffect(revokeAccessResponse.data) {
                        //navController.navigate("AUTH_SCREEN")
                    }
                }
            }
        }
        is Failure -> revokeAccessResponse.e?.let {
            LaunchedEffect(Unit) {
                print(it)
                showSnackBar()
            }
        }
    }*/
}





