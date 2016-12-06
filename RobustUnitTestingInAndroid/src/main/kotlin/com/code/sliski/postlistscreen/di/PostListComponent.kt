package com.code.sliski.postlistscreen.di

import com.code.sliski.AppComponent
import com.code.sliski.postlistscreen.ui.view.PostListFragment
import dagger.Component

@PostListScope
@Component(modules = arrayOf(PostListModule::class),
           dependencies = arrayOf(AppComponent::class))
interface PostListComponent {

    fun inject(view: PostListFragment)
}