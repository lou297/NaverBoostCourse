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
    private TextView movieTitleView;
    private ImageView viewingClassImageView;
    private EditText editCommentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_write);

        initView();
        readIntent();
    }
    private void initView() {
        movieTitleView = findViewById(R.id.movieTitle_CommentWrite);
        viewingClassImageView = findViewById(R.id.viewingClassImageView_CommentWrite);
        editCommentView = findViewById(R.id.editComment_CommentWrite);
        Button saveBut = findViewById(R.id.saveBut_CommentWrite);
        Button cancelBut = findViewById(R.id.cancelBut_CommentWrite);

        saveBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                finish();
            }
        });
        cancelBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
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
                movieTitleView.setText(MovieTitle);
                switch (ViewingClass){
                    case 0:
                        viewingClassImageView.setBackgroundResource(R.drawable.ic_all);
                        break;
                    case 12:
                        viewingClassImageView.setBackgroundResource(R.drawable.ic_12);
                        break;
                    case 15:
                        viewingClassImageView.setBackgroundResource(R.drawable.ic_15);
                        break;
                    case 19:
                        viewingClassImageView.setBackgroundResource(R.drawable.ic_19);
                        break;
                }
            }
        }
    }
}
