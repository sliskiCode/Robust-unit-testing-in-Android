package com.code.sliski.loginscreen.ui

interface LoginFragmentMVP {

    interface View {
        fun showBadFormatInfo()
        fun showUserInfoScreen()
    }

    interface Presenter {
        fun present(userId: String): Unit?
        fun attach(view: View)
        fun detach()
    }
}