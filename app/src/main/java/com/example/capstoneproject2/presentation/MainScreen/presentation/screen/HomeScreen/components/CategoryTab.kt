package com.example.capstoneproject2.presentation.MainScreen.presentation.screen.HomeScreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CategoryTab(){
    var state by remember { mutableStateOf(0) }
    val titles = listOf(
        "All",
        "Accessories",
        "Cages",
        "Food",
        "Grooming",
        "Medicine",
        "Nestbox",
        "Toys",
        "Others"
    )
    Column {
        ScrollableTabRow(selectedTabIndex = state, contentColor = Color.Black,  edgePadding = 8.dp, backgroundColor = Color.White) {
            titles.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = state == index,
                    onClick = { state = index }
                )
            }
        }
    }
}