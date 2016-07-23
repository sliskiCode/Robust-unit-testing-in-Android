package com.code.sliski

import android.content.Context
import com.code.sliski.preference.PreferencesManager
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun applicationContext(): Context
    fun preferencesManager(): PreferencesManager
}