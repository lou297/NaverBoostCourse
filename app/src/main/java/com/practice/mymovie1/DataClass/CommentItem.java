package com.practice.mymovie1.DataClass;

import android.os.Parcel;
import android.os.Parcelable;

public class CommentItem implements Parcelable {
    private String Id;
    private String CommentTime;
    private float CommentRating;
    private String Comment;

    public CommentItem(String id, String commentTime, float commentRating, String comment) {
        Id = id;
        CommentTime = commentTime;
        CommentRating = commentRating;
        Comment = comment;
    }

    public CommentItem(Parcel in) {
        readFromParcel(in);
    }

    public static final Creator<CommentItem> CREATOR = new Creator<CommentItem>() {
        @Override
        public CommentItem createFromParcel(Parcel in) {
            return new CommentItem(in);
        }

        @Override
        public CommentItem[] newArray(int size) {
            return new CommentItem[size];
        }
    };

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getCommentTime() {
        return CommentTime;
    }

    public void setCommentTime(String commentTime) {
        CommentTime = commentTime;
    }

    public float getCommentRating() {
        return CommentRating;
    }

    public void setCommentRating(float commentRating) {
        CommentRating = commentRating;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Id);
        dest.writeString(CommentTime);
        dest.writeFloat(CommentRating);
        dest.writeString(Comment);
    }

    private void readFromParcel(Parcel in){
        Id = in.readString();
        CommentTime = in.readString();
        CommentRating = in.readFloat();
        Comment = in.readString();
    }
}
