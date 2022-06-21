package com.example.capstoneproject2.data.repository

import com.example.capstoneproject2.core.Constants.CREATED_AT
import com.example.capstoneproject2.core.Constants.DISPLAY_NAME
import com.example.capstoneproject2.core.Constants.EMAIL
import com.example.capstoneproject2.core.Constants.PHOTO_URL
import com.example.capstoneproject2.core.Constants.SIGN_IN_REQUEST
import com.example.capstoneproject2.core.Constants.SIGN_UP_REQUEST
import com.example.capstoneproject2.domain.model.Response.*
import com.example.capstoneproject2.domain.repository.AuthRepository
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FieldValue
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl  @Inject constructor(
    private val auth: FirebaseAuth,
    private var oneTapClient: SignInClient,
    @Named(SIGN_IN_REQUEST)
    private var signInRequest: BeginSignInRequest,
    @Named(SIGN_UP_REQUEST)
    private var signUpRequest: BeginSignInRequest,
    private var signInClient: GoogleSignInClient,
    private val usersRef: CollectionReference
) : AuthRepository {
    override fun isUserAuthenticatedInFirebase() = auth.currentUser != null

    override suspend fun oneTapSignInWithGoogle() = flow {
        try {
            emit(Loading)
            val result = oneTapClient.beginSignIn(signInRequest).await()
            emit(Success(result))
        } catch (e: Exception) {
            emit(Failure(e))
        }
    }

    override suspend fun oneTapSignUpWithGoogle() = flow {
        try {
            emit(Loading)
            val result = oneTapClient.beginSignIn(signUpRequest).await()
            emit(Success(result))
        } catch (e: Exception) {
            emit(Failure(e))
        }
    }

    override suspend fun firebaseSignInWithGoogle(googleCredential: AuthCredential) = flow {
        try {
            emit(Loading)
            val authResult = auth.signInWithCredential(googleCredential).await()
            val isNewUser = authResult.additionalUserInfo?.isNewUser
            emit(Success(isNewUser))
        } catch (e: Exception) {
            emit(Failure(e))
        }
    }

    override suspend fun createUserInFirestore() = flow {
        try {
            emit(Loading)
            auth.currentUser?.apply {
                usersRef.document(uid).set(
                    mapOf(
                        DISPLAY_NAME to displayName,
                        EMAIL to email,
                        PHOTO_URL to photoUrl?.toString(),
                        CREATED_AT to FieldValue.serverTimestamp()
                    )
                ).await()
                emit(Success(true))
            }
        } catch (e: Exception) {
            emit(Failure(e))
        }
    }

    override fun getFirebaseAuthState() = callbackFlow {
        val authStateListener = AuthStateListener { auth ->
            trySend(auth.currentUser == null)
        }
        auth.addAuthStateListener(authStateListener)
        awaitClose {
            auth.removeAuthStateListener(authStateListener)
        }
    }

    override suspend fun signOut() = flow {
        try {
            emit(Loading)
            auth.signOut()
            oneTapClient.signOut().await()
            emit(Success(true))
        } catch (e: Exception) {
            emit(Failure(e))
        }
    }

    override suspend fun revokeAccess() = flow {
        try {
            emit(Loading)
            auth.currentUser?.apply {
                usersRef.document(uid).delete().await()
                delete().await()
                signInClient.revokeAccess().await()
                oneTapClient.signOut().await()
            }
            emit(Success(true))
        } catch (e: Exception) {
            emit(Failure(e))
        }
    }

    override fun getDisplayName() = auth.currentUser?.displayName.toString()

    override fun getPhotoUrl() = auth.currentUser?.photoUrl.toString()
}