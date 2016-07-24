package com.code.sliski.mainscreen.ui

import android.os.Bundle
import com.code.sliski.extension.ifNull

class MainActivityPresenter(private var userId: Long) : MainActivityMVP.Presenter {

    private var view: MainActivityMVP.View? = null

    override fun buildView(savedInstanceState: Bundle?) =
            savedInstanceState.ifNull {
                if (userId == 0L)
                    view?.addLoginFragment()
                else
                    view?.addUserInfoFragment()
            }

    override fun attachView(view: MainActivityMVP.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }
}