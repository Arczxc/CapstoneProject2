package com.example.capstoneproject2.presentation.screens.MainScreen.screen.ProfileScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.ContentAlpha.medium
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.capstoneproject2.domain.model.Response.*
import com.example.capstoneproject2.presentation.components.ProgressBar
import com.example.capstoneproject2.presentation.theme.Shapes
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(navigateToAuthScreen: () -> Unit, viewModel: ProfileViewModel = hiltViewModel()){
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize())
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(bottom = 100.dp)
        ){
            Header()
            ProfileAppBar()
            SupportOptionsUI()
        }
    }

    when(val signOutResponse = viewModel.signOutState.value) {
        is Loading -> ProgressBar()
        is Success -> {
            val signedOut = signOutResponse.data
            signedOut?.let {
                if (signedOut) {
                    LaunchedEffect(signOutResponse.data) {
                        //navController.navigate("AUTH_SCREEN")
                        navigateToAuthScreen()
                    }
                }
            }
        }
        is Failure -> signOutResponse.e?.let {
            LaunchedEffect(Unit) {
                print(it)
            }
        }
    }

    /*fun showSnackBar() = coroutineScope.launch {
        val result = scaffoldState.snackbarHostState.showSnackbar(
            message = REVOKE_ACCESS_MESSAGE,
            actionLabel = SIGN_OUT
        )
        if (result == SnackbarResult.ActionPerformed) {
            viewModel.signOut()
        }
    }

    when(val revokeAccessResponse = viewModel.revokeAccessState.value) {
        is Loading -> ProgressBar()
        is Success -> {
            val accessRevoked = revokeAccessResponse.data
            accessRevoked?.let {
                if (accessRevoked) {
                    LaunchedEffect(revokeAccessResponse.data) {
                        //navController.navigate("AUTH_SCREEN")
                    }
                }
            }
        }
        is Failure -> revokeAccessResponse.e?.let {
            LaunchedEffect(Unit) {
                print(it)
                showSnackBar()
            }
        }
    }*/
}

@Composable
fun Header(){
    Text(
        text = "Profile Screen",
        color = Color.Black,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp, bottom = 10.dp),

        )
}

@Composable
fun ProfileAppBar(
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

@Composable
fun SupportOptionsUI(viewModel: ProfileViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier
            .padding(horizontal = 14.dp)
            .padding(top = 10.dp)
    ) {
        Text(
            text = "Support",
            color = Color.Black,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(vertical = 8.dp)
        )
        SupportItem(
            icon = Icons.Default.Smartphone,
            mainText = "Contact",
            onClick = {}
        )
        SupportItem(
            icon = Icons.Default.Feedback,
            mainText = "Feedback",
            onClick = {}
        )
        SupportItem(
            icon = Icons.Default.SystemSecurityUpdateGood,
            mainText = "Privacy Policy",
            onClick = {}
        )
        SupportItem(
            icon = Icons.Default.PriorityHigh,
            mainText = "About",
            onClick = {}
        )
        SupportItem(
            icon = Icons.Default.Logout,
            mainText = "Sign Out",
            onClick = {viewModel.signOut()}
        )

    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SupportItem(icon: ImageVector, mainText: String, onClick: () -> Unit) {
    Card(
        onClick = { onClick() },
        backgroundColor = Color.White,
        modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth(),
        elevation = 0.dp,
    ) {
        Row(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(34.dp)
                        .clip(shape = Shapes.medium)
                        .background(Color.White)
                ) {
                    Icon(
                        icon,
                        contentDescription = "",
                        tint = Color.Unspecified,
                        modifier = Modifier.padding(8.dp)
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))

                Text(
                    text = mainText,
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
            Icon(
                Icons.Default.ArrowRight,
                contentDescription = "",
                modifier = Modifier.size(16.dp)
            )

        }
    }
}
