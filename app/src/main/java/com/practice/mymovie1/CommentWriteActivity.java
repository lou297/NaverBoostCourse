package com.practice.mymovie1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class CommentWriteActivity extends AppCompatActivity {
    private TextView MovieTitleView;
    private ImageView ViewingClassImageView;
    private EditText EditCommentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_write);

        initView();
        readIntent();
    }
    private void initView() {
        MovieTitleView = findViewById(R.id.MovieTitle_CommentWrite);
        ViewingClassImageView = findViewById(R.id.ViewingClassImageView_CommentWrite);
        EditCommentView = findViewById(R.id.EditComment_CommentWrite);
        Button SaveBut = findViewById(R.id.SaveBut_CommentWrite);
        Button CancelBut = findViewById(R.id.CancelBut_CommentWrite);

        SaveBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                finish();
            }
        });
        CancelBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void readIntent() {
        Intent intent = getIntent();
        if(intent!=null){
            if(intent.hasExtra("MovieTitle") && intent.hasExtra("ViewingClass")){
                String MovieTitle = intent.getStringExtra("MovieTitle");
                int ViewingClass = intent.getIntExtra("ViewingClass",0);
                MovieTitleView.setText(MovieTitle);
                switch (ViewingClass){
                    case 0:
                        ViewingClassImageView.setBackgroundResource(R.drawable.ic_all);
                        break;
                    case 12:
                        ViewingClassImageView.setBackgroundResource(R.drawable.ic_12);
                        break;
                    case 15:
                        ViewingClassImageView.setBackgroundResource(R.drawable.ic_15);
                        break;
                    case 19:
                        ViewingClassImageView.setBackgroundResource(R.drawable.ic_19);
                        break;
                }
            }
        }
    }
}
