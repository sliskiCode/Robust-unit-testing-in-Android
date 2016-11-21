package com.code.sliski.userinfoscreen.ui

import android.os.Bundle
import com.code.sliski.extension.ifNull
import com.code.sliski.mvp.Presenter

class UserInfoFragmentPresenter : Presenter<View>() {

    fun present(savedInstanceState: Bundle?, isTablet: Boolean) =
            savedInstanceState.ifNull {
                view?.showPostListScreen()
                if (isTablet) {
                    view?.showPostDetailsScreen()
                }
            }
}