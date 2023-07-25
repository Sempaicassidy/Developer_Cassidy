package com.cassidy.agromarket.activities;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cassidy.agromarket.R;
import com.cassidy.agromarket.adapters.OrderAdapter;
import com.cassidy.agromarket.models.OrderProducts;
import com.cassidy.agromarket.utilities.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private OrderAdapter orderAdapter;
    private List<OrderProducts> orderProductsList;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        recyclerView = findViewById(R.id.recyclerView);
        orderProductsList = new ArrayList<>();
        databaseHelper = new DatabaseHelper(this);

        // Retrieve products from the database
        List<OrderProducts> orderProductsFromDatabase = databaseHelper.getAllOrderProducts();

        if (orderProductsFromDatabase != null && !orderProductsFromDatabase.isEmpty()) {
            orderProductsList.addAll(orderProductsFromDatabase);
        } else {
            Log.d("OrderActivity", "No Product found in the Market");
        }

        Log.d("OrderActivity", "order List Size: " + orderProductsList.size());

        // Set up the RecyclerView and adapter
        orderAdapter = new OrderAdapter(this, orderProductsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(orderAdapter);

        // Set item click listener
        orderAdapter.setOnItemClickListener(new OrderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Handle item click event
                OrderProducts selectedProduct = orderProductsList.get(position);
                // Do something with the clicked product
                // start a new activity to show the details of the product
                // Intent intent = new Intent(OrderActivity.this, PaymentsActivity.class);
                //intent.putExtra("selectedProduct", selectedProduct);
                //startActivity(intent);
            }

            @Override
            public void onRemoveButtonClick(int position) {
                // Handle remove button click event
                if (position != RecyclerView.NO_POSITION) {
                    // Retrieve the OrderProducts object to get the orderId
                    OrderProducts removedProduct = orderProductsList.get(position);
                    int orderId = removedProduct.getOrderId();

                    // Remove the item from the list
                    orderProductsList.remove(position);
                    // Notify the adapter about the item removal
                    orderAdapter.notifyItemRemoved(position);
                    orderAdapter.notifyItemRangeChanged(position, orderProductsList.size());

                    // Remove the product from the database using the orderId
                    databaseHelper.deleteOrderProduct(orderId);
                }
            }
        });

        // Call notifyDataSetChanged() after setting up the adapter and data
        orderAdapter.notifyDataSetChanged();
    }
}
