package com.code.sliski.loginscreen.ui

import com.code.sliski.extension.isPositiveNumber
import com.code.sliski.preference.PreferencesManager

class LoginFragmentPresenter(private var preferencesManager: PreferencesManager) : LoginFragmentMVP.Presenter {

    private var view: LoginFragmentMVP.View? = null

    override fun attemptLogin(userId: String) =
            if (userId.isPositiveNumber()) {
                preferencesManager.saveUserId(userId.toLong())
                view?.goToUserInfo()
            } else {
                view?.showBadFormatInfo()
            }

    override fun attachView(view: LoginFragmentMVP.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }
}