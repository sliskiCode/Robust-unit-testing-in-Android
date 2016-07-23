package com.code.sliski.mainscreen.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.code.sliski.App
import com.code.sliski.R.id.container
import com.code.sliski.R.layout.activity_main
import com.code.sliski.extension.addFragment
import com.code.sliski.loginscreen.ui.LoginFragment
import com.code.sliski.mainscreen.di.DaggerMainComponent
import com.code.sliski.mainscreen.di.MainModule
import com.code.sliski.userinfoscreen.ui.UserInfoFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity(),
                     MainActivityMVP.View {

    @Inject
    lateinit var presenter: MainActivityMVP.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)
        DaggerMainComponent.builder()
                .appComponent((applicationContext as App).component)
                .mainModule(MainModule())
                .build()
                .inject(this)
        presenter.attachView(this)
        presenter.buildView(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView()
    }

    override fun addLoginFragment() {
        addFragment(container, LoginFragment())
    }

    override fun addUserInfoFragment() {
        addFragment(container, UserInfoFragment())
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it != null && it.isVisible) {
                val childFm = it.childFragmentManager
                if (childFm.backStackEntryCount > 0) {
                    childFm.popBackStack()
                    return
                }
            }
        }
        super.onBackPressed()
    }
}