package com.cassidy.agromarket.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.cassidy.agromarket.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView card_view1, card_view2, card_view3, card_view4, card_view5, card_view6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        card_view1 = findViewById(R.id.card_view1);
        card_view2 = findViewById(R.id.card_view2);
        card_view3 = findViewById(R.id.card_view3);
        card_view4 = findViewById(R.id.card_view4);
        card_view5 = findViewById(R.id.card_view5);
        card_view6 = findViewById(R.id.card_view6);


        card_view1.setOnClickListener(this);
        card_view2.setOnClickListener(this);
        card_view3.setOnClickListener(this);
        card_view4.setOnClickListener(this);
        card_view5.setOnClickListener(this);
        card_view6.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        if (v.getId() == R.id.card_view1) {
            intent = new Intent(MainActivity.this, NewsActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.card_view2) {
            intent = new Intent(MainActivity.this, AnalysisActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.card_view3) {
            intent = new Intent(MainActivity.this, ServiceSelectionActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.card_view4) {
            intent = new Intent(MainActivity.this, TransportActivity.class);
            startActivity(intent);
        } else if (v.getId()==R.id.card_view5){
            intent = new Intent(MainActivity.this, EducationActivity.class);
            startActivity(intent);
        } else if (v.getId()==R.id.card_view6) {
            intent = new Intent(MainActivity.this, SupportActivity.class);
            startActivity(intent);
        }

    }

}
