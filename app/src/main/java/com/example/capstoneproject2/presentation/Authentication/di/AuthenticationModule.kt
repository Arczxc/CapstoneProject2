package com.example.capstoneproject2.presentation.Authentication.di

import android.app.Application
import android.content.Context
import com.example.capstoneproject2.R
import com.example.capstoneproject2.core.Constants
import com.example.capstoneproject2.presentation.Authentication.data.repository.AuthRepositoryImpl
import com.example.capstoneproject2.presentation.Authentication.domain.repository.AuthRepository
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Named

@Module
@ExperimentalCoroutinesApi
@InstallIn(SingletonComponent::class)
class AuthenticationModule {                       //Modules are kind of containers for specific types of dependencies

    @Provides
    fun provideContext(
        app: Application
    ): Context = app.applicationContext

    @Provides
    fun provideFirebaseAuth() = Firebase.auth      //Firebase authentication instance

    @Provides
    fun provideFirebaseFirestore() = Firebase.firestore      // Firebase database instance

    @Provides
    fun provideUsersRef(
        db: FirebaseFirestore
    ) = db.collection(Constants.USERS_REF)      // USERS COLLECTION IN FIRESTORE

    @Provides
    fun provideOneTapClient(
        context: Context
    ) = Identity.getSignInClient(context)

    @Provides
    @Named(Constants.SIGN_IN_REQUEST)
    fun provideSignInRequest(
        app: Application
    ) = BeginSignInRequest.builder()
        .setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                .setServerClientId(app.getString(R.string.web_client_id))
                .setFilterByAuthorizedAccounts(false)
                .build()
        )
        .setAutoSelectEnabled(false)
        .build()

    @Provides
    @Named(Constants.SIGN_UP_REQUEST)
    fun provideSignUpRequest(
        app: Application
    ) = BeginSignInRequest.builder()
        .setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                .setServerClientId(app.getString(R.string.web_client_id))
                .setFilterByAuthorizedAccounts(false)
                .build()
        )
        .build()

    @Provides
    fun provideGoogleSignInOptions(
        app: Application
    ) = GoogleSignInOptions
        .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(app.getString(R.string.web_client_id))
        .requestEmail()
        .build()

    @Provides
    fun provideGoogleSignInClient(
        app: Application,
        options: GoogleSignInOptions
    ) = GoogleSignIn.getClient(app, options)

    @Provides
    fun provideAuthRepository(
        oneTapClient: SignInClient,
        @Named(Constants.SIGN_IN_REQUEST)
        signInRequest: BeginSignInRequest,
        @Named(Constants.SIGN_UP_REQUEST)
        signUpRequest: BeginSignInRequest,
        signInClient: GoogleSignInClient,
        auth: FirebaseAuth,                                   //The entry point of the Firebase Authentication SDK
        usersRef: CollectionReference
    ): AuthRepository = AuthRepositoryImpl(                 // Will return AuhtRepository Implementation
        oneTapClient = oneTapClient,
        signInRequest = signInRequest,
        signUpRequest = signUpRequest,
        signInClient = signInClient,
        auth = auth,                                          //The entry point of the Firebase Authentication SDK
        usersRef = usersRef
    )
}