package com.example.capstoneproject2.presentation.MainScreen.presentation.screen.HomeScreen.NextScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.capstoneproject2.theme.*
import com.example.capstoneproject2.R

//@Preview(showBackground = true)
@Composable
fun ProductDetailsScreen(function: () -> Unit) {
    Box(Modifier.verticalScroll(rememberScrollState())) {
        Column {
            TopBarWithBack(
                title = "Product",
                onBackClick = {

                },
            )
            Column {
                Content()
            }
        }
    }
}

@Composable
fun TopBarWithBack(title: String, onBackClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { onBackClick() }) {
            Icon(
                modifier = Modifier.size(32.dp, 32.dp),
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "",
                tint = black
            )
        }

        Text(
            text = title,
            color = paledark,
            modifier = Modifier.padding(start = 16.dp),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
        )

        Card(
            modifier = Modifier
                .padding(end = 20.dp)
                .width(50.dp),
            elevation = 4.dp,
            shape = RoundedCornerShape(8.dp)
        ) {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Outlined.Favorite,
                    contentDescription = ""
                )

            }
        }

    }

}


@Composable
fun Content() {
    Column() {
        ProductItemsImage()
        ProductContent()
        ProductAbout()
        Spacer(modifier = Modifier.padding(24.dp))
        ProductAddtoCart()

    }
}

@Composable
fun ProductContent() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp)
    ) {
        Column() {
            Text(
                text = "Bird Cages for birds",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = paledark,
            )
            Text(
                text = "Cage",
                fontSize = 14.sp,
                color = texttitlewhite,
            )
        }

        Card(
            modifier = Modifier
                .width(130.dp)
                .height(100.dp),
            elevation = 10.dp
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "₱5000",
                    fontSize = 18.sp,
                    color = paledark,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
            }
        }

    }
}


@Composable
fun ProductItemsImage() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .fillMaxHeight()
        ) {
            Image(
                painter = painterResource(R.drawable.birdcages2),
                contentDescription = "Product Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )
        }
    }
}

@Composable
fun ProductAbout() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)

    ) {

        Box(
            modifier = Modifier
                .size(30.dp)
                .clip(shape = CircleShape)
                .background(orangedark)
        )
        Box(
            modifier = Modifier
                .size(30.dp)
                .clip(shape = CircleShape)
                .background(greendark)
        )
        Box(
            modifier = Modifier
                .size(30.dp)
                .clip(shape = CircleShape)
                .background(orangelight)
        )
    }
    Spacer(modifier = Modifier.padding(5.dp))
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "The purpose of a product description is to supply customers with important information about the features and benefits of the product so they’re compelled to buy",
            fontSize = 18.sp,
            color = texttitlewhite,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ProductAddtoCart() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(addtocart),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Card(
                modifier = Modifier
                    .offset(16.dp, (-40).dp)
                    .fillMaxWidth(0.2f)
                    .height(70.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                IconButton(
                    onClick = { },
                    Modifier.background(paleBlack)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ShoppingCart,
                        contentDescription = "",
                        tint = white
                    )

                }
            }
            ClickableText(
                text = AnnotatedString("+ Add to Cart"),
                Modifier.offset(16.dp, (-30).dp),
                onClick = { offset ->
                }
            )


        }
    }
}
