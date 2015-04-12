package com.code.sliski.ui.presenter

import android.os.Bundle
import com.code.sliski.ui.fragment.UserInfoFragmentView

public class UserInfoFragmentPresenterImpl : UserInfoFragmentPresenter {

    var userInfoFragmentView: UserInfoFragmentView? = null

    override fun addFragments(savedInstanceState: Bundle?, isTablet: Boolean) {
        if (savedInstanceState == null) {
            userInfoFragmentView?.addPostListFragment()
            if (isTablet) {
                userInfoFragmentView?.addPostDetailsFragment()
            }
        }
    }
}