package com.code.sliski.ui.presenter

public trait PostListFragmentPresenter {
    public fun getPosts()
    public fun onItemClick(position: Int, isTablet: Boolean)
}