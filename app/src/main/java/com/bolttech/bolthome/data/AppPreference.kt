package com.bolttech.bolthome.data

import android.content.Context
import android.content.SharedPreferences
import com.bolttech.bolthome.R
import javax.inject.Singleton

@Singleton
class AppPreference constructor(context : Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    fun getStoredTag(key: String): String? =
        prefs.getString(key, "")

    fun setStoredTag(key:String, token: String) {
        prefs.edit().putString(key, token).apply()

    }
}