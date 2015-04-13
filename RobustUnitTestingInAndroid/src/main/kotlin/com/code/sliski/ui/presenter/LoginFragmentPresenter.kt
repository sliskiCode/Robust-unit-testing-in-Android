package com.code.sliski.ui.presenter

import com.code.sliski.ui.fragment.LoginFragmentView

public trait LoginFragmentPresenter {
    fun login(userId: String)
    public fun setView(view: LoginFragmentView)
    public fun getView() : LoginFragmentView?
}