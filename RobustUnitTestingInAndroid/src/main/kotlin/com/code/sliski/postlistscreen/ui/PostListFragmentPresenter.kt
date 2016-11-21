package com.code.sliski.postlistscreen.ui

import com.code.sliski.api.Client
import com.code.sliski.api.model.Post
import com.code.sliski.mvp.Presenter
import rx.Scheduler
import rx.Subscriber
import rx.Subscription

class PostListFragmentPresenter(private val client: Client,
                                private val userId: Long,
                                private var postList: List<Post>,
                                private val schedulerIO: Scheduler,
                                private val schedulerUI: Scheduler) : Presenter<View>() {

    private lateinit var postsSubscription: Subscription

    fun present() = if (postList.isNotEmpty()) {
        view?.showPosts(postList)
    } else {
        val posts = client.getPosts(userId)
        postsSubscription = posts.subscribeOn(schedulerIO)
                                 .map { it.posts }
                                 .observeOn(schedulerUI)
                                 .subscribe(PostSubscriber())
    }

    fun onItemClick(position: Int, isTablet: Boolean) = if (isTablet)
        view?.notifyOnPostClicked(postList[position])
    else
        view?.showPostDetailsScreen(postList[position])

    fun unsubscribe() {
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