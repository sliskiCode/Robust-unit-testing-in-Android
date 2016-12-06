package com.code.sliski.postlistscreen.ui.view

import com.code.sliski.postlistscreen.ui.model.PresentationPost

interface View {
    fun showPosts(posts: List<PresentationPost>)
    fun showPostDetailsScreen(post: PresentationPost)
    fun notifyOnPostClicked(post: PresentationPost)
    fun showError(message: String)
}