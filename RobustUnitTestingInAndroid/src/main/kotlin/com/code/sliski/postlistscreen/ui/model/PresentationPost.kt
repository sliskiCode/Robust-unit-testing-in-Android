package com.code.sliski.postlistscreen.ui.model

import android.os.Parcel
import android.os.Parcelable
import com.code.sliski.api.model.Post

class PresentationPost(post: Post,
                       val postId: Long = post.postId,
                       val score: Int = post.score,
                       val link: String = post.link) : Parcelable {


    constructor(parcel: Parcel) : this(Post(parcel.readLong(),
                                            parcel.readInt(),
                                            parcel.readString()))

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<PresentationPost> = object : Parcelable.Creator<PresentationPost> {
            override fun createFromParcel(parcel: Parcel): PresentationPost {
                return PresentationPost(parcel)
            }

            override fun newArray(size: Int): Array<PresentationPost?> {
                return arrayOf()
            }
        }
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeLong(postId)
        dest.writeInt(score)
        dest.writeString(link)
    }

    override fun describeContents() = 0

    override fun toString() = "$link $score"
}