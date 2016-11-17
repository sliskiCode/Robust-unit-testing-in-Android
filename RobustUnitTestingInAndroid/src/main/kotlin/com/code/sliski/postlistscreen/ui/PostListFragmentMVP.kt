package com.code.sliski.postlistscreen.ui

import com.code.sliski.api.model.Post

interface PostListFragmentMVP {

    interface View {
        fun showPosts(posts: List<Post>)
        fun showPostDetailsScreen(post: Post)
        fun notifyOnPostClicked(post: Post)
        fun showError(message: String)
    }

    interface Presenter {
        fun present()
        fun onItemClick(position: Int, isTablet: Boolean)
        fun attach(view: View?)
        fun detach()
        fun unsubscribe()
    }
}