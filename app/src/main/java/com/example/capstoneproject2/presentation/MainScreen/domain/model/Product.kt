package com.example.capstoneproject2.presentation.MainScreen.domain.model

data class Product(
    var id: String? = null,
    var name: String? = null,
    var description: String? = null,
    var price: Int? = null,
    var group: Int? = null,
    var rating: Int? = null,
    var photoUrl: String? = null,
)
