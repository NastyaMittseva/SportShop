package com.example.shop.di.modules

import com.example.shop.domain.MainApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class MainApiModule {

    @Provides
    @Singleton
    fun provideMainApi(): MainApi = Retrofit.Builder()
        .baseUrl("http://46.17.97.59:5000")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MainApi::class.java)
}