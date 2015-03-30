package com.code.sliski.ui.fragment

import android.support.v4.app.Fragment
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import com.code.sliski.ui.R
import android.widget.EditText
import android.widget.Button
import android.widget.Toast
import com.code.sliski.ui.preference.PrefManager
import android.content.Context

public class UserIdFragment : Fragment() {

    private var mUserIdEditText: EditText? = null
    private var mGoForUserButton: Button? = null

    override public fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.user_id_fragment, container, false)
    }

    override public fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mUserIdEditText = view.findViewById(R.id.user_id_editText) as EditText
        mGoForUserButton = view.findViewById(R.id.go_button) as Button
    }

    override public fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setListeners()
    }

    private fun setListeners() {
        mGoForUserButton?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                try {
                    saveUserIdToPrefs()
                    replaceFragmentWithUserInfoFragment()
                } catch (e: NumberFormatException) {
                    Toast.makeText(getActivity(), "Bad id format", Toast.LENGTH_SHORT).show();
                }
            }
        })
    }

    private fun saveUserIdToPrefs() {
        val prefManager = PrefManager(getActivity().getSharedPreferences(getString(R.string.preferences), Context.MODE_PRIVATE))
        val userId = mUserIdEditText?.getText().toString().toLong()
        prefManager
                .userId()
                .put(userId)
                .commit()
    }

    private fun replaceFragmentWithUserInfoFragment() {
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, UserInfoFragment())
                .commit()
    }
}