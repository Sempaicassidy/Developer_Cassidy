package com.cassidy.agromarket.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cassidy.agromarket.R;
import com.cassidy.agromarket.adapters.CartAdapter;
import com.cassidy.agromarket.models.Cart;
import com.cassidy.agromarket.models.Product;
import com.cassidy.agromarket.utilities.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    private List<Product> cartItems;
    private List<Cart>  cartList;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        dbHelper = new DatabaseHelper(this);
        cartItems = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartAdapter = new CartAdapter(this, cartItems);
        recyclerView.setAdapter(cartAdapter);

        loadCartItems();

        // Set item click listener for the cart items
        cartAdapter.setOnItemClickListener(new CartAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Handle item click event
                Product product = cartItems.get(position);
                // Do something with the clicked product
                // For example, start a new activity to show the details of the product
                Intent intent = new Intent(CartActivity.this, ProductDetailsActivity.class);
                intent.putExtra("product", product);
                startActivity(intent);
            }
        });
    }

    private void loadCartItems() {
        // Retrieve cart items from the database or any other data source
        // For this example, let's assume you have a method to fetch cart items from the database
        // You can replace this with your actual logic to retrieve cart items

        // For demonstration purposes, let's assume you have a method in the DatabaseHelper
        // that retrieves the cart items as a list of Product objects.
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        List<Cart> cartFromDatabase = dbHelper.getAllCartItems();

        if (cartFromDatabase != null && !cartFromDatabase.isEmpty()) {
            cartList.addAll(cartFromDatabase);
            cartAdapter.notifyDataSetChanged();
            //} else {
            Toast.makeText(CartActivity.this, "No Products found in the Cart", Toast.LENGTH_LONG).show();
        }
    }
}
