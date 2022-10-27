package com.example.capstoneproject2.presentation.MainScreen.presentation.screen.HomeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.ExperimentalPagingApi
import com.example.capstoneproject2.presentation.MainScreen.presentation.screen.HomeScreen.components.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

@Composable
@InternalCoroutinesApi
@ExperimentalPagingApi
@ExperimentalCoroutinesApi
fun HomeScreen(
    onClick: () -> Unit,
){
    Box(modifier = Modifier.fillMaxSize())
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(bottom = 50.dp)
        ){

            TopAppBar()
            TitleSection()
            Spacer(modifier = Modifier.height(16.dp))
            SearchSection()
            Spacer(modifier = Modifier.height(16.dp))
            CategoryTab()
            ProductCard(onClick)
        }
    }
}