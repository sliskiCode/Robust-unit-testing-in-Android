package com.code.sliski.userinfoscreen.ui

import android.os.Bundle

interface UserInfoFragmentMVP {

    interface View {
        fun showPostListScreen()
        fun showPostDetailsScreen()
    }

    interface Presenter {
        fun present(savedInstanceState: Bundle?, isTablet: Boolean)
        fun attach(view: View)
        fun detach()
    }
}