package com.code.sliski.mainscreen.ui

import android.os.Bundle
import com.code.sliski.UserRole
import com.code.sliski.extension.ifNull
import com.code.sliski.mvp.Presenter

class MainActivityPresenter(private val userId: Long,
                            private val userRole: UserRole,
                            private val userRoleDictionary: Map<UserRole, String>) : Presenter<View>() {

    private val isLoggedIn by lazy { userId != 0L }

    fun present(state: Bundle?) = state.ifNull {
        showScreen()
        showMessage()
    }

    private fun showScreen() = if (isLoggedIn) {
        view?.showUserInfoScreen()
    } else {
        view?.showLoginScreen()
    }

    private fun showMessage() {
        if (isLoggedIn) {
            view?.displayMessage(userRoleDictionary[userRole]!!)
        }
    }
}