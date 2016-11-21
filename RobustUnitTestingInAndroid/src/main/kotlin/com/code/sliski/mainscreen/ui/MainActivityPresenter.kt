package com.code.sliski.mainscreen.ui

import android.os.Bundle
import com.code.sliski.extension.ifNull
import com.code.sliski.mvp.Presenter

class MainActivityPresenter(private val userId: Long) : Presenter<View>() {

    fun present(savedInstanceState: Bundle?) =
            savedInstanceState.ifNull {
                if (userId == 0L)
                    view?.showLoginScreen()
                else
                    view?.showUserInfoScreen()
            }
}