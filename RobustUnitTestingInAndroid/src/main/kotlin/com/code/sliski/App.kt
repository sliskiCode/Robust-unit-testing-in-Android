package com.code.sliski

import android.app.Application
import com.code.sliski.postlistscreen.di.DaggerPostListComponent
import com.code.sliski.postlistscreen.di.PostListComponent
import com.code.sliski.postlistscreen.di.PostListModule

class App : Application() {

    lateinit var component: AppComponent

    private var postListComponent: PostListComponent? = null

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder()
                                      .appModule(AppModule(this))
                                      .build()
    }

    fun postListComponent(): PostListComponent {
        if (postListComponent == null) {
            postListComponent = DaggerPostListComponent.builder()
                                                       .appComponent(component)
                                                       .postListModule(PostListModule())
                                                       .build()
        }

        return postListComponent!!
    }

    fun releasePostListComponent() {
        postListComponent = null
    }
}