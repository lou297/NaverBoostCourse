package com.practice.mymovie1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.practice.mymovie1.Adapter.CommentAdapter;
import com.practice.mymovie1.DataClass.CommentItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    int ThumbUpRate = 0;
    int ThumbDownRate = 0;
    boolean ThumbUpCond = false;
    boolean ThumbDownCond = false;
    TextView ThumbUpRateText;
    TextView ThumbDownRateText;
    Button ThumbUp;
    Button ThumbDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ThumbUpRateText = findViewById(R.id.ThumbUpRate);
        ThumbDownRateText = findViewById(R.id.ThumbDownRate);

        ThumbUp = findViewById(R.id.ThumbUpBut);
        ThumbDown = findViewById(R.id.ThumbDownBut);

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

        LoadComment();
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

    private void LoadComment(){
        ArrayList<CommentItem> list = new ArrayList<>();
        list.add(new CommentItem("kim71**","10분 전",4.0F,"적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요."));
        list.add(new CommentItem("aaa123**","1시간 전",0.5F,"너무 재미없다"));
        list.add(new CommentItem("kim71**","10분 전",3.5F,"적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요."));
        list.add(new CommentItem("kim71**","10분 전",2.1F,"적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요."));


        CommentAdapter adapter = new CommentAdapter(this,list);
        ListView listView = findViewById(R.id.CommentListView);
        listView.setAdapter(adapter);
    }

}
