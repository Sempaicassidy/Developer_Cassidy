package com.cassidy.agromarket.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cassidy.agromarket.R;
import com.cassidy.agromarket.adapters.NewsAdapter;
import com.cassidy.agromarket.models.News;
import com.cassidy.agromarket.utilities.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        newsAdapter = new NewsAdapter(this, new ArrayList<>());
        recyclerView.setAdapter(newsAdapter);

        databaseHelper = new DatabaseHelper(this);

        // Retrieve news data from the database
        List<News> newsList = databaseHelper.getAllNews();

        // Update the RecyclerView with the retrieved news data
        newsAdapter.setNewsList(newsList);
    }
}
