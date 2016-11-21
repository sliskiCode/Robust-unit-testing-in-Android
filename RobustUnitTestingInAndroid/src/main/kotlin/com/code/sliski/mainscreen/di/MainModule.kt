package com.code.sliski.mainscreen.di

import com.code.sliski.mainscreen.ui.MainActivityPresenter
import com.code.sliski.preference.PreferencesManager
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides @MainScope
    fun mainActivityPresenter(preferencesManager: PreferencesManager): MainActivityPresenter =
            MainActivityPresenter(preferencesManager.getUserId())
}