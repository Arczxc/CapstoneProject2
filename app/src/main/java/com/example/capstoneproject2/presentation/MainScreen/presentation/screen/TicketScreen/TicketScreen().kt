package com.example.capstoneproject2.presentation.MainScreen.presentation.screen.TicketScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SupportAgent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.capstoneproject2.presentation.MainScreen.presentation.screen.HomeScreen.NextScreen.ProductDetailsScreen

@Composable
fun TicketScreen(onClick: () -> Unit,){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { onClick() }) {
                Icon(
                    Icons.Filled.SupportAgent,
                    contentDescription = "description",
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text("Contact US")
            }
        }
    }
}