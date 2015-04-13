package com.code.sliski.ui.presenter

import android.os.Bundle
import com.code.sliski.ui.activity.MainActivityView

public trait MainActivityPresenter {
    public fun addFragment(savedInstanceState: Bundle?)
    public fun setView(view: MainActivityView)
    public fun getView() : MainActivityView?
}