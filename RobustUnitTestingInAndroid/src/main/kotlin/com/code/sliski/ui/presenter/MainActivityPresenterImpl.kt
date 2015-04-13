package com.code.sliski.ui.presenter

import android.os.Bundle
import com.code.sliski.preference.PreferencesManager
import com.code.sliski.ui.activity.MainActivityView
import javax.inject.Inject

public class MainActivityPresenterImpl : MainActivityPresenter {

    private var mainActivityView: MainActivityView? = null
    var preferencesManager: PreferencesManager? = null

    override fun addFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            val userId = preferencesManager?.userId()?.getOr(0L)
            if (userId == 0L) {
                mainActivityView?.addLoginFragment()
            } else {
                mainActivityView?.addUserInfoFragment()
            }
        }
    }

    override fun setView(view: MainActivityView) {
        mainActivityView = view
    }

    override fun getView(): MainActivityView? {
        return mainActivityView
    }
}