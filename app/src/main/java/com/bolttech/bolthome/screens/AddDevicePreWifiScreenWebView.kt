package com.bolttech.bolthome.screens

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.bolttech.bolthome.component.JavaScriptInterface
import com.bolttech.bolthome.component.Page

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun AddDevicePreWifiScreenWebView(navController: NavController) {
    val mUrl = "http://bolthome.com.s3-website-ap-southeast-1.amazonaws.com/"
    Page {
        AndroidView(factory = {
            WebView(it).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true
                addJavascriptInterface(
                    JavaScriptInterface(
                        context, navController = navController
                    ), "boltechFunctions")
                loadUrl(mUrl)
            }
        }, update = {
            it.loadUrl(mUrl)
        })
    }
}