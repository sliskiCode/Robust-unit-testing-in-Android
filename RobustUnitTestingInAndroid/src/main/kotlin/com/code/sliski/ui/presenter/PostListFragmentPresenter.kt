package com.code.sliski.ui.presenter

import com.code.sliski.ui.fragment.PostListFragmentView

public trait PostListFragmentPresenter {
    public fun getPosts()
    public fun onItemClick(position: Int, isTablet: Boolean)
    public fun setView(view: PostListFragmentView?)
    public fun getView() : PostListFragmentView?
}