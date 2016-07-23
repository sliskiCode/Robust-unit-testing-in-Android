package com.code.sliski.userinfoscreen.ui

import android.os.Bundle

interface UserInfoFragmentMVP {

    interface View {
        fun addPostListFragment()
        fun addPostDetailsFragment()
    }

    interface Presenter {
        fun buildView(savedInstanceState: Bundle?, isTablet: Boolean)
        fun attachView(view: View)
        fun detachView()
    }
}