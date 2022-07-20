package com.example.capstoneproject2.presentation.Authentication.presentation.LoginScreen

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.ExperimentalPagingApi
import com.example.capstoneproject2.presentation.Authentication.domain.model.Response
import com.example.capstoneproject2.presentation.Authentication.presentation.LoginScreen.components.*
import com.example.capstoneproject2.core.Constants
import com.example.capstoneproject2.core.Utils
import com.example.capstoneproject2.presentation.components.ProgressBar
import com.example.capstoneproject2.presentation.screens.MainScreen.MainScreen
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

@Composable
@InternalCoroutinesApi
@ExperimentalPagingApi
@ExperimentalCoroutinesApi
fun LoginScreen(
    viewModel: LoginScreenViewModel = hiltViewModel(),
    onClick: () -> Unit,
    onSignUpClick: () -> Unit,
    onForgotClick: () -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopImageLogin()
        Spacer(modifier = Modifier.padding(8.dp))
        GoogleButton { viewModel.oneTapSignIn() }
        EmailInput()
        PassInput()
        LoginButton()
        TextButtons(text = "Forgot Password ?")
        TextButtons(text = "Don't have an Account ? Sign Up")
    }

    val launcher =  rememberLauncherForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
        if (result.resultCode == AppCompatActivity.RESULT_OK) {
            try {
                val credentials = viewModel.oneTapClient.getSignInCredentialFromIntent(result.data)
                val googleIdToken = credentials.googleIdToken
                val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)
                viewModel.signInWithGoogle(googleCredentials)
            } catch (e: ApiException) {
                Utils.print(e)
            }
        }
    }

    fun launch(signInResult: BeginSignInResult) {
        val intent = IntentSenderRequest.Builder(signInResult.pendingIntent.intentSender).build()
        launcher.launch(intent)
    }

    when(val oneTapSignInResponse = viewModel.oneTapSignInState.value) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> {
            oneTapSignInResponse.data?.let {
                LaunchedEffect(it) {
                    launch(it)
                }
            }
        }
        is Response.Failure -> {
            oneTapSignInResponse.e?.let {
                LaunchedEffect(Unit) {
                    Utils.print(it)
                    if (it.message == Constants.SIGN_IN_ERROR_MESSAGE) {
                        viewModel.oneTapSignUp()
                    }
                }
            }
        }
    }

    when(val oneTapSignUpResponse = viewModel.oneTapSignUpState.value) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> {
            oneTapSignUpResponse.data?.let {
                LaunchedEffect(it) {
                    launch(it)
                }
            }
        }
        is Response.Failure -> oneTapSignUpResponse.e?.let {
            LaunchedEffect(Unit) {
                Utils.print(it)
            }
        }
    }

    when(val signInResponse = viewModel.signInState.value) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> {
            signInResponse.data?.let { isNewUser ->
                if (isNewUser) {
                    LaunchedEffect(isNewUser) {
                        viewModel.createUser()
                    }
                } else {
                    MainScreen()
                }
            }
        }
        is Response.Failure -> signInResponse.e?.let {
            LaunchedEffect(Unit) {
                Utils.print(it)
            }
        }
    }

    when(val createUserResponse = viewModel.createUserState.value) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> {
            createUserResponse.data?.let { isUserCreated ->
                if (isUserCreated) {
                    MainScreen()
                }
            }
        }
        is Response.Failure -> createUserResponse.e?.let {
            LaunchedEffect(Unit) {
                Utils.print(it)
            }
        }
    }
}