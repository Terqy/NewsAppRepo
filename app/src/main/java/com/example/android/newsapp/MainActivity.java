package com.example.android.newsapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements android.app.LoaderManager.LoaderCallbacks<List<News>> {

    private final String TAG = MainActivity.class.getSimpleName();

    public String NEWS_REQUEST_URL = "https://content.guardianapis.com/search?api-key=13011c7a-c539-46f5-ae32-be5f28f60425";
    private static final int NEWS_LOADER_ID = 1;
    private ArrayList<News> newsList;
    private ListView listView;
    private TextView emptyTextView;
    private View loadingIndicator;

    NewsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emptyTextView = (TextView) findViewById(R.id.emptyView);
        loadingIndicator = (View) findViewById(R.id.loading_indicator);

        listView = (ListView) findViewById(R.id.listView);
        newsList = new ArrayList<>();

        adapter = new NewsAdapter(this, newsList);
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

        ConnectivityManager conManager =(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conManager.getActiveNetworkInfo();

        if(networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(NEWS_LOADER_ID, null, this);
        } else {
            loadingIndicator.setVisibility(View.GONE);

            emptyTextView.setText(R.string.emptyView);
        }
    }

    @Override
    public android.content.Loader<List<News>> onCreateLoader(int i, Bundle bundle) {

        Uri baseUri = Uri.parse(NEWS_REQUEST_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        return new NewsLoader(this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(android.content.Loader<List<News>> loader, List<News> news) {
        loadingIndicator.setVisibility(View.GONE);

        if(news != null && !news.isEmpty()) {
            updateUi(news);
        } else {
            emptyTextView.setText(R.string.emptyView);
        }
    }

    @Override
    public void onLoaderReset(android.content.Loader<List<News>> loader) {
        adapter.clear();
    }

    private void updateUi(List<News> news) {
        adapter.clear();
        adapter.addAll(news);
    }
}
