package com.practice.mymovie1.DataClass;

public class CommentItem {
    String Id;
    String CommentTime;
    float CommentRating;
    String Comment;

    public CommentItem(String id, String commentTime, float commentRating, String comment) {
        Id = id;
        CommentTime = commentTime;
        CommentRating = commentRating;
        Comment = comment;
    }

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
}
