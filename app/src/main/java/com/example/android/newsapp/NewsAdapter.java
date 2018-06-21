package com.example.android.newsapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.newsapp.News;
import com.example.android.newsapp.R;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends ArrayAdapter<News> {

    private Context mContext;
    private List<News> newsList;

    public NewsAdapter(Context context, ArrayList<News> news) {
        super(context, 0, news);
        mContext = context;
        newsList = news;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null) {
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list, parent, false);
        }
        News currentNews = newsList.get(position);

        String TITLE = News.getTitle();
        String TYPE = News.getType();

        TextView title = (TextView) listItem.findViewById(R.id.title);
        title.setText(TITLE);

        TextView type = (TextView) listItem.findViewById(R.id.type);
        type.setText(TYPE);

        return listItem;
    }
}
