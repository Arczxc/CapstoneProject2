package com.example.capstoneproject2.domain.repository

interface ProductsOrderRepository {
    suspend fun getOrderShoppingCartItemsFromFirestore(orderId: String): ShoppingCartItemsResponse
}