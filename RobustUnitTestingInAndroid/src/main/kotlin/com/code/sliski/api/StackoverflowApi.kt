package com.code.sliski.api

import com.code.sliski.api.model.Post;
import com.code.sliski.api.model.PostWrapper
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

interface StackoverflowApi {

    @GET("users/{userId}/posts?site=stackoverflow")
    fun getPosts(@Path("userId") userId: Long): Observable<PostWrapper<Post>>
}