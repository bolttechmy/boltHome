package com.bolttech.bolthome.di.modules

import com.bolttech.bolthome.BuildConfig
import com.bolttech.bolthome.data.AppPreference
import com.bolttech.bolthome.network.IApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun getRetrofit(appPreference: AppPreference): IApi {
        val logging = HttpLoggingInterceptor()
        logging.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BASIC else HttpLoggingInterceptor.Level.NONE

        val accessToken = appPreference.getStoredTag("TOKEN")
        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request()
                    .newBuilder()
                    .addHeader(
                        "Authorization",
                        "Bearer $accessToken"
                    )
                    .build()
                val response = chain.proceed(request)
                response
            }.addInterceptor(logging)
            .build()

        return Retrofit.Builder()
//            .baseUrl("https://api.home-protec.app.dev.device.bolttech.asia/v1/")
            .baseUrl("https://xt12p902nb.execute-api.ap-southeast-1.amazonaws.com/dev/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(IApi::class.java)
    }
}