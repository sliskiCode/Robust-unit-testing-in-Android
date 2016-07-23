package com.code.sliski.loginscreen.di

import com.code.sliski.loginscreen.ui.LoginFragmentMVP
import com.code.sliski.loginscreen.ui.LoginFragmentPresenterImpl
import com.code.sliski.preference.PreferencesManager
import dagger.Module
import dagger.Provides

@Module
class LoginModule {

    @Provides @LoginScope
    fun loginFragmentPresenter(preferencesManager: PreferencesManager): LoginFragmentMVP.Presenter =
            LoginFragmentPresenterImpl(preferencesManager)
}