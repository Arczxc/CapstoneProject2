package com.example.capstoneproject2.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import com.example.capstoneproject2.domain.model.Product

typealias ProductPagingData = PagingData<Product>

interface ProductSearchRepository {
    fun getProductsFromFirestore(): Flow<ProductPagingData>

    fun getSearchProductsFromFirestore(searchText: String): Flow<ProductPagingData>
}