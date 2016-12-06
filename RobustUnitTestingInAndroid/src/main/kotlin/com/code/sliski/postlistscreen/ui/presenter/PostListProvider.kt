package com.code.sliski.postlistscreen.ui.presenter

import com.code.sliski.api.model.Post
import rx.Observable

interface PostListProvider {

    fun postList(): Observable<List<Post>>
}