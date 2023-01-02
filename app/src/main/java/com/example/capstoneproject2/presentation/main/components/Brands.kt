package com.example.capstoneproject2.presentation.main.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.capstoneproject2.components.ProgressBar
import com.example.capstoneproject2.core.Utils.Companion.print
import com.example.capstoneproject2.domain.model.Response.*
import com.example.capstoneproject2.domain.repository.Brands
import com.example.capstoneproject2.presentation.main.MainViewModel

@Composable
@ExperimentalMaterial3Api
fun Brands(
    viewModel: MainViewModel = hiltViewModel(),
    brandsContent: @Composable (brands: Brands) -> Unit
) {
    when(val brandsResponse = viewModel.brandsResponse) {
        is Loading -> ProgressBar()
        is Success -> brandsResponse.data?.let { brands ->
            brandsContent(brands)
        }
        is Failure -> LaunchedEffect(Unit) {
            print(brandsResponse.e)
        }
    }
}