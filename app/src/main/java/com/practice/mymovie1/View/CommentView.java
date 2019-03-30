package com.practice.mymovie1.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.practice.mymovie1.DataClass.CommentItem;
import com.practice.mymovie1.R;

public class CommentView extends LinearLayout {
    private TextView commentIdView;
    private TextView commentTimeView;
    private RatingBar commentRatingBar;
    private TextView commentView;
    public CommentView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.comment_item_view,this,true);

        commentIdView = findViewById(R.id.commentIdView);
        commentTimeView = findViewById(R.id.commentTimeView);
        commentRatingBar = findViewById(R.id.commentRatingBar_ItemView);
        commentView  = findViewById(R.id.commentView);
    }

    public void setCommentInfo(CommentItem item) {
        String ID = item.getId();
        String Time = item.getCommentTime();
        float Rating = item.getCommentRating();
        String Comment = item.getComment();
        commentIdView.setText(ID);
        commentTimeView.setText(Time);
        if(Rating>=0 && Rating<=5.0)
            commentRatingBar.setRating(Rating);
        commentView.setText(Comment);
    }



}
