package com.code.sliski.ui.fragment

import com.code.sliski.model.Post
import java.util.ArrayList

public trait PostListFragmentView {
    fun setAdapter(posts: ArrayList<Post>?)
    fun addToBackStack(post: Post)
}