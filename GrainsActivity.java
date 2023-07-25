package com.cassidy.agromarket.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cassidy.agromarket.R;
import com.cassidy.agromarket.adapters.ProductAdapter;
import com.cassidy.agromarket.models.Product;
import com.cassidy.agromarket.utilities.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class GrainsActivity extends AppCompatActivity implements ProductAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<Product> productList;
    private List<Product> cartItems = new ArrayList<>();
    private List<Product> orderItems = new ArrayList<>();
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grains);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        productList = new ArrayList<>();
        productAdapter = new ProductAdapter(this, productList);
        productAdapter.setOnItemClickListener(this); // Set the click listener
        recyclerView.setAdapter(productAdapter);

        // Initialize and open the database
        databaseHelper = new DatabaseHelper(this);

        // Retrieve products from the database
        List<Product> productsFromDatabase = databaseHelper.getProductsByCategory("Grains");

        if (productsFromDatabase != null && !productsFromDatabase.isEmpty()) {
            productList.addAll(productsFromDatabase);
            productAdapter.notifyDataSetChanged();
        } else {
            Log.d("GrainsActivity", "No Product found in the Market");
        }

        Log.d("GrainsActivity", "Product List Size: " + productList.size());
    }

    // Implement the onItemClick method from ProductAdapter.OnItemClickListener
    @Override
    public void onItemClick(View view, int position) {
        // Retrieve the clicked product
        Product clickedProduct = productList.get(position);

        // Start ProductDetailsActivity and pass the selected product
        Intent intent = new Intent(this, ProductDetailsActivity.class);
        intent.putExtra("product", clickedProduct);
        startActivity(intent);
    }

    // Implement the onBuyButtonClick method from ProductAdapter.OnItemClickListener
    @Override
    public void onBuyButtonClick(View view, int position) {
        // Implement the logic for handling the buy button click here
        // For example, you can add the clicked product to the cartItems list
        // and update the UI accordingly.
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}
