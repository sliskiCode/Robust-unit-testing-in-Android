package com.code.sliski.postlistscreen.di

import com.code.sliski.api.Client
import com.code.sliski.api.StackoverflowApi
import com.code.sliski.api.StackoverflowClient
import com.code.sliski.postlistscreen.ui.PostListFragmentMVP
import com.code.sliski.postlistscreen.ui.PostListFragmentPresenter
import com.code.sliski.preference.PreferencesManager
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class PostListModule {

    @Provides
    @PostListScope
    fun providePostListFragmentPresenter(client: Client,
                                         preferencesManager: PreferencesManager): PostListFragmentMVP.Presenter =
            PostListFragmentPresenter(client, preferencesManager)

    @Provides
    @PostListScope
    fun client(api: StackoverflowApi): Client = StackoverflowClient(api)

    @Provides
    @PostListScope
    fun stackoverflowApi(): StackoverflowApi =
            Retrofit.Builder()
                    .baseUrl("https://api.stackexchange.com/2.2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build()
                    .create(StackoverflowApi::class.java)
}