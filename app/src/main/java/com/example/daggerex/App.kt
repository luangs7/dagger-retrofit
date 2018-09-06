package com.example.daggerex

import android.app.Application

class App : Application() {

    lateinit var netComponent: NetComponent

    override fun onCreate() {
        super.onCreate()

        netComponent = DaggerNetComponent.builder()
                .applicationModule(ApplicationModule(this))
                .netModule(NetModule("https://jsonplaceholder.typicode.com/"))
                .build()
    }
}