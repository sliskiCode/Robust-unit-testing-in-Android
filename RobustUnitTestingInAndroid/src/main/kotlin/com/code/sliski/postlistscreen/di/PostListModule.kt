package com.code.sliski.postlistscreen.di

import com.code.sliski.api.Client
import com.code.sliski.api.StackoverflowApi
import com.code.sliski.api.StackoverflowClient
import com.code.sliski.postlistscreen.domain.PostListDomain
import com.code.sliski.postlistscreen.ui.mapper.PostPresentationMapperImpl
import com.code.sliski.postlistscreen.ui.presenter.PostListFragmentPresenter
import com.code.sliski.preference.PreferencesManager
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class PostListModule {

    @Provides
    @PostListScope
    fun providePostListFragmentPresenter(client: Client,
                                         preferencesManager: PreferencesManager): PostListFragmentPresenter =
            PostListFragmentPresenter(PostListDomain(client,
                                                     preferencesManager.getUserId(),
                                                     Schedulers.io()),
                                      PostPresentationMapperImpl,
                                      emptyList(),
                                      AndroidSchedulers.mainThread())

    @Provides
    @PostListScope
    fun client(api: StackoverflowApi): Client = StackoverflowClient(api)

    @Provides
    @PostListScope
    fun stackoverflowApi(): StackoverflowApi =
            Retrofit.Builder()
                    .baseUrl("https://api.stackexchange.com/2.2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
                    .create(StackoverflowApi::class.java)
}