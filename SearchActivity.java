package com.cassidy.agromarket.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cassidy.agromarket.R;
import com.cassidy.agromarket.adapters.ProductAdapter;
import com.cassidy.agromarket.models.Product;
import com.cassidy.agromarket.utilities.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private EditText editTextSearch;
    private Button buttonSearch;

    private List<Product> productList;
    private RecyclerView recyclerViewProducts;
    private ProductAdapter productAdapter;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        editTextSearch = findViewById(R.id.editTextSearch);
        buttonSearch = findViewById(R.id.buttonSearch);
        recyclerViewProducts = findViewById(R.id.recyclerViewProducts);
        databaseHelper = new DatabaseHelper(this);

        // Set up RecyclerView and its adapter
        recyclerViewProducts.setLayoutManager(new LinearLayoutManager(this));
        productList = new ArrayList<>();
        productAdapter = new ProductAdapter(this, productList);
        recyclerViewProducts.setAdapter(productAdapter);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performSearch();
            }
        });
    }

    private void performSearch() {
        String searchTerm = editTextSearch.getText().toString().trim();
        List<Product> searchResults = databaseHelper.searchProductsByName(searchTerm);

        // Update the RecyclerView with search results
        productAdapter.setProducts(searchResults);
        productAdapter.notifyDataSetChanged();

        // Clear the search term
        editTextSearch.setText("");
    }
}

