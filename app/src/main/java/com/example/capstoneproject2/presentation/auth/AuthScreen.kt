package com.example.capstoneproject2.presentation.auth

import android.app.Activity.RESULT_OK
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts.StartIntentSenderForResult
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider.getCredential
import com.example.capstoneproject2.core.Utils.Companion.print
import com.example.capstoneproject2.presentation.auth.components.AuthContent
import com.example.capstoneproject2.presentation.auth.components.AuthTopBar
import com.example.capstoneproject2.presentation.auth.components.OneTapSignIn
import com.example.capstoneproject2.presentation.auth.components.SignInWithGoogle

@Composable
@ExperimentalMaterial3Api
fun AuthScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    navigateToHomeScreen: () -> Unit
) {
    Scaffold(
        topBar = {
            AuthTopBar()
        },
        content = { padding ->
            AuthContent(
                padding = padding,
                oneTapSignIn = {
                    viewModel.oneTapSignIn()
                }
            )
        }
    )

    val launcher =  rememberLauncherForActivityResult(StartIntentSenderForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            try {
                val credentials = viewModel.oneTapClient.getSignInCredentialFromIntent(result.data)
                val googleIdToken = credentials.googleIdToken
                val googleCredentials = getCredential(googleIdToken, null)
                viewModel.signInWithGoogle(googleCredentials)
            } catch (it: ApiException) {
                print(it)
            }
        }
    }

    fun launch(signInResult: BeginSignInResult) {
        val intent = IntentSenderRequest.Builder(signInResult.pendingIntent.intentSender).build()
        launcher.launch(intent)
    }

    OneTapSignIn {
        launch(it)
    }

    SignInWithGoogle { signedIn ->
        if (signedIn) {
            navigateToHomeScreen()
        }
    }
}