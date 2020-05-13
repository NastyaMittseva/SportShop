package com.example.shop.di.modules

import com.example.shop.domain.model.Cart
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CartModule {

    @Provides
    @Singleton
    fun provideCart():Cart = Cart()
}