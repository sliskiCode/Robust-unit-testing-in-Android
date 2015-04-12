package com.code.sliski.ui.presenter

import com.code.sliski.preference.PreferencesManager
import com.code.sliski.ui.fragment.LoginFragmentView

public class LoginFragmentPresenterImpl : LoginFragmentPresenter {

    var loginFragmentView: LoginFragmentView? = null
    var preferencesManager: PreferencesManager? = null

    override public fun login(userId: String) {
        try {
            val userIdToLong = userId.toLong()
            preferencesManager
                    ?.userId()
                    ?.put(userIdToLong)
                    ?.commit()
            loginFragmentView?.replaceWithUserInfoFragment()
        } catch(e : NumberFormatException) {
            loginFragmentView?.showBadFormatInfo()
        }
    }
}