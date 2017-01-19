package com.code.sliski.api

import com.code.sliski.api.model.Post
import com.code.sliski.api.model.PostWrapper
import io.reactivex.Single

interface Client {

    fun getPosts(userId: Long): Single<PostWrapper<Post>>
}