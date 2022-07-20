package com.example.capstoneproject2.presentation.Authentication.presentation.LoginScreen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.capstoneproject2.R

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