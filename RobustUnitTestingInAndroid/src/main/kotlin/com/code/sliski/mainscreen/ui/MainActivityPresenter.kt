package com.code.sliski.mainscreen.ui

import android.os.Bundle
import com.code.sliski.preference.PreferencesManager

class MainActivityPresenter(private var preferencesManager: PreferencesManager) : MainActivityMVP.Presenter {

    private var view: MainActivityMVP.View? = null

    override fun buildView(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            val userId = preferencesManager.userId().getOr(0L)
            if (userId == 0L) {
                view?.addLoginFragment()
            } else {
                view?.addUserInfoFragment()
            }
        }
    }

    override fun attachView(view: MainActivityMVP.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }
}