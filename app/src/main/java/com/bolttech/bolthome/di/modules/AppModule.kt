package com.bolttech.bolthome.di.modules

import android.content.Context
import com.bolttech.bolthome.data.AppPreference
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule( val app: Context) {
    @Provides
    @Singleton
    fun provideContext(): Context {
        return app

    }

    @Provides
    @Singleton
    fun providePreference(): AppPreference {
        return AppPreference(app)
    }
}
