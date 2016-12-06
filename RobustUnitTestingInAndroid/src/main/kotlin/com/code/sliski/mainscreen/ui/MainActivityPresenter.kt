package com.code.sliski.mainscreen.ui

import android.os.Bundle
import com.code.sliski.extension.ifNull
import com.code.sliski.mvp.Presenter

class MainActivityPresenter(private val userId: Long) : Presenter<View>() {

    fun present(state: Bundle?) = state.ifNull {
        showScreen()
    }

    private fun showScreen() = if (isLoggedIn()) {
        view?.showUserInfoScreen()
    } else {
        view?.showLoginScreen()
    }

    private fun isLoggedIn() = userId != 0L
}