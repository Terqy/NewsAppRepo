package com.example.android.newsapp;

public class News {

    private String mTitle, mCategory, mUrl, mAuthor, mTime;

    public News(String title, String category, String url, String author, String time) {
        mTitle = title;
        mCategory = category;
        mUrl = url;
        mAuthor = author;
        mTime = time;
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

    public String getAuthor() {
        return mAuthor;
    }

    public String getTime() {
        return mTime;
    }
}
