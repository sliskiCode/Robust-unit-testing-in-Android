package com.code.sliski.ui.event;

import com.code.sliski.ui.model.Post;

public final class OnPostClickedEvent {

    private final Post mPost;

    public OnPostClickedEvent(Post post) {
        mPost = post;
    }

    public Post getPost() {
        return mPost;
    }
}
