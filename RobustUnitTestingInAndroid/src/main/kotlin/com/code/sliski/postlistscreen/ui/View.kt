package com.code.sliski.postlistscreen.ui

import com.code.sliski.api.model.Post

interface View {
    fun showPosts(posts: List<Post>)
    fun showPostDetailsScreen(post: Post)
    fun notifyOnPostClicked(post: Post)
    fun showError(message: String)
}