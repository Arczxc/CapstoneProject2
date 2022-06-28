package com.example.capstoneproject2.presentation.screens.AuthenticationScreen.LoginScreen

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.capstoneproject2.core.Constants.SIGN_IN_ERROR_MESSAGE
import com.example.capstoneproject2.domain.model.Response.*
import com.example.capstoneproject2.core.Utils.Companion.print
import com.example.capstoneproject2.presentation.components.ProgressBar
import com.example.capstoneproject2.presentation.screens.MainScreen.MainScreen
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider


@Composable
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
        InputEmailPass()

    }
    
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
                    MainScreen()
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
                       MainScreen()
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

@Composable
fun TopImageLogin(){
    Card(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(.5f)) {
        Image(
            painter = painterResource(id = com.example.capstoneproject2.R.drawable.login_img),
            contentDescription = "Login Image",
            contentScale = ContentScale.FillBounds
        )
    }
}


@Composable
fun GoogleButton(onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick ,
        //modifier= Modifier.size(50.dp),  //avoid the oval shape
        shape = RectangleShape ,
        border= BorderStroke(1.dp, Color.Black),
        contentPadding = PaddingValues(8.dp),  //avoid the little icon
        colors = ButtonDefaults.outlinedButtonColors(contentColor =  Color.Blue)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = com.example.capstoneproject2.R.drawable.ic_google),
                contentDescription = "",
                tint = Color.Unspecified,
                modifier = Modifier.size(26.dp)
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(text = "Continue with Google", color = Color.Black, fontSize = 16.sp)
        }
    }
}


@Composable
fun InputEmailPass(){
    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Log In with Email",
                //color = LightTextColor,
                //fontFamily = Poppins,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 16.dp)
            )
            EmailInput()
            PassInput()
            LoginButton()
            TextButtonss(text = "Forgot Password ?")
            TextButtonss(text = "Don't have an Account ? Sign Up")

        }
    }
}

@Composable
fun EmailInput(){
    var email by remember { mutableStateOf("") }

    OutlinedTextField(
        value = email, onValueChange = {
            email = it
        },
        label = {
            Text(text = "Email Address", color = Color.Black)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(top = 10.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Color.Black,
            textColor = Color.Black

        ),
        keyboardOptions = KeyboardOptions(
            keyboardType =
            KeyboardType.Email
        ),
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = "",
                tint = Color.Black,
                modifier = Modifier.size(24.dp)
            )
        }
    )
}

@Composable
fun PassInput(){
    var password by remember { mutableStateOf("") }
    var isPasswordOpen by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = password, onValueChange = {
            password = it
        },
        label = {
            Text(text = "Password", color = Color.Black)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(top = 10.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Color.Black,
            textColor = Color.Black
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        visualTransformation = if (!isPasswordOpen) PasswordVisualTransformation() else VisualTransformation.None,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = "",
                tint = Color.Black,
                modifier = Modifier.size(24.dp)
            )
        },
        trailingIcon = {
            IconButton(onClick = { isPasswordOpen = !isPasswordOpen }) {
                if (!isPasswordOpen) {
                    Icon(
                        imageVector = Icons.Default.Visibility,
                        contentDescription = "",
                        tint = Color.Black,
                        modifier = Modifier.size(24.dp)
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.VisibilityOff,
                        contentDescription = "",
                        tint = Color.Black,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    )
}

@Composable
fun LoginButton(){
    Button(
        onClick = {},
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(top = 20.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Black,
            contentColor = Color.White
        ),
        contentPadding = PaddingValues(vertical = 14.dp)
    ) {
        Text(text = "Login")
    }
}


@Composable
fun TextButtonss(text: String){
    TextButton(
        onClick = {},
        contentPadding = PaddingValues(vertical = 0.dp)
    ) {
        Text(
            text = "Forgot Password ?",
            color = Color.Gray,
            fontSize = 12.sp,
            modifier = Modifier.padding(top = 26.dp)
        )
    }
}






