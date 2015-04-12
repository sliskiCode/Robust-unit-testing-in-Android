package com.code.sliski.event

import com.code.sliski.model.Post
import java.util.ArrayList

public class GetPostsResponseEvent(public val posts : ArrayList<Post>)
public class OnPostClickedEvent(public val post : Post)