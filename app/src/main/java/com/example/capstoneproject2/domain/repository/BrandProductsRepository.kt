package com.example.capstoneproject2.domain.repository

import kotlinx.coroutines.flow.Flow

interface BrandProductsRepository {
    fun getBrandProductsFromFirestore(brand: String): Flow<ProductPagingData>
}