package com.marc.dinino.sandboxxsample.viewmodel

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView

object Article {
    @Composable
    fun compose(url: String) {
        Scaffold(
            topBar = { TopAppBar(title = { Text("GFG | WebView", color = Color.White) }, backgroundColor = Color(0xff0f9d58)) },
            content = { content(url) }
        )
    }

    @Composable
    private fun content(url: String){

        // Adding a WebView inside AndroidView
        // with layout as full screen
        AndroidView(factory = {
            WebView(it).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                webViewClient = WebViewClient()
                loadUrl(url)
            }
        }, update = {
            it.loadUrl(url)
        })
}








}