package com.example.daggerex

import dagger.Component

@Component(modules = arrayOf(ApplicationModule::class, NetModule::class))
interface NetComponent {
    fun injectMainActivity(mainActivity: MainActivity)
}