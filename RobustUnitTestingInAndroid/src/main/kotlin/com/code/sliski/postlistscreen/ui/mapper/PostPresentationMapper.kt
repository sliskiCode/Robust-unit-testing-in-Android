package com.code.sliski.postlistscreen.ui.mapper

import com.code.sliski.api.model.Post
import com.code.sliski.postlistscreen.ui.model.PresentationPost

interface PostPresentationMapper {
    fun map(posts: List<Post>): List<PresentationPost>
}