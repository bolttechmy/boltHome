package com.bolttech.bolthome.component

import android.app.Activity
import android.content.Context
import androidx.navigation.NavController

fun navigate(navController: NavController, route : String, mContext:Context){
    (mContext as Activity).runOnUiThread {
        navController.navigate(route)
    }
}