package com.code.sliski.loginscreen.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.code.sliski.App
import com.code.sliski.R.layout.user_id_fragment
import com.code.sliski.R.string.bad_format_info
import com.code.sliski.extension.application
import com.code.sliski.extension.replaceWith
import com.code.sliski.extension.showMessage
import com.code.sliski.loginscreen.di.DaggerLoginComponent
import com.code.sliski.loginscreen.di.LoginModule
import com.code.sliski.userinfoscreen.ui.UserInfoFragment
import kotlinx.android.synthetic.main.user_id_fragment.*
import javax.inject.Inject

class LoginFragment : Fragment(),
                      LoginFragmentMVP.View {

    @Inject
    lateinit var presenter: LoginFragmentMVP.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerLoginComponent.builder()
                            .appComponent(application<App>().component)
                            .loginModule(LoginModule())
                            .build()
                            .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(user_id_fragment, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.attachView(this)
        go_button.setOnClickListener() {
            presenter.attemptLogin(user_id.text.toString())
        }
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView()
    }

    override fun showBadFormatInfo() {
        showMessage(bad_format_info)
    }

    override fun goToUserInfo() {
        replaceWith(UserInfoFragment())
    }
}