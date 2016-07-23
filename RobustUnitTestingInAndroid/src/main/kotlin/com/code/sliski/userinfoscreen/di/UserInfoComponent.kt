package com.code.sliski.userinfoscreen.di

import com.code.sliski.userinfoscreen.ui.UserInfoFragment

import dagger.Component

@UserInfoScope
@Component(modules = arrayOf(UserInfoModule::class))
interface UserInfoComponent {

    fun inject(view: UserInfoFragment)
}