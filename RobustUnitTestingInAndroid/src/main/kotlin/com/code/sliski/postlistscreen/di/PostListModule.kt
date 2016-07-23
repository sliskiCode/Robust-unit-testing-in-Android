package com.code.sliski.postlistscreen.di

import com.code.sliski.api.Client
import com.code.sliski.api.StackoverflowApi
import com.code.sliski.api.StackoverflowClient
import com.code.sliski.api.model.Post
import com.code.sliski.postlistscreen.ui.PostListFragmentMVP
import com.code.sliski.postlistscreen.ui.PostListFragmentPresenterImpl
import com.code.sliski.preference.PreferencesManager
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

@Module
class PostListModule {

    @Provides
    @PostListScope
    fun providePostListFragmentPresenter(client: Client,
                                         preferencesManager: PreferencesManager,
                                         posts: ArrayList<Post>): PostListFragmentMVP.Presenter =
            PostListFragmentPresenterImpl(client,
                                          preferencesManager,
                                          posts)

    @Provides
    @PostListScope
    fun posts(): ArrayList<Post> = ArrayList()

    @Provides
    @PostListScope
    fun client(api: StackoverflowApi): Client = StackoverflowClient(api)

    @Provides
    @PostListScope
    fun stackoverflowApi(): StackoverflowApi {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.stackexchange.com/2.2/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
        return retrofit.create(StackoverflowApi::class.java)
    }
}
