package com.example.android.newsapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements android.app.LoaderManager.LoaderCallbacks<List<News>> {

    private final String TAG = MainActivity.class.getSimpleName();

    public String NEWS_REQUEST_URL = "https://content.guardianapis.com/search?api-key=13011c7a-c539-46f5-ae32-be5f28f60425";
    private static final int NEWS_LOADER_ID = 1;
    private List<News> newsList;

    NewsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.listView);
        newsList = new ArrayList<>();
        adapter = new NewsAdapter(this, new ArrayList<News>());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                News news = adapter.getItem(position);
                Uri newsUri = Uri.parse(news.getUrl());
                Intent i = new Intent(Intent.ACTION_VIEW, newsUri);
                startActivity(i);
            }
        });

        android.app.LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(NEWS_LOADER_ID, null, this);
    }

    @Override
    public android.content.Loader<List<News>> onCreateLoader(int i, Bundle bundle) {

        Uri baseUri = Uri.parse(NEWS_REQUEST_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        return new NewsLoader(this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(android.content.Loader<List<News>> loader, List<News> news) {
        adapter.clear();
        if(news != null && !news.isEmpty()) {
            adapter.addAll(news);
        }
    }

    @Override
    public void onLoaderReset(android.content.Loader<List<News>> loader) {

        adapter.clear();
    }
}
