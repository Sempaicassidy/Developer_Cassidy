package com.cassidy.agromarket.models;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

public class LineChartView extends View {
    private ArrayList<Integer> demandData = new ArrayList<>();
    private ArrayList<Integer> supplyData = new ArrayList<>();
    private Paint demandPaint;
    private Paint supplyPaint;

    public LineChartView(Context context) {
        super(context);
        init();
    }

    public LineChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LineChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        // Initialize paints for demand and supply lines
        demandPaint = new Paint();
        demandPaint.setColor(Color.BLUE);
        demandPaint.setStyle(Paint.Style.STROKE);
        demandPaint.setStrokeWidth(5);

        supplyPaint = new Paint();
        supplyPaint.setColor(Color.RED);
        supplyPaint.setStyle(Paint.Style.STROKE);
        supplyPaint.setStrokeWidth(5);
    }

    public void setDemandData(ArrayList<Integer> demandData) {
        this.demandData = demandData;
        invalidate();
    }

    public void setSupplyData(ArrayList<Integer> supplyData) {
        this.supplyData = supplyData;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Draw demand line
        if (demandData.size() > 1) {
            for (int i = 1; i < demandData.size(); i++) {
                float startX = (i - 1) * getWidth() / (demandData.size() - 1);
                float startY = getHeight() - (demandData.get(i - 1) * getHeight() / 300); // 300 is the maximum value for scaling
                float endX = i * getWidth() / (demandData.size() - 1);
                float endY = getHeight() - (demandData.get(i) * getHeight() / 300);

                canvas.drawLine(startX, startY, endX, endY, demandPaint);
            }
        }

        // Draw supply line
        if (supplyData.size() > 1) {
            for (int i = 1; i < supplyData.size(); i++) {
                float startX = (i - 1) * getWidth() / (supplyData.size() - 1);
                float startY = getHeight() - (supplyData.get(i - 1) * getHeight() / 300);
                float endX = i * getWidth() / (supplyData.size() - 1);
                float endY = getHeight() - (supplyData.get(i) * getHeight() / 300);

                canvas.drawLine(startX, startY, endX, endY, supplyPaint);
            }
        }
    }
}
