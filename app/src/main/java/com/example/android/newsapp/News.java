package com.example.android.newsapp;

public class News {

    private static String mTitle, mType, mUrl;

    public News(String title, String type, String url) {
        mTitle = title;
        mType = type;
        mUrl = url;
    }

    public static String getTitle() {
        return mTitle;
    }

    public static String getType() {
        return mType;
    }

    public static String getUrl() {
        return mUrl;
    }
}
