package com.example.qasim.news;

import com.example.qasim.news.model.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonParser {
    public List<News> extract(String response) {

        try {
            JSONObject jsonObject=new JSONObject(response);
            JSONArray articles=jsonObject.getJSONArray("articles");
            List<News> list=new ArrayList<>();

            for (int i=0;i<articles.length();i++){
                JSONObject current=articles.getJSONObject(i);

                String title=current.getString("title");
                String description=current.getString("description");
                String urlToImage=current.getString("urlToImage");

                News converted=new News(title,description,urlToImage);
                list.add(converted);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
