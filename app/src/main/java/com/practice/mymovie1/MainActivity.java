package com.practice.mymovie1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.practice.mymovie1.Adapter.CommentAdapter;
import com.practice.mymovie1.DataClass.CommentItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final static int WRITE_COMMENT_FROM_MAIN = 1000;
    private final static int[] ViewingClass = {0,12,15,19};
    private TextView thumbUpRateText;
    private TextView thumbDownRateText;
    private Button thumbUp;
    private Button thumbDown;
    private TextView writeCommentBut;
    private Button viewAllCommentBut;

    private int ThumbUpRate = 0;
    private int ThumbDownRate = 0;
    private boolean ThumbUpCond = false;
    private boolean ThumbDownCond = false;

    private ArrayList<CommentItem> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        EnterMovieInfo();
        list = new ArrayList<>();
        loadComment();

        thumbUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlThumbUpRate();
            }
        });
        thumbDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlThumbDownRate();
            }
        });
        writeCommentBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCommentWrite();
            }
        });
        viewAllCommentBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCommentList();
            }
        });
    }

    private void initView(){
        thumbUpRateText = findViewById(R.id.thumbUpRate);
        thumbDownRateText = findViewById(R.id.thumbDownRate);
        thumbUp = findViewById(R.id.thumbUpBut);
        thumbDown = findViewById(R.id.thumbDownBut);
        writeCommentBut = findViewById(R.id.writeCommentBut_Main);
        viewAllCommentBut = findViewById(R.id.viewAllCommentBut);

    }

    private void EnterMovieInfo(){
        TextView moviePlayDateView = findViewById(R.id.moviePlayDateView);
        moviePlayDateView.setText(String.format(getString(R.string.moviePlayDate),2014,7,23));
        TextView movieShowTimesView = findViewById(R.id.movieShowTimesView);
        movieShowTimesView.setText(String.format(getString(R.string.movieShowTimes),137));
        TextView ticketRankView = findViewById(R.id.ticketRankView);
        ticketRankView.setText(String.format(getString(R.string.ticketRank),5));
        TextView ticketPercentsView = findViewById(R.id.ticketPercentsView);
        ticketPercentsView.setText(String.format(getString(R.string.ticketPercents),1,8));
    }

    private void loadComment(){
        if(list!=null){
            list.add(new CommentItem("kim71**", "10분 전", 4.0F, "적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요."));
            list.add(new CommentItem("aaa123**", "1시간 전", 0.5F, "너무 재미없다"));
            list.add(new CommentItem("kim71**", "10분 전", 3.5F, "적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요."));
            list.add(new CommentItem("kim71**", "10분 전", 2.1F, "적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요."));

            CommentAdapter adapter = new CommentAdapter(this,list);
            ListView listView = findViewById(R.id.commentListView_Main);
            listView.setAdapter(adapter);
        }
    }

    private void controlThumbUpRate() {
        if(ThumbUpCond){
            ThumbUpRate--;
            ThumbUpCond = false;
            thumbUp.setBackgroundResource(R.drawable.ic_thumb_up);
        } else{
            ThumbUpRate++;
            ThumbUpCond = true;
            thumbUp.setBackgroundResource(R.drawable.ic_thumb_up_selected);
            if(ThumbDownCond)
                controlThumbDownRate();
        }
        thumbUpRateText.setText(String.valueOf(ThumbUpRate));
    }

    private void controlThumbDownRate() {
        if(ThumbDownCond){
            ThumbDownRate--;
            ThumbDownCond = false;
            thumbDown.setBackgroundResource(R.drawable.ic_thumb_down);
        } else {
            ThumbDownRate++;
            ThumbDownCond = true;
            thumbDown.setBackgroundResource(R.drawable.ic_thumb_down_selected);
            if(ThumbUpCond)
                controlThumbUpRate();
        }
        thumbDownRateText.setText(String.valueOf(ThumbDownRate));
    }

    private void goToCommentList(){
        Intent intent = new Intent(this,CommentListActivity.class);
        intent.putExtra("MovieTitle","군도");
        intent.putExtra("ViewingClass",ViewingClass[2]);
        intent.putExtra("MovieCredits",8.2F);
        intent.putExtra("NumOfReviewers",1142);
        intent.putParcelableArrayListExtra("CommentList",list);
        startActivity(intent);
    }

    private void goToCommentWrite(){
        Intent intent = new Intent(this,CommentWriteActivity.class);
        intent.putExtra("MovieTitle","군도");
        intent.putExtra("ViewingClass",ViewingClass[2]);
        startActivityForResult(intent,WRITE_COMMENT_FROM_MAIN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==WRITE_COMMENT_FROM_MAIN){
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
