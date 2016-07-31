package com.code.sliski.postlistscreen.ui

import com.code.sliski.api.Client
import com.code.sliski.api.model.Post
import com.code.sliski.preference.PreferencesManager
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*

class PostListFragmentPresenter(private var client: Client,
                                private var preferencesManager: PreferencesManager,
                                private var postList: List<Post> = ArrayList()) :
        PostListFragmentMVP.Presenter {

    private var view: PostListFragmentMVP.View? = null

    private lateinit var postsSubscription: Subscription

    private val schedulerIO = Schedulers.io()
    private val schedulerUI = AndroidSchedulers.mainThread()

    override fun present() {
        if (postList.isNotEmpty()) {
            view?.showPosts(postList)
        } else {
            val userId = preferencesManager.getUserId()
            val posts = client.getPosts(userId)
            postsSubscription = posts
                    .subscribeOn(schedulerIO)
                    .map { it.posts }
                    .doOnNext { postList = it }
                    .observeOn(schedulerUI)
                    .doOnCompleted {
                        view?.showPosts(postList)
                        postsSubscription.unsubscribe()
                    }
                    .subscribe()
        }
    }

    override fun onItemClick(position: Int, isTablet: Boolean) {
        val post = postList[position]
        if (isTablet)
            view?.notifyOnPostClicked(post)
        else
            view?.showPostDetailsScreen(post)
    }

    override fun attach(view: PostListFragmentMVP.View?) {
        this.view = view
    }

    override fun detach() {
        this.view = null
    }
}