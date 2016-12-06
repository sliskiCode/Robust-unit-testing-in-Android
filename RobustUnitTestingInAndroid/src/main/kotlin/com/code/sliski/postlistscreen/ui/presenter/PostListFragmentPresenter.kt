package com.code.sliski.postlistscreen.ui.presenter

import com.code.sliski.mvp.Presenter
import com.code.sliski.postlistscreen.ui.mapper.PostPresentationMapper
import com.code.sliski.postlistscreen.ui.model.PresentationPost
import com.code.sliski.postlistscreen.ui.view.View
import rx.Scheduler
import rx.Subscriber
import rx.Subscription

class PostListFragmentPresenter(private val provider: PostListProvider,
                                private val mapper: PostPresentationMapper,
                                private var postList: List<PresentationPost>,
                                private val schedulerUI: Scheduler) : Presenter<View>() {

    private lateinit var subscription: Subscription

    fun present() = if (postList.isNotEmpty()) {
        view?.showPosts(postList)
    } else {
        subscription = provider
                .postList()
                .map { mapper.map(it) }
                .observeOn(schedulerUI)
                .subscribe(PostSubscriber())
    }

    fun onItemClick(position: Int, isTablet: Boolean) = if (isTablet)
        view?.notifyOnPostClicked(postList[position])
    else
        view?.showPostDetailsScreen(postList[position])

    fun unsubscribe() {
        if (subscription.isUnsubscribed) subscription.unsubscribe()
    }

    inner class PostSubscriber : Subscriber<List<PresentationPost>>() {
        override fun onCompleted() {
            view?.showPosts(postList)
        }

        override fun onNext(next: List<PresentationPost>) {
            postList = next
        }

        override fun onError(e: Throwable) {
            view?.showError(e.message!!)
        }
    }
}