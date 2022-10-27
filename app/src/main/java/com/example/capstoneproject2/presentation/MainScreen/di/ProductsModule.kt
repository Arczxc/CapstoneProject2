package com.example.capstoneproject2.presentation.MainScreen.di

import androidx.paging.PagingConfig
import com.example.capstoneproject2.presentation.MainScreen.data.repository.FirestorePagingSource
import com.example.capstoneproject2.presentation.MainScreen.data.repository.ProductsRepositoryImpl
import com.example.capstoneproject2.presentation.MainScreen.domain.repository.ProductsRepository
import com.example.capstoneproject2.core.Constants.NAME
import com.example.capstoneproject2.core.Constants.PAGE_SIZE
import com.example.capstoneproject2.core.Constants.PRODUCTS
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Module
@ExperimentalCoroutinesApi
@InstallIn(SingletonComponent::class)
object ProductsModule {
    @Provides
    fun provideQueryProductsByName() = Firebase.firestore
        .collection(PRODUCTS)
        .orderBy(NAME, Query.Direction.ASCENDING)
        .limit(PAGE_SIZE)

    @Provides
    fun provideFirestorePagingSource(
        queryProductsByName: Query
    ) = FirestorePagingSource(queryProductsByName)

    @Provides
    fun providePagingConfig() = PagingConfig(
        pageSize = PAGE_SIZE.toInt()
    )

    @Provides
    fun provideProductsRepository(
        source: FirestorePagingSource,
        config: PagingConfig
    ): ProductsRepository = ProductsRepositoryImpl(source, config)

}