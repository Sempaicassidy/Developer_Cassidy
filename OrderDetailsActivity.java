package com.cassidy.agromarket.activities;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.cassidy.agromarket.R;
import com.cassidy.agromarket.models.Product;

public class OrderDetailsActivity extends AppCompatActivity {

    private ImageView productImage;
    private TextView productName;
    private TextView productPrice;
    private TextView sellerName;
    private TextView description;
    private Button btnPlaceOrder;
    private TextView quantity;
    private Button btnAddToCart;
    private String category;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        productImage = findViewById(R.id.ProductImage);
        productName = findViewById(R.id.ProductName);
        productPrice = findViewById(R.id.ProductPrice);
        sellerName = findViewById(R.id.SellerName);
        description = findViewById(R.id.Description);
        btnPlaceOrder = findViewById(R.id.btnplaceorder);
        quantity = findViewById(R.id.quantity);
        btnAddToCart = findViewById(R.id.btnaddcart);

        // Get the selected product from the intent
        Product selectedProduct = getIntent().getParcelableExtra("selectedProduct");

        if (selectedProduct != null) {
            // Set the values of the views with the product details
            productImage.setImageDrawable(drawableFromByteArray(selectedProduct.getImageBytes()));
            productName.setText(selectedProduct.getProductName());
            productPrice.setText(selectedProduct.getProductPrice());
            sellerName.setText(selectedProduct.getSellerName());
            description.setText(selectedProduct.getProductDescription());
            category = selectedProduct.getProductCategory();
        }
    }

    private Drawable drawableFromByteArray(byte[] bytes) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        return new BitmapDrawable(getResources(), bitmap);
    }
}
