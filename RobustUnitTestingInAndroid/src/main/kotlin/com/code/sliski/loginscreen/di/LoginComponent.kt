package com.code.sliski.loginscreen.di

import com.code.sliski.AppComponent
import com.code.sliski.loginscreen.ui.LoginFragment
import dagger.Component

@LoginScope
@Component(modules = arrayOf(LoginModule::class),
           dependencies = arrayOf(AppComponent::class))
interface LoginComponent {

    fun inject(view: LoginFragment)
}