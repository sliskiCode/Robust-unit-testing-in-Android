package com.code.sliski.mainscreen.di

import com.code.sliski.mainscreen.ui.MainActivityMVP
import com.code.sliski.mainscreen.ui.MainActivityPresenter
import com.code.sliski.preference.PreferencesManager
import com.code.sliski.preference.PreferencesManagerImpl
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides @MainScope
    fun mainActivityPresenter(preferencesManager: PreferencesManager): MainActivityMVP.Presenter =
            MainActivityPresenter(preferencesManager.getUserId())
}