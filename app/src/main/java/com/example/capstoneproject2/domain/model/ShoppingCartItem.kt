package com.example.capstoneproject2.domain.model

import com.google.firebase.firestore.ServerTimestamp
import java.util.*

data class ShoppingCartItem (
    @ServerTimestamp
    val additionDate: Date? = null,
    val id: String? = null,
    val name: String? = null,
    val price: Int? = null,
    val quantity: Int? = null,
    val thumb: String? = null // eto ata
)