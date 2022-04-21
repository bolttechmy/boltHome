package com.bolttech.bolthome

import android.app.Application
import com.bolttech.bolthome.di.components.AppComponent
import com.bolttech.bolthome.di.components.DaggerAppComponent
import com.bolttech.bolthome.di.modules.AppModule
import com.bolttech.bolthome.di.modules.NetworkModule
import com.google.android.gms.security.ProviderInstaller


class BoltHomeApplication : Application() {
     companion object {
         lateinit var appComponent: AppComponent
     }

     init {

         appComponent = DaggerAppComponent.builder()
             .networkModule(NetworkModule())
             .appModule(AppModule(this))
             .build()
     }

 }

