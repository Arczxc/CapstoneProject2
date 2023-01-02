package com.example.capstoneproject2.domain.repository

import com.example.capstoneproject2.domain.model.ShoppingCartItem

interface ShoppingCartActions {
    suspend fun incrementShoppingCartQuantityInRealtimeDatabase()

    suspend fun incrementShoppingCartItemQuantityInFirestore(itemId: String)

    suspend fun addShoppingCartItemToFirestore(item: ShoppingCartItem)

    fun decrementShoppingCartQuantityInRealtimeDatabase()

    suspend fun decrementShoppingCartItemQuantityInFirestore(itemId: String)
}