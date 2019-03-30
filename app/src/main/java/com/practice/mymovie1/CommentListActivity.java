package com.practice.mymovie1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.practice.mymovie1.Adapter.CommentAdapter;
import com.practice.mymovie1.DataClass.CommentItem;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CommentListActivity extends AppCompatActivity {
    private final static int WRITE_COMMENT_FROM_LIST = 1001;
    private final static int[] ViewingClass = {0,12,15,19};// 관람 등급

    private TextView movieTitleView;
    private ImageView viewingClassImageView;
    private RatingBar commentRatingBar;
    private TextView movieCreditsView;
    private TextView numOfReviewersView;
    private TextView writeCommentView;
    private ListView commentListView;
    private ArrayList<CommentItem> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_list);


        initView();
        list = new ArrayList<>();
        readIntent();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                onBackPressed();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void initView(){
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        movieTitleView = findViewById(R.id.movieTitle_CommentList);
        viewingClassImageView = findViewById(R.id.viewingClassImageView_CommentList);
        commentRatingBar = findViewById(R.id.commentRatingBar_CommentLIst);
        movieCreditsView = findViewById(R.id.movieCreditsView);
        numOfReviewersView = findViewById(R.id.numOfReviewersView);

        writeCommentView = findViewById(R.id.writeCommentBut_CommentList);
        commentListView = findViewById(R.id.commentListView_CommentList);
        writeCommentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCommentWrite();
            }
        });

    }

    private void readIntent(){
        Intent intent = getIntent();
        if(intent!=null){
            if(intent.hasExtra("MovieTitle") && intent.hasExtra("ViewingClass") && intent.hasExtra("MovieCredits")
                    && intent.hasExtra("NumOfReviewers") && intent.hasExtra("CommentList")){
                String MovieTitle = intent.getStringExtra("MovieTitle");
                int ViewingClass =  intent.getIntExtra("ViewingClass",0);
                float MovieCredits = intent.getFloatExtra("MovieCredits",10.0F);
                int NumOfReviewers = intent.getIntExtra("NumOfReviewers",0);
                list = intent.getParcelableArrayListExtra("CommentList");

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
                commentRatingBar.setRating(MovieCredits/2);
                movieCreditsView.setText(String.valueOf(MovieCredits));

                //천 단위마다 ',' 넣어주기
                DecimalFormat format = new DecimalFormat("###,###");
                numOfReviewersView.setText(String.format(getString(R.string.numOfReviewers_CommentList),format.format(NumOfReviewers)));

                if(!list.isEmpty()){
                    CommentAdapter adapter = new CommentAdapter(this,list);
                    commentListView.setAdapter(adapter);
                }
            }
        }
    }

    private void goToCommentWrite(){
        Intent intent = new Intent(this,CommentWriteActivity.class);
        intent.putExtra("MovieTitle","군도");
        intent.putExtra("ViewingClass",ViewingClass[2]);
        startActivityForResult(intent,WRITE_COMMENT_FROM_LIST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==WRITE_COMMENT_FROM_LIST){
            if(resultCode==RESULT_OK){
                Toast.makeText(this,"작성 확인 됨", Toast.LENGTH_SHORT).show();
            }
            else if(resultCode==RESULT_CANCELED) {
                Toast.makeText(this,"작성 취소 됨", Toast.LENGTH_SHORT).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
