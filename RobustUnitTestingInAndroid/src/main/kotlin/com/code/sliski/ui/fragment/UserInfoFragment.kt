package com.code.sliski.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.code.sliski.App
import com.code.sliski.R
import com.code.sliski.ui.presenter.UserInfoFragmentPresenter
import com.code.sliski.ui.presenter.UserInfoFragmentPresenterImpl
import com.code.sliski.util.addChildFragment
import com.code.sliski.util.isTablet
import javax.inject.Inject

public class UserInfoFragment : Fragment(), UserInfoFragmentView {

    public var presenter: UserInfoFragmentPresenter? = null
        [Inject] set

    override fun onCreate(savedInstanceState: Bundle?) {
        super<Fragment>.onCreate(savedInstanceState)
        App.mGraph.inject(this)
        (presenter as UserInfoFragmentPresenterImpl).userInfoFragmentView = this
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.user_info_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super<Fragment>.onActivityCreated(savedInstanceState)
        presenter!!.addFragments(savedInstanceState, isTablet())
    }

    override fun addPostListFragment() {
        addChildFragment(R.id.list_container, PostListFragment())
    }

    override fun addPostDetailsFragment() {
        addChildFragment(R.id.preview_container, PostDetailsFragment())
    }
}