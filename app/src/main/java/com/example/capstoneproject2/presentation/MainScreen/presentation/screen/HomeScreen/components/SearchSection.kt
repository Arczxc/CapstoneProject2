package com.example.capstoneproject2.presentation.MainScreen.presentation.screen.HomeScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun SearchSection() {
    var searchText = remember {
        mutableStateOf("")
    }
    OutlinedTextField(
        value = searchText.value,
        onValueChange = {
            searchText.value = it
        },
        placeholder = {
            Text(text = "Search here...", color = Color(0xFFCCCCCC))
        },
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF4F4F4), shape = CircleShape),
        shape = CircleShape,
        singleLine = true,
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        }
    )
}