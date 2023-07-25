package com.cassidy.agromarket.activities;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.cassidy.agromarket.R;

public class ServiceSelectionActivity extends AppCompatActivity {

    private Button buyButton;
    private Button saleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_selection);

        buyButton = findViewById(R.id.buybutton);
        saleButton = findViewById(R.id.salebutton);

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceSelectionActivity.this, MarketsActivity.class);
                startActivity(intent);
            }
        });

        saleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceSelectionActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
