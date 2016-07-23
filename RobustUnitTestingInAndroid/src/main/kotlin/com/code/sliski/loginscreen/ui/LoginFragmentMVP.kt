package com.code.sliski.loginscreen.ui

interface LoginFragmentMVP {

    interface View {
        fun showBadFormatInfo()
        fun goToUserInfo()
    }

    interface Presenter {
        fun attemptLogin(userId: String): Unit?
        fun attachView(view: View)
        fun detachView()
    }
}