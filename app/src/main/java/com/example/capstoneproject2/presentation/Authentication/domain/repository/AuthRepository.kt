package com.example.capstoneproject2.presentation.Authentication.domain.repository

import com.example.capstoneproject2.presentation.Authentication.domain.model.Response
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.firebase.auth.AuthCredential
import kotlinx.coroutines.flow.Flow

interface AuthRepository {                                                       //Abstracting the functionality and implementation details of the actual repository
    fun isUserAuthenticatedInFirebase(): Boolean
                                                                                 // suspend func can be suspended and can start somewhere else in an application
    suspend fun oneTapSignInWithGoogle(): Flow<Response<BeginSignInResult>>

    suspend fun oneTapSignUpWithGoogle(): Flow<Response<BeginSignInResult>>

    suspend fun firebaseSignInWithGoogle(googleCredential: AuthCredential): Flow<Response<Boolean>>

    suspend fun createUserInFirestore(): Flow<Response<Boolean>>

    suspend fun signOut(): Flow<Response<Boolean>>

    suspend fun revokeAccess(): Flow<Response<Boolean>>

    fun getFirebaseAuthState(): Flow<Boolean>

    fun getDisplayName(): String

    fun getPhotoUrl(): String
}