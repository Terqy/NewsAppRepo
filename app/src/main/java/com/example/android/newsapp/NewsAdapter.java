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
    private List<News> newsList = new ArrayList<>();

    public NewsAdapter(Activity context, List<News> news) {
        super(context, 0, news);
        mContext = context;
        newsList = news;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listView = convertView;
        if(listView == null) {
            listView = LayoutInflater.from(getContext()).
                    inflate(R.layout.list, parent, false);
        }
        News currentNews = newsList.get(position);

        TextView title = (TextView) listView.findViewById(R.id.title);
        TextView publisher = (TextView) listView.findViewById(R.id.type);

        title.setText(currentNews.getTitle());
        publisher.setText(currentNews.getType());

        return listView;
    }
}
