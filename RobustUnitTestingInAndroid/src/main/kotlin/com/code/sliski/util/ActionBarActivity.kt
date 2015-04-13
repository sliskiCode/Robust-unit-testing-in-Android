package com.code.sliski.util

import android.support.v4.app.Fragment
import android.support.v7.app.ActionBarActivity

public fun ActionBarActivity.addFragment(layoutId: Int, fragment: Fragment) {
    getSupportFragmentManager().
            beginTransaction().
            add(layoutId, fragment)
            .commit()
}