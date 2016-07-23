package com.code.sliski.postdetailsscreen

import com.code.sliski.api.model.Post

interface OnPostClickListener {

    fun onPostClick(post: Post)
}