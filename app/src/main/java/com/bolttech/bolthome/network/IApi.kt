package com.bolttech.bolthome.network

import com.bolttech.bolthome.data.Device
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IApi {

    @GET("users")
    suspend fun getUsers(): Device
}