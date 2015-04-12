package com.code.sliski.ui.fragment

import android.support.v7.app.ActionBarActivity

public trait LoginFragmentView {
    public fun showBadFormatInfo()
    public fun replaceWithUserInfoFragment()
    public fun onClickGoButton()
    public fun setId(id: String)
}