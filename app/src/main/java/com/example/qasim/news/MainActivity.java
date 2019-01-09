package com.example.qasim.news;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.qasim.news.controller.NewsController;
import com.example.qasim.news.model.News;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    ImageButton search;
    EditText editText;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search = findViewById(R.id.img_button);
        editText = findViewById(R.id.editText);
        recyclerView = findViewById(R.id.recycler);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeSearch();
            }
        });
    }

    private void makeSearch() {
        String url="https://newsapi.org/v2/everything?q=";
        String text=editText.getText().toString();
        String apikey="&apiKey=0c534e19d75c43e889e79d6f1c4aba57";
        AsyncHttpClient asyncHttpClient=new AsyncHttpClient();
        asyncHttpClient.get(url + text + apikey, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response=new String(responseBody);
                JsonParser jsonParser=new JsonParser();
                List<News> list=jsonParser.extract(response);

                setupRecycler(list);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    private void setupRecycler(List<News> list) {
        NewsController newsController=new NewsController(list,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(newsController);
    }
}
