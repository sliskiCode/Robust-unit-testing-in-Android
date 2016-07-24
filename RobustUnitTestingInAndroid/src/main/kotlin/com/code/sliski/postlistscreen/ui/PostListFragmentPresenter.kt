package com.code.sliski.postlistscreen.ui

import com.code.sliski.api.Client
import com.code.sliski.api.model.Post
import com.code.sliski.preference.PreferencesManager
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class PostListFragmentPresenter(private var client: Client,
                                private var preferencesManager: PreferencesManager,
                                private var postList: List<Post>) : PostListFragmentMVP.Presenter {

    private var view: PostListFragmentMVP.View? = null

    private lateinit var postsSubscription: Subscription

    private val schedulerIO = Schedulers.io()
    private val schedulerUI = AndroidSchedulers.mainThread()

    override fun loadData() {
        if (postList.isNotEmpty()) {
            view?.setAdapter(postList)
        } else {
            val userId = preferencesManager.getUserId()
            val posts = client.getPosts(userId)
            postsSubscription = posts
                    .subscribeOn(schedulerIO)
                    .map { it.posts }
                    .doOnNext { postList = it }
                    .observeOn(schedulerUI)
                    .doOnCompleted {
                        view?.setAdapter(postList)
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
            view?.addToBackStack(post)
    }

    override fun attachView(view: PostListFragmentMVP.View?) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }
}