package com.code.sliski.loginscreen.ui

import com.code.sliski.extension.isPositiveNumber
import com.code.sliski.mvp.Presenter
import com.code.sliski.preference.PreferencesManager

class LoginFragmentPresenter(private val preferencesManager: PreferencesManager) : Presenter<View>() {

    fun present(userId: String) = if (userId.isPositiveNumber()) {
        preferencesManager.saveUserId(userId.toLong())
        view?.showUserInfoScreen()
    } else {
        view?.showBadFormatInfo()
    }
}