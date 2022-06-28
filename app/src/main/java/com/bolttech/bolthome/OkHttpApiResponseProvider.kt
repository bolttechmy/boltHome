package com.bolttech.bolthome
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class OkHttpApiResponseProvider {
    fun getResponseFromApiThroughOkHttpLib() : String{
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://reqres.in/api/users/2")
            .build();

        val response: Response = client.newCall(request).execute()
        return response.body?.string() ?: ""
    }
}