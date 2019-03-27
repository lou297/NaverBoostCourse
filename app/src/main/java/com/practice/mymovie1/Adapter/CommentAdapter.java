package com.practice.mymovie1.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.practice.mymovie1.DataClass.CommentItem;
import com.practice.mymovie1.View.CommentView;

import java.util.ArrayList;

public class CommentAdapter extends BaseAdapter {
    private ArrayList<CommentItem> list;
    private Context context;

    public CommentAdapter(Context context,ArrayList<CommentItem> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommentView view;
        if(convertView==null) {
            view = new CommentView(context);
        } else {
            view = (CommentView) convertView;
        }
        CommentItem item = list.get(position);

        view.setCommentInfo(item);
        return view;
    }
}
