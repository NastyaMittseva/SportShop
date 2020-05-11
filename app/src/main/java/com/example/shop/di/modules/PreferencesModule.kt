package com.example.shop.di.modules

import android.content.Context
import android.content.SharedPreferences
import com.example.shop.data.ViewedProductDaoImpl
import com.example.shop.domain.ViewedProductDao
import dagger.Module
import dagger.Provides

@Module
class PreferencesModule {

    @Provides
    fun providePreferences(context: Context): SharedPreferences =
        context.getSharedPreferences("data", Context.MODE_PRIVATE)

    @Provides
    fun provideViewedProduct(preferences: SharedPreferences) : ViewedProductDao =
        ViewedProductDaoImpl(preferences)

}