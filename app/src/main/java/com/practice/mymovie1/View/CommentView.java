package com.practice.mymovie1.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.practice.mymovie1.R;

public class CommentView extends LinearLayout {
    TextView CommentIdView;
    TextView CommentTimeView;
    RatingBar CommentRatingBar;
    TextView CommentView;
    public CommentView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.comment_item_view,this,true);

        CommentIdView = findViewById(R.id.CommentIdView);
        CommentTimeView = findViewById(R.id.CommentTimeView);
        CommentRatingBar = findViewById(R.id.CommentRatingBar);
        CommentView  = findViewById(R.id.CommentView);
    }

    public void SetCommentId(String ID){
        CommentIdView.setText(ID);
    }
    public void SetCommentTime(String Time){
        CommentTimeView.setText(Time);
    }
    public void SetCommentRatingBar(float Rating){
        if(Rating>=0 && Rating<=5.0)
            CommentRatingBar.setRating(Rating);
    }
    public void SetCommnet(String Comment){
        CommentView.setText(Comment);
    }


}
