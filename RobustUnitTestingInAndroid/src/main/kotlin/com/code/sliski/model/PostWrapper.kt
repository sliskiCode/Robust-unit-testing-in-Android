package com.code.sliski.model

import com.google.gson.annotations.SerializedName

public final class PostWrapper<Post>(SerializedName("items") public val mPosts : List<Post>) {
}