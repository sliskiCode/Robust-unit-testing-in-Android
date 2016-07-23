package com.code.sliski.api.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Post constructor(@SerializedName("post_id") val postId: Long,
                            @SerializedName("score") val score: Int,
                            @SerializedName("link") val link: String) : Parcelable {

    constructor(parcel: Parcel) : this(parcel.readLong(),
                                       parcel.readInt(),
                                       parcel.readString())

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<Post> = object : Parcelable.Creator<Post> {
            override fun createFromParcel(parcel: Parcel): Post {
                return Post(parcel)
            }

            override fun newArray(size: Int): Array<Post?> {
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
}