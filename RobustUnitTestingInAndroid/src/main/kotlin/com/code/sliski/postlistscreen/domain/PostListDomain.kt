package com.code.sliski.postlistscreen.domain

import com.code.sliski.api.Client
import com.code.sliski.api.model.Post
import com.code.sliski.postlistscreen.ui.presenter.PostListProvider
import io.reactivex.Scheduler
import io.reactivex.Single

class PostListDomain(private val client: Client,
                     private val userId: Long,
                     private val schedulerIO: Scheduler) : PostListProvider {

    override fun postList(): Single<List<Post>> = client
            .getPosts(userId)
            .subscribeOn(schedulerIO)
            .map { it.posts }
            .map(::scoreMoreThanFive)
            .map(::scoreDescending)
}

private fun scoreMoreThanFive(posts: List<Post>) = posts.filter { it.score > 5 }
private fun scoreDescending(posts: List<Post>) = posts.sortedBy(Post::score)