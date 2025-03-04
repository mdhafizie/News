package com.news.android.presentation.ui

import android.net.Uri
import android.webkit.*
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun CustomWebView(url: String) {
    val decodedUrl = Uri.decode(url) // Decode the URL
    LocalContext.current
    var webView: WebView? by remember { mutableStateOf(null) }
    var progress by remember { mutableIntStateOf(0) }

    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(
            factory = { ctx ->
                WebView(ctx).apply {
                    settings.javaScriptEnabled = true
                    settings.domStorageEnabled = true
                    settings.useWideViewPort = true
                    settings.loadWithOverviewMode = true
                    settings.allowContentAccess = true
                    settings.allowFileAccess = true
                    settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW

                    webViewClient = object : WebViewClient() {
                        override fun onPageFinished(view: WebView?, url: String?) {
                            super.onPageFinished(view, url)
                            progress = 100 // Hide progress when page loads
                        }
                    }

                    webChromeClient = object : WebChromeClient() {
                        override fun onProgressChanged(view: WebView?, newProgress: Int) {
                            progress = newProgress
                        }
                    }

                    loadUrl(decodedUrl) // Load the decoded URL
                    webView = this
                }
            },
            modifier = Modifier.fillMaxSize()
        )

        // Show progress bar while loading
        if (progress in 1..99) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(50.dp)
                    .align(androidx.compose.ui.Alignment.Center),
            )
        }
    }

    // Handle back press for WebView navigation
    BackHandler(enabled = webView?.canGoBack() == true) {
        webView?.goBack()
    }
}
