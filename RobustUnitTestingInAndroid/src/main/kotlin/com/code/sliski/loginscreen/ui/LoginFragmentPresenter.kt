package com.code.sliski.loginscreen.ui

import com.code.sliski.extension.isPositiveNumber
import com.code.sliski.preference.PreferencesManager

class LoginFragmentPresenter(private var preferencesManager: PreferencesManager) : LoginFragmentMVP.Presenter {

    private var view: LoginFragmentMVP.View? = null

    override fun present(userId: String) =
            if (userId.isPositiveNumber()) {
                preferencesManager.saveUserId(userId.toLong())
                view?.showUserInfoScreen()
            } else {
                view?.showBadFormatInfo()
            }

    override fun attach(view: LoginFragmentMVP.View) {
        this.view = view
    }

    override fun detach() {
        this.view = null
    }
}