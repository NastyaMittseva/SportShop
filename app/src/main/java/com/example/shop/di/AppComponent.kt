package com.example.shop.di

import android.content.Context
import com.example.shop.di.modules.MainApiModule
import com.example.shop.di.modules.PreferencesModule
import com.example.shop.ui.CatalogActivity
import com.example.shop.ui.CategoryActivity
import com.example.shop.ui.ProductActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        PreferencesModule::class,
        MainApiModule::class
    ]
)

@Singleton
interface AppComponent {
    @Component.Builder
    interface Builder{
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent
    }

    fun inject(activity: CatalogActivity)
    fun inject(activity: CategoryActivity)
    fun inject(activity: ProductActivity)
}