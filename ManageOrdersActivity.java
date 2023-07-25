package com.cassidy.agromarket.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cassidy.agromarket.R;
import com.cassidy.agromarket.adapters.OrderManagementAdapter;
import com.cassidy.agromarket.models.OrderProducts;
import com.cassidy.agromarket.utilities.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class ManageOrdersActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private OrderManagementAdapter orderManagementAdapter;
    private List<OrderProducts> orderProductsList;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_orders);

        recyclerView = findViewById(R.id.recyclerView);
        orderProductsList = new ArrayList<>();
        databaseHelper = new DatabaseHelper(this);

        // Retrieve the products from the database
        orderProductsList = databaseHelper.getAllOrderProducts();

        // Set up the RecyclerView and adapter
        orderManagementAdapter = new OrderManagementAdapter(this, orderProductsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(orderManagementAdapter);
    }

 }
