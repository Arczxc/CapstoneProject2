package com.example.capstoneproject2.presentation.screens.AuthenticationScreen.LoginScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.capstoneproject2.domain.model.Response
import com.example.capstoneproject2.domain.model.Response.Success
import com.example.capstoneproject2.domain.repository.AuthRepository
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.AuthCredential
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val repo: AuthRepository,
    val oneTapClient: SignInClient
): ViewModel() {
    val isUserAuthenticated get() = repo.isUserAuthenticatedInFirebase()

    private val _oneTapSignInState = mutableStateOf<Response<BeginSignInResult>>(Success(null))
    val oneTapSignInState: State<Response<BeginSignInResult>> = _oneTapSignInState

    private val _oneTapSignUpState = mutableStateOf<Response<BeginSignInResult>>(Success(null))
    val oneTapSignUpState: State<Response<BeginSignInResult>> = _oneTapSignUpState

    private val _signInState = mutableStateOf<Response<Boolean>>(Success(null))
    val signInState: State<Response<Boolean>> = _signInState

    private val _createUserState = mutableStateOf<Response<Boolean>>(Success(null))
    val createUserState: State<Response<Boolean>> = _createUserState

    fun getAuthState() = liveData(Dispatchers.IO) {
        repo.getFirebaseAuthState().collect { response ->
            emit(response)
        }
    }

    fun oneTapSignIn() {
        viewModelScope.launch {
            repo.oneTapSignInWithGoogle().collect { response ->
                _oneTapSignInState.value = response
            }
        }
    }

    fun oneTapSignUp() {
        viewModelScope.launch {
            repo.oneTapSignUpWithGoogle().collect { response ->
                _oneTapSignUpState.value = response
            }
        }
    }

    fun signInWithGoogle(googleCredential: AuthCredential) {
        viewModelScope.launch {
            repo.firebaseSignInWithGoogle(googleCredential).collect { response ->
                _signInState.value = response
            }
        }
    }

    fun createUser() {
        viewModelScope.launch {
            repo.createUserInFirestore().collect { response ->
                _createUserState.value = response
            }
        }
    }
}