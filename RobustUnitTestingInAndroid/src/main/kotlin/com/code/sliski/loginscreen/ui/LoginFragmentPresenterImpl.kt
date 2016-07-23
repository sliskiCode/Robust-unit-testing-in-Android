package com.code.sliski.loginscreen.ui

import com.code.sliski.extension.isPositiveNumber
import com.code.sliski.preference.PreferencesManager

class LoginFragmentPresenterImpl(private var preferencesManager: PreferencesManager) : LoginFragmentMVP.Presenter {

    private var view: LoginFragmentMVP.View? = null

    override fun attemptLogin(userId: String) =
            if (userId.isPositiveNumber().not()) {
                view?.showBadFormatInfo()
            } else {
                preferencesManager.userId().put(userId.toLong()).commit()
                view?.goToUserInfo()
            }

    override fun attachView(view: LoginFragmentMVP.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }
}