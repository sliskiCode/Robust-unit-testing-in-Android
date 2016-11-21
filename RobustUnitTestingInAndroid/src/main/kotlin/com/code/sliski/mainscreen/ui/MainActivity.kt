package com.code.sliski.mainscreen.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.code.sliski.App
import com.code.sliski.R.id.container
import com.code.sliski.R.layout.activity_main
import com.code.sliski.extension.addFragment
import com.code.sliski.extension.application
import com.code.sliski.loginscreen.ui.LoginFragment
import com.code.sliski.mainscreen.di.DaggerMainComponent
import com.code.sliski.mainscreen.di.MainModule
import com.code.sliski.userinfoscreen.ui.UserInfoFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity(), View {

    @Inject
    lateinit var presenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)
        DaggerMainComponent.builder()
                           .appComponent(application<App>().component)
                           .mainModule(MainModule())
                           .build()
                           .inject(this)
        presenter.attach(this)
        presenter.present(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        presenter.attach(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detach()
    }

    override fun showLoginScreen() {
        addFragment(container, LoginFragment())
    }

    override fun showUserInfoScreen() {
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