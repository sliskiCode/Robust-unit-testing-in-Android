package com.code.sliski.api.model

import com.google.gson.annotations.SerializedName

class PostWrapper<out Post>(@SerializedName("items") val posts: List<Post>)