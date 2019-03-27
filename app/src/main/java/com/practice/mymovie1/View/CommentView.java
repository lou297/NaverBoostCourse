package com.practice.mymovie1.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.practice.mymovie1.DataClass.CommentItem;
import com.practice.mymovie1.R;

public class CommentView extends LinearLayout {
    private TextView CommentIdView;
    private TextView CommentTimeView;
    private RatingBar CommentRatingBar;
    private TextView CommentView;
    public CommentView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.comment_item_view,this,true);

        CommentIdView = findViewById(R.id.CommentIdView);
        CommentTimeView = findViewById(R.id.CommentTimeView);
        CommentRatingBar = findViewById(R.id.CommentRatingBar_ItemView);
        CommentView  = findViewById(R.id.CommentView);
    }

    public void setCommentInfo(CommentItem item) {
        String ID = item.getId();
        String Time = item.getCommentTime();
        float Rating = item.getCommentRating();
        String Comment = item.getComment();
        CommentIdView.setText(ID);
        CommentTimeView.setText(Time);
        if(Rating>=0 && Rating<=5.0)
            CommentRatingBar.setRating(Rating);
        CommentView.setText(Comment);
    }



}
