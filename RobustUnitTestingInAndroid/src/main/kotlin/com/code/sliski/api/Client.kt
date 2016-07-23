package com.code.sliski.api

import com.code.sliski.api.model.Post
import com.code.sliski.api.model.PostWrapper
import rx.Observable

interface Client {

    fun getPosts(userId: Long): Observable<PostWrapper<Post>>
}