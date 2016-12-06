package com.code.sliski.postlistscreen.domain

import com.code.sliski.api.Client
import com.code.sliski.api.model.Post
import com.code.sliski.postlistscreen.ui.presenter.PostListProvider
import rx.Observable
import rx.Scheduler

class PostListDomain(private val client: Client,
                     private val userId: Long,
                     private val schedulerIO: Scheduler) : PostListProvider {

    override fun postList(): Observable<List<Post>> = client
            .getPosts(userId)
            .subscribeOn(schedulerIO)
            .map { it.posts }
            .flatMapIterable { it }
            .filter { it.score > 5 }
            .toSortedList(::descending)
}

private fun descending(post1: Post, post2: Post) = post2.score.compareTo(post1.score)