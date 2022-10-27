package com.example.capstoneproject2.presentation.MainScreen.presentation.screen.HomeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.cachedIn
import com.example.capstoneproject2.presentation.MainScreen.domain.repository.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
@ExperimentalPagingApi
class HomeScreenViewModel @Inject constructor(
    private val repo: ProductsRepository
): ViewModel() {
    val products = repo.getProducts().cachedIn(viewModelScope)
}