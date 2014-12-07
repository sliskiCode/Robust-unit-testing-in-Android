package com.code.sliski.ui.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public final class PostWrapper<Post>{

   @SerializedName("items") private List<Post> mPosts;

   public List<Post> getPosts() {
      return mPosts;
   }
}