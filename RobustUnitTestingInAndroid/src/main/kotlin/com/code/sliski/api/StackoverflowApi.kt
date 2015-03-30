package com.code.sliski.api

import com.code.sliski.model.Post;
import com.code.sliski.model.PostWrapper
import retrofit.Callback;
import retrofit.http.GET
import retrofit.http.Path

public trait StackoverflowApi {
    GET("/users/{userId}/posts?site=stackoverflow")
    fun getPosts(Path("userId") userId : Long, callback : Callback<PostWrapper<Post>> )
}