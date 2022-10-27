package com.example.capstoneproject2.presentation.MainScreen.presentation.screen.HomeScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.capstoneproject2.presentation.MainScreen.domain.model.Product
import com.example.capstoneproject2.presentation.MainScreen.presentation.screen.HomeScreen.HomeScreenViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

@Composable
@InternalCoroutinesApi
@ExperimentalPagingApi
@ExperimentalCoroutinesApi
fun ProductCard (
    onItemClick: () -> Unit,
    viewModel: HomeScreenViewModel = hiltViewModel()
){
    val products = viewModel.products.collectAsLazyPagingItems()

    LazyColumn {
        items(items = products) { product ->
            if (product != null) {
                ProductItem(
                    product,
                    onItemClick = {
                        onItemClick ()
                    }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
@InternalCoroutinesApi
@ExperimentalCoroutinesApi
fun ProductItem(
    product: Product,
    onItemClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Gray, shape = RoundedCornerShape(24.dp))
            //.border(BorderStroke(2.dp, Color.Black))
            .height(200.dp)
            .clickable { onItemClick() },
        contentAlignment = Alignment.Center
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

           /* product.photoUrl?.let { painterResource(it.toInt()) }?.let {
                Image(
                    painter = it,
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(0.4f)
                )
            }*/
            val dataImage = product.photoUrl

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(dataImage)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    //.clip(CircleShape)
                    //.width(96.dp)
                    //.height(96.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {

                product.name?.let { name->
                    Text(
                        text = name,
                        color = Color.DarkGray,
                        style = MaterialTheme.typography.h6,
                        fontSize = 25.sp
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))
                product.description?.let {
                    Text(
                        text = it,
                        color = Color(0xFFB1B1B1)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    product.price?.let{
                        Text(
                            text = "₱ " + it.toString(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "Add to Cart",
                        fontSize = 14.sp,
                        modifier = Modifier
                            .background(Color.Green, shape = CircleShape)
                            .padding(top = 4.dp, bottom = 4.dp, start = 12.dp, end = 12.dp),
                        color = Color.Black
                    )
                }
            }

        }
    }
}