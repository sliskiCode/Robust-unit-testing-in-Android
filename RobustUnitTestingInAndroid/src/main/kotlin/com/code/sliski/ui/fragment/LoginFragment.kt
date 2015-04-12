package com.code.sliski.ui.fragment

import android.support.v4.app.Fragment
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import com.code.sliski.R
import android.widget.EditText
import android.widget.Button
import android.widget.Toast
import android.content.Context
import android.support.v7.app.ActionBarActivity
import com.code.sliski.App
import com.code.sliski.preference.PreferencesManager
import com.code.sliski.ui.presenter.LoginFragmentPresenter
import com.code.sliski.ui.presenter.LoginFragmentPresenterImpl
import com.code.sliski.util.findView
import com.code.sliski.util.replaceWith
import com.code.sliski.util.showToastShort
import java.io.Serializable
import javax.inject.Inject

public class LoginFragment : Fragment(), LoginFragmentView, View.OnClickListener {

    public var presenter: LoginFragmentPresenter? = null
        [Inject] set

    override fun onCreate(savedInstanceState: Bundle?) {
        super<Fragment>.onCreate(savedInstanceState)
        App.mGraph.inject(this)
        (presenter as LoginFragmentPresenterImpl).loginFragmentView = this
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.user_id_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super<Fragment>.onActivityCreated(savedInstanceState)
        findView<View>(R.id.go_button).setOnClickListener(this)
    }

    override fun setId(id: String) {
        findView<EditText>(R.id.user_id_editText).setText(id)
    }

    override fun onClickGoButton() {
        findView<EditText>(R.id.user_id_editText).callOnClick()
    }

    override fun showBadFormatInfo() {
        showToastShort(R.string.bad_format_info)
    }

    override fun replaceWithUserInfoFragment() {
        replaceWith(UserInfoFragment())
    }

    override fun onClick(v: View) {
        val userId = findView<EditText>(R.id.user_id_editText).getText().toString()
        presenter?.login(userId)
    }
}