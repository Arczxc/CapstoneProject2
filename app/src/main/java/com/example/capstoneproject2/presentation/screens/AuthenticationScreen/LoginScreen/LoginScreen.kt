package com.example.capstoneproject2.presentation.screens.AuthenticationScreen.LoginScreen

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.capstoneproject2.core.Constants.SIGN_IN_ERROR_MESSAGE
import com.example.capstoneproject2.domain.model.Response.*
import com.example.capstoneproject2.core.Utils.Companion.print
import com.example.capstoneproject2.presentation.components.ProgressBar
import com.example.capstoneproject2.presentation.screens.AuthenticationScreen.LoginScreen.components.LoginScreenContent
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider


@Composable
fun LoginScreen(
    viewModel: LoginScreenViewModel = hiltViewModel(),
    navigateToMainScreen: () -> Unit,
    ){
    Scaffold(
        content ={ padding ->
            LoginScreenContent(padding)
        }
    )
    
        val launcher =  rememberLauncherForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
            if (result.resultCode == AppCompatActivity.RESULT_OK) {
                try {
                    val credentials = viewModel.oneTapClient.getSignInCredentialFromIntent(result.data)
                    val googleIdToken = credentials.googleIdToken
                    val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)
                    viewModel.signInWithGoogle(googleCredentials)
                } catch (e: ApiException) {
                    print(e)
                }
            }
        }

        fun launch(signInResult: BeginSignInResult) {
            val intent = IntentSenderRequest.Builder(signInResult.pendingIntent.intentSender).build()
            launcher.launch(intent)
        }

        when(val oneTapSignInResponse = viewModel.oneTapSignInState.value) {
            is Loading -> ProgressBar()
            is Success -> {
                oneTapSignInResponse.data?.let {
                    LaunchedEffect(it) {
                        launch(it)
                    }
                }
            }
            is Failure -> {
                oneTapSignInResponse.e?.let {
                    LaunchedEffect(Unit) {
                        print(it)
                        if (it.message == SIGN_IN_ERROR_MESSAGE) {
                            viewModel.oneTapSignUp()
                        }
                    }
                }
            }
        }

        when(val oneTapSignUpResponse = viewModel.oneTapSignUpState.value) {
            is Loading -> ProgressBar()
            is Success -> {
                oneTapSignUpResponse.data?.let {
                    LaunchedEffect(it) {
                        launch(it)
                    }
                }
            }
            is Failure -> oneTapSignUpResponse.e?.let {
                LaunchedEffect(Unit) {
                    print(it)
                }
            }
        }

    when(val signInResponse = viewModel.signInState.value) {
        is Loading -> ProgressBar()
        is Success -> {
            signInResponse.data?.let { isNewUser ->
                if (isNewUser) {
                    LaunchedEffect(isNewUser) {
                        viewModel.createUser()
                    }
                } else {
                    navigateToMainScreen()
                }
            }
        }
            is Failure -> signInResponse.e?.let {
                LaunchedEffect(Unit) {
                    print(it)
                }
            }
        }

        when(val createUserResponse = viewModel.createUserState.value) {
            is Loading -> ProgressBar()
            is Success -> {
                createUserResponse.data?.let { isUserCreated ->
                    if (isUserCreated) {
                        navigateToMainScreen()
                    }
                }
            }
            is Failure -> createUserResponse.e?.let {
                LaunchedEffect(Unit) {
                    print(it)
                }
            }
        }
}





