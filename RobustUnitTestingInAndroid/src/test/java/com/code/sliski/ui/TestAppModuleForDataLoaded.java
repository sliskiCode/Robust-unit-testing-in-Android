package com.code.sliski.ui;

import android.content.Context;
import com.code.sliski.api.Client;
import com.code.sliski.api.StackoverflowApi;
import com.code.sliski.preference.PreferencesManager;
import com.code.sliski.ui.fragment.PostDetailsFragmentTest;
import com.code.sliski.ui.fragment.PostListFragmentTest;
import com.code.sliski.model.Post;
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
        posts.add(new Post(1, 1 , "www.1.com"));
        posts.add(new Post(2, 2 , "www.2.com"));
        posts.add(new Post(3, 3 , "www.3.com"));
        posts.add(new Post(4, 4 , "www.4.com"));
        posts.add(new Post(5, 5 , "www.5.com"));
        return posts;
    }

    @Provides
    @Singleton
    public Client provideClient(StackoverflowApi api, EventBus eventBus) {
        return mock(Client.class);
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
    public PreferencesManager providePrefManager() {
        PreferencesManager prefManagerMock = mock(PreferencesManager.class);
        LongEditor longEditorMock = mock(LongEditor.class);
        when(longEditorMock.getOr(0l)).thenReturn(1408086l);
        when(prefManagerMock.userId()).thenReturn(longEditorMock);
        return prefManagerMock;
    }
}
