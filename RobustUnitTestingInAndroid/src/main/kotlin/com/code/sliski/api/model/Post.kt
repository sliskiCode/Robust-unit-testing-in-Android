package com.code.sliski.api.model

import com.google.gson.annotations.SerializedName

data class Post constructor(@SerializedName("post_id") val postId: Long,
                            @SerializedName("score") val score: Int,
                            @SerializedName("link") val link: String)