package com.example.android.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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
            listItem = LayoutInflater.from(mContext).
                    inflate(R.layout.list, parent, false);
        }
        News currentNews = newsList.get(position);

        String title = currentNews.getTitle();
        String category = currentNews.getCategory();
        String author = currentNews.getAuthor();

        TextView textViewTitle = (TextView) listItem.findViewById(R.id.title);
        textViewTitle.setText(title);

        TextView textViewCategory = (TextView) listItem.findViewById(R.id.type);
        textViewCategory.setText("Category: " + category);

        TextView textViewAuthor = (TextView) listItem.findViewById(R.id.author);
        textViewAuthor.setText("Author: " + author);

        return listItem;
    }
}
