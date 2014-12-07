package com.code.sliski.ui;

import android.content.Context;
import com.code.sliski.ui.api.StackoverflowApi;
import com.code.sliski.ui.api.StackoverflowClient;
import com.code.sliski.ui.fragment.PostDetailsFragmentTest;
import com.code.sliski.ui.fragment.PostListFragmentTest;
import com.code.sliski.ui.model.Post;
import com.code.sliski.ui.preference.PrefManager;
import com.google.gson.Gson;
import com.tale.prettysharedpreferences.LongEditor;
import dagger.Module;
import dagger.Provides;
import de.greenrobot.event.EventBus;

import javax.inject.Singleton;
import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Module(
        includes = {
                AppModule.class
        },
        injects = {
                PostListFragmentTest.class,
                PostDetailsFragmentTest.class
        },
        overrides = true
)
public class TestAppModuleForDataLoaded {

    private Context mContext;

    public TestAppModuleForDataLoaded(Context context) {
        mContext = context;
    }

    public TestAppModuleForDataLoaded() {
    }

    @Provides
    public ArrayList<Post> providePosts() {
        ArrayList<Post> posts = new ArrayList<Post>();
        posts.add(Post.Builder.withPostId(1).withLink("www.1.com").withScore(1).build());
        posts.add(Post.Builder.withPostId(2).withLink("www.2.com").withScore(2).build());
        posts.add(Post.Builder.withPostId(3).withLink("www.3.com").withScore(3).build());
        posts.add(Post.Builder.withPostId(4).withLink("www.4.com").withScore(4).build());
        posts.add(Post.Builder.withPostId(5).withLink("www.5.com").withScore(5).build());
        return posts;
    }

    @Provides
    @Singleton
    public StackoverflowClient provideStackoverflowClient(StackoverflowApi api, EventBus eventBus) {
        return mock(StackoverflowClient.class);
    }

    @Provides
    @Singleton
    public StackoverflowApi provideStackoverflowApi(Gson gson) {
        return mock(StackoverflowApi.class);
    }

    @Provides
    @Singleton
    public Gson provideGson() {
        return null;
    }

    @Provides
    @Singleton
    public EventBus provideEventBus() {
        return mock(EventBus.class);
    }

    @Provides
    @Singleton
    public PrefManager providePrefManager() {
        PrefManager prefManagerMock = mock(PrefManager.class);
        LongEditor longEditorMock = mock(LongEditor.class);
        when(longEditorMock.getOr(0l)).thenReturn(1408086l);
        when(prefManagerMock.userId()).thenReturn(longEditorMock);
        return prefManagerMock;
    }
}
