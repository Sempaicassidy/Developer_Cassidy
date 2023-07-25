package com.cassidy.agromarket.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cassidy.agromarket.R;
import com.cassidy.agromarket.models.Product;
import com.cassidy.agromarket.utilities.DatabaseHelper;

public class ProductDetailsActivity extends AppCompatActivity {
    private ImageView productImage;
    private TextView productName;
    private TextView productPrice;
    private TextView description;
    private TextView sellerName;
    private Button btnPlaceOrder;
    private Button btnAddToCart;
    private EditText quantity;

    private String category;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        // Initialize views
        productImage = findViewById(R.id.ProductImage);
        productName = findViewById(R.id.ProductName);
        productPrice = findViewById(R.id.ProductPrice);
        sellerName = findViewById(R.id.SellerName);
        description = findViewById(R.id.Description);
        btnPlaceOrder = findViewById(R.id.btnplaceorder);
        quantity = findViewById(R.id.quantity);
        btnAddToCart = findViewById(R.id.btnaddcart);

        // Get the selected product from the intent
        Product selectedProduct = getIntent().getParcelableExtra("product");

        if (selectedProduct != null) {
            // Set the values of the views with the product details
            productImage.setImageDrawable(drawableFromByteArray(selectedProduct.getImageBytes()));
            productName.setText(selectedProduct.getProductName());
            productPrice.setText(selectedProduct.getProductPrice());
            sellerName.setText(selectedProduct.getSellerName());
            description.setText(selectedProduct.getProductDescription());
            category = selectedProduct.getProductCategory();
        }

        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedProduct != null) {
                    // Retrieve the data from the views
                    byte[] imageBytes = selectedProduct.getImageBytes();
                    String productName = selectedProduct.getProductName();
                    String productPrice = selectedProduct.getProductPrice();
                    String productQuantityStr = quantity.getText().toString();

                    if (productQuantityStr.isEmpty()) {
                        // Show an error message if the quantity field is empty
                        Toast.makeText(ProductDetailsActivity.this, "Please enter the quantity.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    int productQuantity = Integer.parseInt(productQuantityStr);

                    if (productQuantity <= 0) {
                        // Show an error message if the quantity is not a positive number
                        Toast.makeText(ProductDetailsActivity.this, "Invalid quantity. Please enter a valid number.", Toast.LENGTH_SHORT).show();
                        return;
                   }

                    // Create an instance of the DatabaseHelper
                    DatabaseHelper dbHelper = new DatabaseHelper(ProductDetailsActivity.this);

                    // Insert the product into the database
                    long productId = dbHelper.insertOrder(productName, productPrice, imageBytes, productQuantity);

                    // Create an Intent to start the OrderActivity
                    Intent intent = new Intent(ProductDetailsActivity.this, OrderActivity.class);
                    // Pass the product ID to the intent
                    intent.putExtra("productId", productId);
                    // Start the OrderActivity
                    startActivity(intent);
                } else {
                    Toast.makeText(ProductDetailsActivity.this, "Selected product is null.", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve the data from the views
                String productTitle = productName.getText().toString();
                byte[] productImage = selectedProduct.getImageBytes();
                String productPriceStr = selectedProduct.getProductPrice(); // Retrieve product price as a string
                String productQuantity = quantity.getText().toString();

                if (productPriceStr.isEmpty()) {
                    // Show an error message if the price field is empty
                    Toast.makeText(ProductDetailsActivity.this, "Product price not available.", Toast.LENGTH_SHORT).show();
                    return;
                }

                int productPrice = Integer.parseInt(productPriceStr); // Convert product price to integer

                if (productQuantity.isEmpty()) {
                    // Show an error message if the quantity field is empty
                    Toast.makeText(ProductDetailsActivity.this, "Please enter the quantity.", Toast.LENGTH_SHORT).show();
                    return;
                }

                int productQuantityValue = Integer.parseInt(productQuantity);

                if (productQuantityValue <= 0) {
                    // Show an error message if the quantity is not a positive number
                    Toast.makeText(ProductDetailsActivity.this, "Invalid quantity. Please enter a valid number.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Create an instance of the DatabaseHelper
                DatabaseHelper dbHelper = new DatabaseHelper(ProductDetailsActivity.this);

                // Insert the cart item into the database
                  long cartItemId = dbHelper.insertCartItem(productTitle, productImage, productPrice, productQuantity);

                if (cartItemId != -1) {
                    Intent intent = new Intent(ProductDetailsActivity.this, CartActivity.class);
                    // Pass the product ID to the intent
                    intent.putExtra("cartItemId", cartItemId);
                    // Start the CartActivity
                    startActivity(intent);
                    // Data inserted successfully
                    Toast.makeText(ProductDetailsActivity.this, "Item added to cart.", Toast.LENGTH_SHORT).show();
                } else {
                    // Failed to insert data
                    Toast.makeText(ProductDetailsActivity.this, "Failed to add item to cart.", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    private Drawable drawableFromByteArray(byte[] bytes) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        return new BitmapDrawable(getResources(), bitmap);
    }
}
