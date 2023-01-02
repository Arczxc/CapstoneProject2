package com.example.capstoneproject2.presentation.shopping_cart.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.capstoneproject2.components.ProgressBar
import com.example.capstoneproject2.core.Utils.Companion.print
import com.example.capstoneproject2.domain.model.Response.*
import com.example.capstoneproject2.domain.repository.ShoppingCartItems
import com.example.capstoneproject2.presentation.shopping_cart.ShoppingCartViewModel

@Composable
fun ShoppingCart(
    viewModel: ShoppingCartViewModel = hiltViewModel(),
    shoppingCartContent: @Composable (items: ShoppingCartItems) -> Unit
) {
    when(val shoppingCartItemsResponse = viewModel.shoppingCartItemsResponse) {
        is Loading -> ProgressBar()
        is Success -> shoppingCartItemsResponse.data?.let { items ->
            shoppingCartContent(items)
        }
        is Failure -> LaunchedEffect(Unit) {
            print(shoppingCartItemsResponse.e)
        }
    }
}