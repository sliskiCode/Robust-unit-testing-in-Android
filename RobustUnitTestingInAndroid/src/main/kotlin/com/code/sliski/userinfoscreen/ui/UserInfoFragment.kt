package com.code.sliski.userinfoscreen.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.code.sliski.R.id.list_container
import com.code.sliski.R.id.preview_container
import com.code.sliski.R.layout.user_info_fragment
import com.code.sliski.extension.addChildFragment
import com.code.sliski.extension.isTablet
import com.code.sliski.postdetailsscreen.PostDetailsFragment
import com.code.sliski.postlistscreen.ui.PostListFragment
import com.code.sliski.userinfoscreen.di.DaggerUserInfoComponent
import com.code.sliski.userinfoscreen.di.UserInfoModule
import javax.inject.Inject

class UserInfoFragment : Fragment(),
                         UserInfoFragmentMVP.View {

    @Inject
    lateinit var presenter: UserInfoFragmentMVP.Presenter

    private lateinit var postListFragment: PostListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerUserInfoComponent.builder()
                               .userInfoModule(UserInfoModule())
                               .build()
                               .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(user_info_fragment, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.attach(this)
        presenter.present(savedInstanceState, isTablet())
    }

    override fun onStart() {
        super.onStart()
        presenter.attach(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detach()
    }

    override fun showPostListScreen() {
        postListFragment = PostListFragment()
        addChildFragment(list_container, postListFragment)
    }

    override fun showPostDetailsScreen() {
        val detailsFragment = PostDetailsFragment()
        postListFragment.onPostClickListener = detailsFragment
        addChildFragment(preview_container, detailsFragment)
    }
}