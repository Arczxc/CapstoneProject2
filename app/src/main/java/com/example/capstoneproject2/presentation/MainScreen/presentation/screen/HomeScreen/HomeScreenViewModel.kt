package com.example.capstoneproject2.presentation.MainScreen.presentation.screen.HomeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.cachedIn
import com.example.capstoneproject2.presentation.MainScreen.domain.use_cases_product.UseCasesProduct
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
@ExperimentalPagingApi
class HomeScreenViewModel @Inject constructor(
    useCases: UseCasesProduct
): ViewModel() {
    val products = useCases.getProducts().cachedIn(viewModelScope)

}