package com.bolttech.bolthome.repositories

import com.bolttech.bolthome.network.IApi
import javax.inject.Inject

class DashBoardRepository @Inject constructor( var api: IApi) {
    suspend fun getDevice() = api.getUsers()
}