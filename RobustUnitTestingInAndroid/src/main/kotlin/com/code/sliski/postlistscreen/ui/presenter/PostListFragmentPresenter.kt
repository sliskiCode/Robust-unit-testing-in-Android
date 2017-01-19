package com.code.sliski.postlistscreen.ui.presenter

import com.code.sliski.mvp.Presenter
import com.code.sliski.postlistscreen.ui.mapper.PostPresentationMapper
import com.code.sliski.postlistscreen.ui.model.PresentationPost
import com.code.sliski.postlistscreen.ui.view.View
import io.reactivex.Scheduler
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

class PostListFragmentPresenter(provider: PostListProvider,
                                private val mapper: PostPresentationMapper,
                                private var postList: List<PresentationPost>,
                                schedulerUI: Scheduler) : Presenter<View>() {

    private val single = provider.postList()
                                 .map { mapper.map(it) }
                                 .observeOn(schedulerUI)

    private lateinit var disposable: Disposable

    fun present() = single.subscribe(PostSubscriber())

    fun onItemClick(position: Int, isTablet: Boolean) = if (isTablet)
        view?.notifyOnPostClicked(postList[position])
    else
        view?.showPostDetailsScreen(postList[position])

    fun dispose() {
        if (!disposable.isDisposed) disposable.dispose()
    }

    inner class PostSubscriber : SingleObserver<List<PresentationPost>> {
        override fun onError(e: Throwable) {
            view?.showError(e.message!!)
        }

        override fun onSuccess(result: List<PresentationPost>) {
            postList = result
            view?.showPosts(postList)
        }

        override fun onSubscribe(d: Disposable) {
            disposable = d
        }
    }
}