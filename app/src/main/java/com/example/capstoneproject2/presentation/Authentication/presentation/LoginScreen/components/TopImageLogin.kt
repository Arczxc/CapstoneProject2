package com.example.capstoneproject2.presentation.Authentication.presentation.LoginScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.capstoneproject2.R

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
