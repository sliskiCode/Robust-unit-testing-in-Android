package com.code.sliski.ui.presenter

import android.os.Bundle
import com.code.sliski.ui.fragment.UserInfoFragmentView

public class UserInfoFragmentPresenterImpl : UserInfoFragmentPresenter {

    private var userInfoFragmentView: UserInfoFragmentView? = null

    override fun addFragments(savedInstanceState: Bundle?, isTablet: Boolean) {
        if (savedInstanceState == null) {
            userInfoFragmentView?.addPostListFragment()
            if (isTablet) {
                userInfoFragmentView?.addPostDetailsFragment()
            }
        }
    }

    override fun setView(view: UserInfoFragmentView) {
        userInfoFragmentView = view
    }

    override fun getView(): UserInfoFragmentView? {
        return userInfoFragmentView
    }
}