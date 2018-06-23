package com.example.android.newsapp;

public class News {

    private String mTitle, mCategory, mUrl;

    public News(String title, String category, String url) {
        mTitle = title;
        mCategory = category;
        mUrl = url;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getCategory() {
        return mCategory;
    }

    public String getUrl() {
        return mUrl;
    }
}
