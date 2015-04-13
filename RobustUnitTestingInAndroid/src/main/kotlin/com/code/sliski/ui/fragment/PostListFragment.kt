package com.code.sliski.ui.fragment

import android.os.Bundle
import android.support.v4.app.ListFragment
import android.support.v7.internal.widget.AdapterViewCompat
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.code.sliski.App
import com.code.sliski.api.Client
import com.code.sliski.event.GetPostsResponseEvent
import com.code.sliski.event.OnPostClickedEvent
import com.code.sliski.model.Post
import com.code.sliski.preference.PreferencesManager
import de.greenrobot.event.EventBus
import java.util.ArrayList
import javax.inject.Inject
import com.code.sliski.R
import com.code.sliski.ui.presenter.PostListFragmentPresenter
import com.code.sliski.ui.presenter.PostListFragmentPresenterImpl
import com.code.sliski.util.addToBackStack
import com.code.sliski.util.isTablet

public class PostListFragment : ListFragment(), PostListFragmentView,
        AdapterView.OnItemClickListener {

    public var presenter: PostListFragmentPresenter? = null
        [Inject] set

    override public fun onCreate(savedInstanceState: Bundle?) {
        super<ListFragment>.onCreate(savedInstanceState)
        App.mGraph.inject(this)
        presenter?.setView(this)
    }

    override public fun onActivityCreated(savedInstanceState: Bundle?) {
        super<ListFragment>.onActivityCreated(savedInstanceState)
        getListView().setOnItemClickListener(this)
        presenter?.getPosts()
    }

    override fun onPause() {
        super<ListFragment>.onPause()
        presenter?.setView(null)
    }

    override fun onItemClick(pv: AdapterView<*>, v: View, p: Int, id: Long) {
        presenter?.onItemClick(p, isTablet())
    }

    override fun setAdapter(posts: ArrayList<Post>?) {
        val layoutId = android.R.layout.simple_list_item_1
        setListAdapter(ArrayAdapter(getActivity(), layoutId, posts))
    }

    override fun addToBackStack(post: Post) {
        addToBackStack(R.id.list_container, PostDetailsFragment.getInstance(post))
    }
}