package com.code.sliski.mainscreen.ui

import android.os.Bundle
import com.code.sliski.extension.ifNull

class MainActivityPresenter(private var userId: Long) : MainActivityMVP.Presenter {

    private var view: MainActivityMVP.View? = null

    override fun present(savedInstanceState: Bundle?) =
            savedInstanceState.ifNull {
                if (userId == 0L)
                    view?.showLoginScreen()
                else
                    view?.showUserInfoScreen()
            }

    override fun attach(view: MainActivityMVP.View) {
        this.view = view
    }

    override fun detach() {
        this.view = null
    }
}