package com.bolttech.bolthome.di.modules

import com.bolttech.bolthome.repositories.DashBoardRepository
import com.bolttech.bolthome.network.IApi
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun getCalRepository(iApi: IApi) = DashBoardRepository(iApi)
}