package com.code.sliski.postlistscreen.ui

import com.code.sliski.api.Client
import com.code.sliski.api.model.Post
import com.code.sliski.preference.PreferencesManager
import rx.Scheduler
import rx.Subscriber
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*

class PostListFragmentPresenter(private val client: Client,
                                private val preferencesManager: PreferencesManager,
                                private var postList: List<Post>,
                                private val schedulerIO: Scheduler,
                                private val schedulerUI: Scheduler) : PostListFragmentMVP.Presenter {

    private var view: PostListFragmentMVP.View? = null

    private lateinit var postsSubscription: Subscription

    override fun present() {
        if (postList.isNotEmpty()) {
            view?.showPosts(postList)
        } else {
            val userId = preferencesManager.getUserId()
            val posts = client.getPosts(userId)
            postsSubscription = posts.subscribeOn(schedulerIO)
                                     .map { it.posts }
                                     .observeOn(schedulerUI)
                                     .subscribe(PostSubscriber())
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

    override fun unsubscribe() {
        if (postsSubscription.isUnsubscribed) postsSubscription.unsubscribe()
    }

    inner class PostSubscriber : Subscriber<List<Post>>() {
        override fun onCompleted() {
            view?.showPosts(postList)
        }

        override fun onNext(next: List<Post>) {
            postList = next
        }

        override fun onError(e: Throwable) {
            view?.showError(e.message!!)
        }
    }
}