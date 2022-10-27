package com.example.capstoneproject2.presentation.MainScreen.presentation.screen.CartScreen

import android.annotation.SuppressLint
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun CartScreen(){


    MainContent()

}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainContent() {
    Scaffold(
        topBar = { TopAppBar(title = { Text("TRACK YOUR ORDER HERE", color = Color.White) }, backgroundColor = Color(0xff0f9d58)) },
        content = { MyContent() }
    )
}

// Creating a composable
// function to create WebView
// Calling this function as
// content in the above function
@Composable
fun MyContent(){

    // Declare a string that contains a url
    val mUrl = "https://www.ninjavan.co/en-ph/tracking"

    // Adding a WebView inside AndroidView
    // with layout as full screen
    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = WebViewClient()
            loadUrl(mUrl)
        }
    }, update = {
        it.loadUrl(mUrl)
    })
}