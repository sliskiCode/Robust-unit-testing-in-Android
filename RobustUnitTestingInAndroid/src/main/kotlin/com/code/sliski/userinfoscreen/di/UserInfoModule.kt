package com.code.sliski.userinfoscreen.di

import com.code.sliski.userinfoscreen.ui.UserInfoFragmentPresenter
import dagger.Module
import dagger.Provides

@Module
class UserInfoModule {

    @Provides @UserInfoScope
    fun userInfoFragmentPresenter() = UserInfoFragmentPresenter()
}