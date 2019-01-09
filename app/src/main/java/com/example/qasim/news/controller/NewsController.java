package com.example.qasim.news.controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qasim.news.R;
import com.example.qasim.news.model.News;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsController extends RecyclerView.Adapter<NewsController.NewsViewHolder> {
    private List<News> items;
    private Context context;

    public NewsController(List<News> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View root = layoutInflater.inflate(R.layout.item_news, viewGroup, false);
        return new NewsViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder newsViewHolder, int i) {
        News item = items.get(i);
        newsViewHolder.title.setText(item.getTitle());
        newsViewHolder.description.setText(item.getDescription());
        Picasso.get().load(item.getUrlToImage()).into(newsViewHolder.urlImage);
    }

    @Override
    public int getItemCount() {
        if (items == null)
            return 0;
        return items.size();
    }


    public class NewsViewHolder extends RecyclerView.ViewHolder {
        private TextView title, description;
        private ImageView urlImage;
        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txt_title);
            description = itemView.findViewById(R.id.txt_description);
            urlImage = itemView.findViewById(R.id.img_view);
        }
    }
}