package com.code.sliski.ui.model;

import android.os.Parcel;
import com.google.gson.annotations.SerializedName;

public final class Post implements android.os.Parcelable {

    @SerializedName("post_id") private final long mPostId;
    @SerializedName("score") private final int mScore;
    @SerializedName("link") private final String mLink;

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        public Post createFromParcel(Parcel source) {
            return new Post(source);
        }
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    private Post(long postId, int score, String link) {
        mPostId = postId;
        mScore = score;
        mLink = link;
    }

    private Post(Parcel in) {
        mPostId = in.readLong();
        mScore = in.readInt();
        mLink = in.readString();
    }

    @Override
    public String toString() {
        return String.valueOf(mPostId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mPostId);
        dest.writeInt(mScore);
        dest.writeString(mLink);
    }

    public static class Builder {
        private long mPostId;
        private int mScore;

        private String mLink;

        public static Builder withPostId(long postId) {
            Builder builder = new Builder();
            builder.mPostId = postId;
            return builder;
        }

        public Builder withScore(int score) {
            mScore = score;
            return this;
        }

        public Builder withLink(String link) {
            mLink = link;
            return this;
        }
        public Post build() {
            return new Post(mPostId, mScore, mLink);
        }

    }

    public int getScore() {
        return mScore;
    }

    public String getLink() {
        return mLink;
    }

    public long getPostId() {
        return mPostId;
    }
}
