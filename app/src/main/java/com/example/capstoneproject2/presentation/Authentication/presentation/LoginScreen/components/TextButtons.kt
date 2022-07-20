package com.example.capstoneproject2.presentation.Authentication.presentation.LoginScreen.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextButtons(text: String){
    TextButton(
        onClick = {},
        contentPadding = PaddingValues(vertical = 0.dp)
    ) {
        Text(
            text = text,
            color = Color.Gray,
            fontSize = 12.sp,
            modifier = Modifier.padding(top = 26.dp)
        )
    }
}
