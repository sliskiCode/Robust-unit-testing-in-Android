package com.code.sliski;

import android.support.annotation.Nullable;
import com.code.sliski.api.Client;
import com.code.sliski.api.StackoverflowApi;
import com.code.sliski.preference.PreferencesManager;
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

@Module
public class TestAppModule {

    @Provides
    @Nullable
    public ArrayList<Post> providePosts() {
        return null;
    }

    @Provides
    @Singleton
    public Client provideClient(StackoverflowApi api, EventBus eventBus) {
        return mock(Client.class);
    }

    @Provides
    @Singleton
    public StackoverflowApi provideStackoverflowApi(@Nullable Gson gson) {
        return mock(StackoverflowApi.class);
    }

    @Provides
    @Singleton
    @Nullable
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
