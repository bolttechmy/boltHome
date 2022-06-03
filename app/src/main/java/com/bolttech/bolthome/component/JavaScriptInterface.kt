package com.bolttech.bolthome.component

import android.content.Context
import android.webkit.JavascriptInterface
import androidx.navigation.NavController
import com.bolttech.bolthome.navigation.AppScreens

class JavaScriptInterface(private val mContext: Context, private val navController: NavController) {

    @JavascriptInterface
    fun startWifiScanning() {
        navigate(navController, AppScreens.AddDeviceWifiSelectionScreen.route, mContext)
    }

    @JavascriptInterface
    fun addDeviceManually() {
        navigate(navController, AppScreens.AddDeviceWifiSelectionScreen.route, mContext)
    }
}