package com.code.sliski.mainscreen.ui

import android.os.Bundle

interface MainActivityMVP {

    interface View {
        fun addLoginFragment()
        fun addUserInfoFragment()
    }

    interface Presenter {
        fun buildView(savedInstanceState: Bundle?)
        fun attachView(view: View)
        fun detachView()
    }
}