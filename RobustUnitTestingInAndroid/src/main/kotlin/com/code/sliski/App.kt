package com.code.sliski

import android.app.Application
import com.code.sliski.postlistscreen.di.DaggerPostListComponent
import com.code.sliski.postlistscreen.di.PostListComponent
import com.code.sliski.postlistscreen.di.PostListModule

class App : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent.builder()
                          .appModule(AppModule(this))
                          .build()
    }

    var postListComponent: PostListComponent? = null

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