package com.code.sliski.postlistscreen.ui.presenter

import com.code.sliski.api.model.Post
import io.reactivex.Single

interface PostListProvider {

    fun postList(): Single<List<Post>>
}