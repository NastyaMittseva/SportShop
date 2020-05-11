package com.example.shop

import android.app.Application
import com.example.shop.di.AppComponent
import com.example.shop.di.DaggerAppComponent

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .context(this)
            .build()
    }

    companion object{
        lateinit var appComponent: AppComponent
    }
}