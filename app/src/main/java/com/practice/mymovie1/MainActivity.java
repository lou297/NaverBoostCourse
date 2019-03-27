package com.practice.mymovie1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.practice.mymovie1.Adapter.CommentAdapter;
import com.practice.mymovie1.DataClass.CommentItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final static int WRITE_COMMENT_FROM_MAIN = 1000;
    private final static int[] ViewingClass = {0,12,15,19};
    private TextView ThumbUpRateText;
    private TextView ThumbDownRateText;
    private Button ThumbUp;
    private Button ThumbDown;
    private TextView WriteCommentBut;
    private Button ViewAllCommentBut;

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

        ThumbUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlThumbUpRate();
            }
        });
        ThumbDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlThumbDownRate();
            }
        });
        WriteCommentBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCommentWrite();
            }
        });
        ViewAllCommentBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCommentList();
            }
        });
    }

    private void initView(){
        ThumbUpRateText = findViewById(R.id.ThumbUpRate);
        ThumbDownRateText = findViewById(R.id.ThumbDownRate);
        ThumbUp = findViewById(R.id.ThumbUpBut);
        ThumbDown = findViewById(R.id.ThumbDownBut);
        WriteCommentBut = findViewById(R.id.WriteCommentBut_Main);
        ViewAllCommentBut = findViewById(R.id.ViewAllCommentBut);

    }

    private void EnterMovieInfo(){
        TextView MoviePlayDateView = findViewById(R.id.MoviePlayDateView);
        MoviePlayDateView.setText(String.format(getString(R.string.MoviePlayDate),2014,7,23));
        TextView MovieShowTimesView = findViewById(R.id.MovieShowTimesView);
        MovieShowTimesView.setText(String.format(getString(R.string.MovieShowTimes),137));
        TextView TicketRankView = findViewById(R.id.TicketRankView);
        TicketRankView.setText(String.format(getString(R.string.TicketRank),5));
        TextView TicketPercentsView = findViewById(R.id.TicketPercentsView);
        TicketPercentsView.setText(String.format(getString(R.string.TicketPercents),1,8));
    }

    private void loadComment(){
        if(list!=null){
            list.add(new CommentItem("kim71**","10분 전",4.0F,"적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요."));
            list.add(new CommentItem("aaa123**","1시간 전",0.5F,"너무 재미없다"));
            list.add(new CommentItem("kim71**","10분 전",3.5F,"적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요."));
            list.add(new CommentItem("kim71**","10분 전",2.1F,"적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요."));

            CommentAdapter adapter = new CommentAdapter(this,list);
            ListView listView = findViewById(R.id.CommentListView_Main);
            listView.setAdapter(adapter);
        }
    }

    private void ControlThumbUpRate() {
        if(ThumbUpCond){
            ThumbUpRate--;
            ThumbUpCond = false;
            ThumbUp.setBackgroundResource(R.drawable.ic_thumb_up);
        } else{
            ThumbUpRate++;
            ThumbUpCond = true;
            ThumbUp.setBackgroundResource(R.drawable.ic_thumb_up_selected);
            if(ThumbDownCond)
                ControlThumbDownRate();
        }
        ThumbUpRateText.setText(String.valueOf(ThumbUpRate));
    }

    private void ControlThumbDownRate() {
        if(ThumbDownCond){
            ThumbDownRate--;
            ThumbDownCond = false;
            ThumbDown.setBackgroundResource(R.drawable.ic_thumb_down);
        } else {
            ThumbDownRate++;
            ThumbDownCond = true;
            ThumbDown.setBackgroundResource(R.drawable.ic_thumb_down_selected);
            if(ThumbUpCond)
                ControlThumbUpRate();
        }
        ThumbDownRateText.setText(String.valueOf(ThumbDownRate));
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
        super.onActivityResult(requestCode, resultCode, data);
    }
}
