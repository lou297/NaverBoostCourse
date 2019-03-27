package com.practice.mymovie1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.practice.mymovie1.Adapter.CommentAdapter;
import com.practice.mymovie1.DataClass.CommentItem;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CommentListActivity extends AppCompatActivity {
    private final static int WRITE_COMMENT_FROM_LIST = 1001;
    private final static int[] ViewingClass = {0,12,15,19};// 관람 등급

    private TextView MovieTitleView;
    private ImageView ViewingClassImageView;
    private RatingBar CommentRatingBar;
    private TextView MovieCreditsView;
    private TextView NumOfReviewersView;
    private TextView WriteCommentView;
    private ListView CommentListView;
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

        MovieTitleView = findViewById(R.id.MovieTitle_CommentList);
        ViewingClassImageView = findViewById(R.id.ViewingClassImageView_CommentList);
        CommentRatingBar = findViewById(R.id.CommentRatingBar_CommentLIst);
        MovieCreditsView = findViewById(R.id.MovieCreditsView);
        NumOfReviewersView = findViewById(R.id.NumOfReviewersView);

        WriteCommentView = findViewById(R.id.WriteCommentBut_CommentList);
        CommentListView = findViewById(R.id.CommentListView_CommentList);

        WriteCommentView.setOnClickListener(new View.OnClickListener() {
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
                CommentRatingBar.setRating(MovieCredits/2);
                MovieCreditsView.setText(String.valueOf(MovieCredits));

                //천 단위마다 ',' 넣어주기
                DecimalFormat format = new DecimalFormat("###,###");
                NumOfReviewersView.setText(String.format(getString(R.string.NumOfReviewers_CommentList),format.format(NumOfReviewers)));

                if(!list.isEmpty()){
                    CommentAdapter adapter = new CommentAdapter(this,list);
                    CommentListView.setAdapter(adapter);
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
}
