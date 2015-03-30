package com.code.sliski.ui;

import android.content.Context;
import com.code.sliski.api.Client;
import com.code.sliski.api.StackoverflowApi;
import com.code.sliski.api.StackoverflowClient;
import com.code.sliski.preference.PreferencesManager;
import com.code.sliski.ui.fragment.PostListFragment;
import com.code.sliski.model.Post;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;
import de.greenrobot.event.EventBus;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

import javax.inject.Singleton;
import java.util.ArrayList;

@Module(
        injects = {
                PostListFragment.class,
        }
)
public class AppModule {

    private Context mContext;

    public AppModule(Context context) {
        mContext = context;
    }

    public AppModule() {
    }

    @Provides
    public ArrayList<Post> providePosts() {
        return null;
    }

    @Provides
    @Singleton
    public Client provideClient(StackoverflowApi api, EventBus eventBus) {
        return new StackoverflowClient(api, eventBus);
    }

    @Provides
    @Singleton
    public StackoverflowApi provideStackoverflowApi(Gson gson) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://api.stackexchange.com/2.2")
                .setConverter(new GsonConverter(gson))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        return restAdapter.create(StackoverflowApi.class);
    }

    @Provides
    @Singleton
    public Gson provideGson() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

    @Provides
    @Singleton
    public EventBus provideEventBus() {
        return EventBus.getDefault();
    }

    @Provides
    @Singleton
    public PreferencesManager providePreferencesManager() {
        return new PreferencesManager(mContext.getSharedPreferences(mContext.getString(R.string.preferences), Context.MODE_PRIVATE));
    }
}
