package com.code.sliski.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.code.sliski.ui.R

public class UserInfoFragment : Fragment() {

    override public fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.user_info_fragment, container, false)
    }

    override public fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (savedInstanceState == null) {
            addFragments()
        }
    }

    private fun addFragments() {
        if (isTablet()) {
            getChildFragmentManager()
                    .beginTransaction()
                    .add(R.id.list_container, PostListFragment())
                    .commit()
            getChildFragmentManager()
                    .beginTransaction()
                    .add(R.id.preview_container, PostDetailsFragment())
                    .commit()
        } else {
            getChildFragmentManager()
                    .beginTransaction()
                    .add(R.id.list_container, PostListFragment())
                    .commit()
        }
    }

    public fun isTablet(): Boolean {
        return getView().findViewById(R.id.preview_container) != null
    }
}