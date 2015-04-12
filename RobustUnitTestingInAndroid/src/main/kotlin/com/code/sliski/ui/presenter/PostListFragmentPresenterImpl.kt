package com.code.sliski.ui.presenter

import com.code.sliski.App
import com.code.sliski.api.Client
import com.code.sliski.event.GetPostsResponseEvent
import com.code.sliski.event.OnPostClickedEvent
import com.code.sliski.model.Post
import com.code.sliski.preference.PreferencesManager
import com.code.sliski.ui.fragment.PostListFragmentView
import de.greenrobot.event.EventBus
import java.util.ArrayList
import javax.inject.Inject

public class PostListFragmentPresenterImpl() : PostListFragmentPresenter {

    var postListFragmentView : PostListFragmentView? = null
    var client: Client? = null
    var preferencesManager: PreferencesManager? = null
    var postList: ArrayList<Post>? = null
    var eventBus: EventBus? = null

    override fun getPosts() {
        if (postList != null) {
            postListFragmentView?.setAdapter(postList)
        } else {
            eventBus?.register(this)
            val userId = preferencesManager?.userId()?.getOr(0L);
            client?.getPosts(userId as Long);
        }
    }

    override fun onItemClick(position: Int, isTablet: Boolean) {
        val post = postList?.get(position) as Post
        if (isTablet) {
            eventBus?.post(OnPostClickedEvent(post))
        } else {
            postListFragmentView?.addToBackStack(post)
        }
    }

    public fun onEvent(event: GetPostsResponseEvent) {
        postList = event.posts
        postListFragmentView?.setAdapter(ArrayList(event.posts))
    }
}