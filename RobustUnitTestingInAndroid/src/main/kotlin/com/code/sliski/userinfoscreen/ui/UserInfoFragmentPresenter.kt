package com.code.sliski.userinfoscreen.ui

import android.os.Bundle
import com.code.sliski.extension.ifNull

class UserInfoFragmentPresenter : UserInfoFragmentMVP.Presenter {

    private var view: UserInfoFragmentMVP.View? = null

    override fun present(savedInstanceState: Bundle?,
                         isTablet: Boolean) =
            savedInstanceState.ifNull {
                view?.showPostListScreen()
                if (isTablet) {
                    view?.showPostDetailsScreen()
                }
            }

    override fun attach(view: UserInfoFragmentMVP.View) {
        this.view = view
    }

    override fun detach() {
        this.view = null
    }
}