package com.code.sliski.postlistscreen.ui

import com.code.sliski.api.model.Post

interface PostListFragmentMVP {

    interface View {
        fun setAdapter(posts: List<Post>)
        fun notifyOnPostClicked(post: Post)
        fun addToBackStack(post: Post)
    }

    interface Presenter {
        fun loadData()
        fun onItemClick(position: Int, isTablet: Boolean)
        fun attachView(view: View?)
        fun detachView()
    }
}