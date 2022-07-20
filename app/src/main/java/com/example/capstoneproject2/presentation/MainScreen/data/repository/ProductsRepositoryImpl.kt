package com.example.capstoneproject2.presentation.MainScreen.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.capstoneproject2.presentation.MainScreen.domain.repository.ProductsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@ExperimentalCoroutinesApi
class ProductsRepositoryImpl @Inject constructor(
    private val source: FirestorePagingSource,
    private val config: PagingConfig
): ProductsRepository {
    override fun getProducts()= Pager(
        config = config
    ) {
        source
    }.flow
}