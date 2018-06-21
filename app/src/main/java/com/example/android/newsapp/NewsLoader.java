package com.example.android.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.Collection;
import java.util.List;

/**
 * Created by Kai Morten on 20.06.2018.
 */

public class NewsLoader extends AsyncTaskLoader<List<News>> {

    private final String TAG = NewsLoader.class.getSimpleName();

    private String mUrl;

    public NewsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<News> loadInBackground() {
        if(mUrl == null) {
            return null;
        }
        List<News> news = QueryUtils.fetchNewsData(mUrl);
        return news;
    }
}
