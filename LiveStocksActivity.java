package com.cassidy.agromarket.activities;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cassidy.agromarket.R;
import com.cassidy.agromarket.adapters.ProductAdapter;
import com.cassidy.agromarket.models.Product;
import com.cassidy.agromarket.utilities.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class LiveStocksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_stocks);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<Product> productList = new ArrayList<>();
        ProductAdapter productAdapter = new ProductAdapter(this, productList);
        recyclerView.setAdapter(productAdapter);

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        List<Product> productFromDatabase = dbHelper.getProductsByCategory("LiveStocks");

        if (productFromDatabase != null && !productFromDatabase.isEmpty()) {
            productList.addAll(productFromDatabase);
            productAdapter.notifyDataSetChanged();
        } else {
            Log.d("LiveStocksActivity", "No Products found in the database");
        }
    }
}