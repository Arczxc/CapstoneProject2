package com.example.capstoneproject2.presentation.MainScreen.presentation.screen.ProfileScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.capstoneproject2.presentation.MainScreen.presentation.screen.ProfileScreen.ProfileViewModel
import com.example.capstoneproject2.theme.Shapes

@Composable
fun SupportOptions(viewModel: ProfileViewModel = hiltViewModel()) {
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