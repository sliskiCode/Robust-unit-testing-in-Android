package com.code.sliski.ui.activity

import android.support.v7.app.ActionBarActivity
import android.os.Bundle
import com.code.sliski.R
import android.content.Context
import android.support.v4.app.Fragment
import com.code.sliski.App
import com.code.sliski.preference.PreferencesManager
import com.code.sliski.ui.fragment.UserIdFragment
import com.code.sliski.ui.fragment.UserInfoFragment
import javax.inject.Inject

public class MainActivity : ActionBarActivity() {

    public var mPreferencesManager: PreferencesManager? = null
        [Inject] set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        App.mGraph.inject(this)

        if (savedInstanceState == null) {
            addFragment()
        }
    }

    private fun addFragment() {
        val userId = mPreferencesManager?.userId()?.getOr(0L)
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

    override fun onBackPressed() {
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