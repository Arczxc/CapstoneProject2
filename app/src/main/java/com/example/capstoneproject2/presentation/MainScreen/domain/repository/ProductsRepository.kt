package com.example.capstoneproject2.presentation.MainScreen.domain.repository

import androidx.paging.PagingData
import com.example.capstoneproject2.presentation.MainScreen.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    fun getProducts(): Flow<PagingData<Product>>
}