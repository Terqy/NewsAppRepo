package com.example.android.newsapp;

import android.app.usage.UsageEvents;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class QueryUtils {

    private static final String TAG = QueryUtils.class.getSimpleName();

    public static List<News> fetchNewsData(String requestUrl) {
        URL url = createUrl(requestUrl);
        String jsonResponse = null;

        try{
            jsonResponse = makeHttpRequest(url);
        } catch(IOException e) {
            Log.e(TAG, "Error closing input stream", e);
        }

        List<News> news = extractNewsFromJson(jsonResponse);
        return news;
    }

    public static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e){
            Log.e(TAG, "Error creating url!");
        }

        return url;
    }

    public static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        HttpURLConnection connection = null;
        InputStream inputStream = null;
        if(url == null) {
            return jsonResponse;
        }

        try{
            connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("GET");
            connection.connect();

            if(connection.getResponseCode() == 200) {
                inputStream = connection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
        } catch(IOException e) {
            Log.e(TAG, "Error response code: " + connection.getResponseCode());
        } finally {
            if(connection != null) {
                connection.disconnect();
            }

            if(inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder sb = new StringBuilder();
        if(inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while(line != null) {
                sb.append(line);
                line = reader.readLine();
            }
        }
        return sb.toString();
    }

    private static List<News> extractNewsFromJson(String newsJSON) {

        if(TextUtils.isEmpty(newsJSON)) {
            return null;
        }

       List<News> news = new ArrayList<>();

        try {
            JSONObject baseJson = new JSONObject(newsJSON);
            JSONObject responseJson = baseJson.getJSONObject("response");
            JSONArray newsArray = responseJson.getJSONArray("results");

            for(int i = 0; i < newsArray.length(); i++) {
                JSONObject jsonAtPosition = newsArray.getJSONObject(i);
                String title = jsonAtPosition.getString("webTitle");
                String type = jsonAtPosition.getString("sectionName");
                String url = jsonAtPosition.getString("webUrl");

                News.setTitle(title.toString());
                News.setTitle(type.toString());
                News.setUrl(url.toString());

                News NEWS = new News(title, type, url);
                Log.e(TAG, title + type + url);
                news.add(NEWS);
            }
        } catch (JSONException e) {
            Log.e(TAG, "Trouble fetching jsonResponse");
        }
        return news;
    }
}
