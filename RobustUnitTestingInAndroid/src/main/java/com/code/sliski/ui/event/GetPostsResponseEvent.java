package com.code.sliski.ui.event;

import com.code.sliski.ui.model.Post;

import java.util.List;

public final class GetPostsResponseEvent {

    private final List<Post> mPosts;

    public GetPostsResponseEvent(List<Post> posts) {
        mPosts = posts;
    }

    public List<Post> getPosts() {
        return mPosts;
    }
}
