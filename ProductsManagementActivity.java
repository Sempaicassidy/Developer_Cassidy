package com.cassidy.agromarket.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cassidy.agromarket.R;
import com.cassidy.agromarket.adapters.ManageProductsAdapter;
import com.cassidy.agromarket.models.Product;
import com.cassidy.agromarket.utilities.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class ProductsManagementActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Product> productList;
    private ManageProductsAdapter manageProductsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_management);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        productList = new ArrayList<>();
        manageProductsAdapter = new ManageProductsAdapter(this, productList);
        recyclerView.setAdapter(manageProductsAdapter);

       DatabaseHelper dbHelper = new DatabaseHelper(this);
        List<Product> productFromDatabase = dbHelper.getAllProducts();

        if (productFromDatabase != null && !productFromDatabase.isEmpty()) {
            productList.addAll(productFromDatabase);
            manageProductsAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(ProductsManagementActivity.this, "No Products found in the market", Toast.LENGTH_SHORT).show();
        }

    }
}