package com.example.capstoneproject2.components

import androidx.compose.runtime.Composable
import com.example.capstoneproject2.components.icons.SearchIcon
import com.example.capstoneproject2.components.icons.ShoppingCartIcon

@Composable
fun TopBarActions(
    onSearchIconClick: () -> Unit,
    onShoppingCartIconClick: () -> Unit
) {
    SearchIcon(
        onSearchIconClick = onSearchIconClick
    )
    ShoppingCartIcon(
        onShoppingCartIconClick = onShoppingCartIconClick
    )
}