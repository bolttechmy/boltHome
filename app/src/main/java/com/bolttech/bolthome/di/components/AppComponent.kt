package com.bolttech.bolthome.di.components

import com.bolttech.bolthome.di.modules.AppModule
import com.bolttech.bolthome.di.modules.NetworkModule
import com.bolttech.bolthome.ui.DashBoardActivity
import com.bolttech.bolthome.viewmodels.DashBoardViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class), (NetworkModule::class)])
interface AppComponent {
    fun inject(auth: DashBoardActivity)
    fun inject(networkModule: NetworkModule)
    fun inject(userViewModel: DashBoardViewModel)

}