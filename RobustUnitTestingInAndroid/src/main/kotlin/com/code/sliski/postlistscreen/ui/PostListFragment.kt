package com.code.sliski.postlistscreen.ui

import android.R.layout.simple_list_item_1
import android.os.Bundle
import android.support.v4.app.ListFragment
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.code.sliski.App
import com.code.sliski.R.id.list_container
import com.code.sliski.api.model.Post
import com.code.sliski.extension.addToBackStack
import com.code.sliski.extension.application
import com.code.sliski.extension.isTablet
import com.code.sliski.postdetailsscreen.OnPostClickListener
import com.code.sliski.postdetailsscreen.PostDetailsFragment
import javax.inject.Inject

class PostListFragment : ListFragment(),
                         PostListFragmentMVP.View,
                         AdapterView.OnItemClickListener {

    @Inject
    lateinit var presenter: PostListFragmentMVP.Presenter

    lateinit var onPostClickListener: OnPostClickListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        application<App>().postListComponent().inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        listView.onItemClickListener = this
        presenter.attach(this)
        presenter.present()
    }

    override fun onStart() {
        super.onStart()
        presenter.attach(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detach()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (activity.isFinishing) {
            application<App>().releasePostListComponent()
        }
    }

    override fun showPosts(posts: List<Post>) {
        listAdapter = ArrayAdapter(activity, simple_list_item_1, posts)
    }

    override fun showPostDetailsScreen(post: Post) {
        addToBackStack(list_container, PostDetailsFragment.getInstance(post))
    }

    override fun notifyOnPostClicked(post: Post) {
        onPostClickListener.onPostClick(post)
    }

    override fun onItemClick(pv: AdapterView<*>, v: View, p: Int, id: Long) {
        presenter.onItemClick(p, isTablet())
    }
}