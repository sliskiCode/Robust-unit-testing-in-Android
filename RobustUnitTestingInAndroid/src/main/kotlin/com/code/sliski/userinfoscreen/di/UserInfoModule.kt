package com.code.sliski.userinfoscreen.di

import com.code.sliski.userinfoscreen.ui.UserInfoFragmentMVP
import com.code.sliski.userinfoscreen.ui.UserInfoFragmentPresenterImpl
import dagger.Module
import dagger.Provides

@Module
class UserInfoModule {

    @Provides @UserInfoScope
    fun userInfoFragmentPresenter(): UserInfoFragmentMVP.Presenter =
            UserInfoFragmentPresenterImpl()
}
