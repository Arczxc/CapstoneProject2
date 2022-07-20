package com.example.capstoneproject2.presentation.MainScreen.presentation.screen.ProfileScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstoneproject2.presentation.Authentication.domain.model.Response
import com.example.capstoneproject2.presentation.Authentication.domain.model.Response.*
import com.example.capstoneproject2.presentation.Authentication.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repo: AuthRepository
): ViewModel() {
    private val _signOutState = mutableStateOf<Response<Boolean>>(Success(false))
    val signOutState: State<Response<Boolean>> = _signOutState

    private val _revokeAccessState = mutableStateOf<Response<Boolean>>(Success(false))
    val revokeAccessState: State<Response<Boolean>> = _revokeAccessState

    val displayName get() = repo.getDisplayName()

    val photoUrl get() = repo.getPhotoUrl()

    fun signOut() {
        viewModelScope.launch {
            repo.signOut().collect { response ->
                _signOutState.value = response
            }
        }
    }

    fun revokeAccess() {
        viewModelScope.launch {
            repo.revokeAccess().collect { response ->
                _revokeAccessState.value = response
            }
        }
    }
}