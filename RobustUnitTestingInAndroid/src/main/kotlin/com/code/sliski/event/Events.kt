package com.code.sliski.event

import com.code.sliski.model.Post

public class GetPostsResponseEvent(public val posts : List<Post>)
public class OnPostClickedEvent(public val post : Post)