package com.example.capstoneproject2.presentation.MainScreen.presentation.screen.ProfileScreen.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.capstoneproject2.presentation.MainScreen.presentation.screen.ProfileScreen.ProfileViewModel

@Composable
fun ProfileDisplay(
    viewModel: ProfileViewModel = hiltViewModel()
){
    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(viewModel.photoUrl)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .width(96.dp)
                .height(96.dp)
        )
        Text(
            text = viewModel.displayName,
            fontSize = 24.sp
        )
    }
    /* Row(
         modifier = Modifier
             .padding(8.dp)
             .fillMaxWidth(),
         horizontalArrangement = Arrangement.SpaceBetween
     ) {

         Row() {
             Button(onClick = { navController.navigate("Login" ) },
                 Modifier
                     .background(Color.Black)
             ) {
                 Text(text = "Log in")
             }
             Spacer(modifier = Modifier.padding(8.dp))
             Button(onClick = { navController.navigate("Signup") },
                 Modifier
                     .background(Color.Black)
             ) {
                 Text(text = "Sign up")
             }
         }
     }*/
}
