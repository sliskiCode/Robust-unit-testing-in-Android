package com.code.sliski.userinfoscreen.ui

import android.os.Bundle
import com.code.sliski.extension.ifNull

class UserInfoFragmentPresenter : UserInfoFragmentMVP.Presenter {

    private var view: UserInfoFragmentMVP.View? = null

    override fun buildView(savedInstanceState: Bundle?,
                           isTablet: Boolean) =
            savedInstanceState.ifNull {
                view?.addPostListFragment()
                if (isTablet) {
                    view?.addPostDetailsFragment()
                }
            }

    override fun attachView(view: UserInfoFragmentMVP.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }
}