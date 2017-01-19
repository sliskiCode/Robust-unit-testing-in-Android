package com.code.sliski.api

import com.code.sliski.api.model.Post;
import com.code.sliski.api.model.PostWrapper
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface StackoverflowApi {

    @GET("users/{userId}/posts?site=stackoverflow")
    fun getPosts(@Path("userId") userId: Long): Single<PostWrapper<Post>>
}