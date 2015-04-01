package com.code.sliski.ui.fragment

import android.os.Bundle
import android.support.v4.app.ListFragment
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

public class PostListFragment : ListFragment() {

    private val POSTS = "POSTS"

    public var mClient: Client? = null
        [Inject] set

    public var mEventBus: EventBus? = null
        [Inject] set

    public var mPreferencesManager: PreferencesManager? = null
        [Inject] set

    public var mPosts: ArrayList<Post>? = null
        [Inject] set

    override public fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.mGraph.inject(this)
        mEventBus?.register(this)
    }

    override public fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (savedInstanceState != null) {
            mPosts = savedInstanceState.getParcelableArrayList(POSTS)
        }
        if (mPosts == null) {
            getPosts()
        } else {
            setAdapter()
        }
        setListeners()
    }

    private fun getPosts() {
        val userId = mPreferencesManager?.userId()?.getOr(0L);
        mClient?.getPosts(userId as Long);
    }

    private fun setAdapter() {
        setListAdapter(ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, mPosts))
    }

    private fun setListeners() {
        getListView().setOnItemClickListener { parent, view, position, id ->
            val post = mPosts?.get(position)
            if ((getParentFragment() as UserInfoFragment).isTablet()) {
                mEventBus?.post(OnPostClickedEvent(post as Post))
            } else {
                getParentFragment()
                        .getChildFragmentManager()
                        .beginTransaction()
                        .replace(R.id.list_container, PostDetailsFragment.getInstance(post as Post))
                        .addToBackStack("details")
                        .commit()
            }
        }
    }

    override public fun onDestroy() {
        super.onDestroy()
        mEventBus?.unregister(this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(POSTS, mPosts)
    }

    public fun onEvent(event: GetPostsResponseEvent) {
        mPosts = ArrayList(event.posts)
        setAdapter()
    }
}