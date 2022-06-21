package com.example.capstoneproject2.presentation.screens.AuthenticationScreen.LoginScreen.components

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
import com.example.capstoneproject2.R
import com.example.capstoneproject2.presentation.screens.AuthenticationScreen.LoginScreen.LoginScreenViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LoginScreenContent(
    padding: PaddingValues,
    viewModel: LoginScreenViewModel = hiltViewModel()
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
}

@Composable
fun TopImageLogin(){
    Card(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(.5f)) {
        Image(
            painter = painterResource(id = R.drawable.login_img),
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
                painter = painterResource(id = R.drawable.ic_google),
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
