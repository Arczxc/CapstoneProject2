package com.example.capstoneproject2.navigation


import com.example.capstoneproject2.core.AppConstants.AUTH_SCREEN
import com.example.capstoneproject2.core.AppConstants.BRAND_PRODUCTS_SCREEN
import com.example.capstoneproject2.core.AppConstants.MAIN_SCREEN
import com.example.capstoneproject2.core.AppConstants.PRODUCTS_ORDER_SCREEN
import com.example.capstoneproject2.core.AppConstants.PRODUCT_DETAILS_SCREEN
import com.example.capstoneproject2.core.AppConstants.PRODUCT_SEARCH_SCREEN
import com.example.capstoneproject2.core.AppConstants.SHOPPING_CART_SCREEN
import com.example.capstoneproject2.core.AppConstants.THANK_YOU_SCREEN

sealed class Screen(val route: String) {
    object AuthScreen: Screen(AUTH_SCREEN)
    object MainScreen: Screen(MAIN_SCREEN)
    object ProductSearchScreen: Screen(PRODUCT_SEARCH_SCREEN)
    object ShoppingCartScreen: Screen(SHOPPING_CART_SCREEN)
    object BrandProductsScreen: Screen(BRAND_PRODUCTS_SCREEN)
    object ProductDetailsScreen: Screen(PRODUCT_DETAILS_SCREEN)
    object ProductsOrderScreen: Screen(PRODUCTS_ORDER_SCREEN)
    object ThankYouScreen: Screen(THANK_YOU_SCREEN)
}