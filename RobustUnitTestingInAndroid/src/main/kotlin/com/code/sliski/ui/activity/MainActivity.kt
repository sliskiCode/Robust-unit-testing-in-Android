package com.code.sliski.ui.activity

import android.support.v7.app.ActionBarActivity
import android.os.Bundle
import com.code.sliski.R
import android.content.Context
import android.support.v4.app.Fragment
import com.code.sliski.App
import com.code.sliski.preference.PreferencesManager
import com.code.sliski.ui.fragment.LoginFragment
import com.code.sliski.ui.fragment.UserInfoFragment
import com.code.sliski.ui.presenter.MainActivityPresenter
import com.code.sliski.ui.presenter.MainActivityPresenterImpl
import javax.inject.Inject

public class MainActivity : ActionBarActivity(), MainActivityView {

    public var presenter: MainActivityPresenter? = null
        [Inject] set

    override fun onCreate(savedInstanceState: Bundle?) {
        super<ActionBarActivity>.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        App.mGraph.inject(this)
        (presenter as MainActivityPresenterImpl).mainActivityView = this
        presenter!!.addFragment(savedInstanceState)
    }

    override fun addLoginFragment() {
        getSupportFragmentManager().
                beginTransaction().
                add(R.id.container, LoginFragment())
                .commit()
    }

    override fun addUserInfoFragment() {
        getSupportFragmentManager().
                beginTransaction().
                add(R.id.container, UserInfoFragment())
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
        super<ActionBarActivity>.onBackPressed()
    }
}