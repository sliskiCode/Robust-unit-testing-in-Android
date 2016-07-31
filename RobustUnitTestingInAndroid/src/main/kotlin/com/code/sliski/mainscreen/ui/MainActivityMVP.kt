package com.code.sliski.mainscreen.ui

import android.os.Bundle

interface MainActivityMVP {

    interface View {
        fun showLoginScreen()
        fun showUserInfoScreen()
    }

    interface Presenter {
        fun present(savedInstanceState: Bundle?)
        fun attach(view: View)
        fun detach()
    }
}