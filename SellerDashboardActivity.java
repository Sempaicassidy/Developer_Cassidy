package com.cassidy.agromarket.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.cassidy.agromarket.R;

public class SellerDashboardActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView card_view1, card_view2, card_view3, card_view4, card_view5, card_view6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_dashboard);

        card_view1 = findViewById(R.id.card_view1);
        card_view2 = findViewById(R.id.card_view2);
        card_view3 = findViewById(R.id.card_view3);


        card_view1.setOnClickListener(this);
        card_view2.setOnClickListener(this);
        card_view3.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        if (v.getId() == R.id.card_view1) {
            intent = new Intent(SellerDashboardActivity.this, SalesActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.card_view2) {
          //  intent = new Intent(SellerDashboardActivity.this, ReceiveOrderActivity.class);
            //startActivity(intent);
            Toast.makeText(this, "Not Active Still On Development Thanks", Toast.LENGTH_LONG).show();
        } else if (v.getId() == R.id.card_view3) {
            //intent = new Intent(SellerDashboardActivity.this, WithdrawMoneyActivity.class);
            //startActivity(intent);
            Toast.makeText(this, "Not Active Still On Development Tanks", Toast.LENGTH_LONG).show();
        }
    }

}