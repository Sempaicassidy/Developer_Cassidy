package com.cassidy.agromarket.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cassidy.agromarket.R;
import com.cassidy.agromarket.models.Product;
import com.cassidy.agromarket.utilities.DatabaseHelper;

import java.io.ByteArrayOutputStream;

public class SalesActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private ImageView productImage;
    private EditText etProductName, etProductPrice, etProductDescription, etSellerName;
    private Spinner sProductCategory, sProductLocation;
    private Button addButton;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);

        // Initialize UI elements
        etProductName = findViewById(R.id.etAddProductName);
        etSellerName = findViewById(R.id.etSellerName);
        sProductLocation = findViewById(R.id.sProductLocation);
        sProductCategory = findViewById(R.id.sProductCategory);
        etProductDescription = findViewById(R.id.etAddProductDescription);
        etProductPrice = findViewById(R.id.etAddProductPrice);
        productImage = findViewById(R.id.productImage);
        addButton = findViewById(R.id.addButton);

        // Initialize the DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Set click listeners
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProductToDatabase();
            }
        });

        productImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
    }

    private void saveProductToDatabase() {
        String productName = etProductName.getText().toString().trim();
        String sellerName = etSellerName.getText().toString().trim();
        String productLocation = sProductLocation.getSelectedItem().toString();
        String productCategory = sProductCategory.getSelectedItem().toString();
        String productDescription = etProductDescription.getText().toString().trim();
        String productPrice = etProductPrice.getText().toString().trim();
        Drawable imageDrawable = productImage.getDrawable();

        // Check if the product name and seller name are not empty
        if (productName.isEmpty() || productPrice.isEmpty() || productDescription.isEmpty() || productCategory.isEmpty() || productLocation.isEmpty() || sellerName.isEmpty()) {
            Toast.makeText(SalesActivity.this, "Please fill in all the fields in order to sell", Toast.LENGTH_SHORT).show();
            // Clear input fields
            etProductName.setText("");
            etProductPrice.setText("");
            etProductDescription.setText("");
            sProductCategory.setSelection(0);
            sProductLocation.setSelection(0);
            etSellerName.setText("");
            productImage.setImageResource(R.drawable.mtama);

            return;
        }

        // Convert the imageDrawable to a byte array
        byte[] imageBytes = drawableToCompressedByteArray(imageDrawable);

        Product product = new Product(0, imageBytes, productName, productPrice, productDescription, productLocation, productCategory, sellerName);



        long productId = databaseHelper.insertProduct(product);

        Toast.makeText(SalesActivity.this, "Product posted successfully", Toast.LENGTH_SHORT).show();
    }

    private void selectImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            productImage.setImageURI(imageUri);
        }
    }

    private byte[] drawableToCompressedByteArray(Drawable drawable) {
        if (drawable == null) {
            return new byte[0];
        }

        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        Bitmap bitmap = bitmapDrawable.getBitmap();

        // Define the maximum image size you want to store in bytes (e.g., 1MB)
        final int MAX_IMAGE_SIZE_BYTES = 10 * 1024 * 1024;

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        int quality = 100;

        // Compress the image while the size is larger than the maximum size after compression
        while (stream.toByteArray().length > MAX_IMAGE_SIZE_BYTES && quality > 0) {
            stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, stream);
            quality -= 5; // Reduce the image quality by 5% in each iteration
        }

        return stream.toByteArray();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        databaseHelper.close(); // Close the database when the activity is destroyed
    }
}
