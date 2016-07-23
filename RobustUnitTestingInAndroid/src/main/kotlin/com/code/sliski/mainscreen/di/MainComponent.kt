package com.code.sliski.mainscreen.di

import com.code.sliski.AppComponent
import com.code.sliski.mainscreen.ui.MainActivity
import dagger.Component

@MainScope
@Component(modules = arrayOf(MainModule::class),
           dependencies = arrayOf(AppComponent::class))
interface MainComponent {

    fun inject(view: MainActivity)
}