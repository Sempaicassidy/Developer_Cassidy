package com.cassidy.agromarket.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.cassidy.agromarket.R;
import com.cassidy.agromarket.models.LineChartView;

import java.util.ArrayList;

public class AnalysisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);

        LineChartView lineChartView = findViewById(R.id.lineChartView);

        // Sample data for demand and supply (replace with your actual data)
        ArrayList<Integer> demandData = new ArrayList<>();
        demandData.add(100);
        demandData.add(150);
        demandData.add(200);
        demandData.add(180);
        demandData.add(220);

        ArrayList<Integer> supplyData = new ArrayList<>();
        supplyData.add(80);
        supplyData.add(120);
        supplyData.add(180);
        supplyData.add(160);
        supplyData.add(200);

        // Set data to the custom LineChartView
        lineChartView.setDemandData(demandData);
        lineChartView.setSupplyData(supplyData);
    }
}
