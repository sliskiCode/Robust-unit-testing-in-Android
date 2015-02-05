package com.code.sliski.ui.activity

import android.support.v7.app.ActionBarActivity
import android.os.Bundle
import com.code.sliski.ui.R
import com.code.sliski.ui.preference.PrefManager
import android.content.Context
import android.support.v4.app.Fragment
import com.code.sliski.ui.fragment.UserIdFragment
import com.code.sliski.ui.fragment.UserInfoFragment

public class MainActivity : ActionBarActivity() {

    override protected fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            addFragment()
        }
    }

    private fun addFragment() {
        val prefManager = PrefManager(getSharedPreferences(getString(R.string.preferences), Context.MODE_PRIVATE))
        val userId = prefManager.userId().getOr(0L)
        var fragment: Fragment
        if (userId == 0L) {
            fragment = UserIdFragment()
        } else {
            fragment = UserInfoFragment()
        }
        getSupportFragmentManager().
                beginTransaction().
                add(R.id.container, fragment)
                .commit()
    }

    override public fun onBackPressed() {
        val fm = getSupportFragmentManager()
        fm.getFragments().forEach {
            if (it.isVisible()) {
                val childFm = it.getChildFragmentManager()
                if (childFm.getBackStackEntryCount() > 0) {
                    childFm.popBackStack()
                    return
                }
            }
        }
        super.onBackPressed()
    }
}