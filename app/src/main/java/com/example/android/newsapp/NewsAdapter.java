package com.example.android.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class NewsAdapter extends ArrayAdapter<News> {

    public NewsAdapter(Context context, ArrayList<News> news) {
        super(context, 0, news);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).
                    inflate(R.layout.list, parent, false);
        }
        News currentNews = getItem(position);

        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView publisher = (TextView) convertView.findViewById(R.id.type);

        String TITLE = currentNews.getTitle();
        String TYPE = currentNews.getType();

        title.setText(TITLE);
        publisher.setText(TYPE);

        return convertView;
    }
}
