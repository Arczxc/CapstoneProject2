package com.example.capstoneproject2.presentation.MainScreen.domain.use_cases_product

import com.example.capstoneproject2.presentation.MainScreen.domain.repository.ProductsRepository

class GetProduct (
    private val repository: ProductsRepository
) {
    operator fun invoke() = repository.getProducts()
}