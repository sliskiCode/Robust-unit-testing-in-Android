package com.code.sliski.userinfoscreen.ui

import android.os.Bundle

class UserInfoFragmentPresenterImpl : UserInfoFragmentMVP.Presenter {

    private var view: UserInfoFragmentMVP.View? = null

    override fun buildView(savedInstanceState: Bundle?, isTablet: Boolean) {
        if (savedInstanceState == null) {
            view?.addPostListFragment()
            if (isTablet) {
                view?.addPostDetailsFragment()
            }
        }
    }

    override fun attachView(view: UserInfoFragmentMVP.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }
}