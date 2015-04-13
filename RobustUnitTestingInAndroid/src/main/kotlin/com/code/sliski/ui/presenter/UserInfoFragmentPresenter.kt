package com.code.sliski.ui.presenter

import android.os.Bundle
import com.code.sliski.ui.fragment.UserInfoFragmentView

public trait UserInfoFragmentPresenter {
    public fun addFragments(savedInstanceState: Bundle?, isTablet: Boolean)
    public fun setView(view: UserInfoFragmentView)
    public fun getView() : UserInfoFragmentView?
}