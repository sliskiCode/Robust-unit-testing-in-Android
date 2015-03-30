package com.code.sliski.model

import android.os.Parcel
import com.google.gson.annotations.SerializedName
import android.os.Parcelable

public data class Post(SerializedName("post_id") public val postId: Long,
                  SerializedName("score") public val score: Int,
                  SerializedName("link") public val link: String?) : Parcelable {

    public val CREATOR: Parcelable.Creator<Post> = object : Parcelable.Creator<Post> {
        override fun createFromParcel(source: Parcel): Post {
            return Post(source.readLong(), source.readInt(), source.readString())
        }

        override fun newArray(size: Int): Array<Post?> {
            return arrayOfNulls(size)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeLong(postId)
        dest.writeInt(score)
        dest.writeString(link)
    }
}
