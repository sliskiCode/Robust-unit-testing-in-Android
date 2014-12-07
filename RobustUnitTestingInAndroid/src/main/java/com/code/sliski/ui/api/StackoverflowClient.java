package com.code.sliski.ui.api;

import com.code.sliski.ui.event.GetPostsResponseEvent;
import com.code.sliski.ui.model.PostWrapper;
import com.code.sliski.ui.model.Post;
import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class StackoverflowClient {

    private final StackoverflowApi mApi;
    private final EventBus mEventBus;

    public StackoverflowClient(StackoverflowApi api, EventBus eventBus) {
        mApi = api;
        mEventBus = eventBus;
    }

    public void getPosts(long userId) {
        mApi.getPosts(userId, new Callback<PostWrapper<Post>>() {
            @Override
            public void success(PostWrapper<Post> postsWrapper, Response response) {
                mEventBus.post(new GetPostsResponseEvent(postsWrapper.getPosts()));
            }

            @Override
            public void failure(RetrofitError error) {
            }
        });
    }
}
