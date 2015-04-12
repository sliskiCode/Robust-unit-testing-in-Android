package com.code.sliski.api

import com.code.sliski.event.GetPostsResponseEvent
import com.code.sliski.model
import de.greenrobot.event.EventBus
import retrofit.Callback;
import com.code.sliski.model.PostWrapper
import com.code.sliski.model.Post
import retrofit.RetrofitError
import retrofit.client.Response
import java.util.ArrayList

public class StackoverflowClient(private var mApi: StackoverflowApi, private var mEventBus: EventBus) : Client {
    override fun getPosts(userId: Long) {
        mApi.getPosts(userId, object : Callback<model.PostWrapper<Post>> {
            override fun failure(error: RetrofitError) {
            }

            override fun success(postsWrapper: model.PostWrapper<Post>, response: Response) {
                mEventBus.post(GetPostsResponseEvent(ArrayList(postsWrapper.mPosts)));
            }
        })
    }
}