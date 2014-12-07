package com.code.sliski.ui.api;

import com.code.sliski.ui.model.PostWrapper;
import com.code.sliski.ui.model.Post;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

public interface StackoverflowApi {
    @GET("/users/{userId}/posts?site=stackoverflow")
    void getPosts(@Path("userId") long userId, Callback<PostWrapper<Post>> callback);
}