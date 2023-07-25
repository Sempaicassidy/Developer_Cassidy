package com.cassidy.agromarket.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.cassidy.agromarket.R;
import com.cassidy.agromarket.models.OrderProducts;
import com.cassidy.agromarket.models.Payments;
import com.cassidy.agromarket.utilities.DatabaseHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PaymentsActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_WRITE_STORAGE = 101;
    private RadioGroup paymentRadioGroup;
    private RadioButton selectedPaymentRadioButton;
    private TextView amount_payable, delivery_charge, price, item_ordered, order_id;
    private OrderProducts selectedProduct;

    int pageWidth = 612; // 8.5 inches * 72 points/inch
    int pageHeight = 792; // 11 inches * 72 points/inch

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payments);

        // Find the views
        paymentRadioGroup = findViewById(R.id.myRadioGroup);
        amount_payable = findViewById(R.id.amount_payable);
        delivery_charge = findViewById(R.id.delivery_charge);
        price = findViewById(R.id.price);
        item_ordered = findViewById(R.id.item_ordered);
        order_id = findViewById(R.id.order_id);
        // Get the selected product from the intent
        selectedProduct = getIntent().getParcelableExtra("selectedProduct");

        if (selectedProduct != null) {
            int orderId = selectedProduct.getOrderId();

            // Get the payment details based on the order ID
            DatabaseHelper databaseHelper = new DatabaseHelper(this);
            Payments paymentDetails = databaseHelper.getPaymentDetailsByOrderId(orderId);

            if (paymentDetails != null) {
                // Set the values of the views with the payment details
                paymentRadioGroup.getCheckedRadioButtonId();
                item_ordered.setText(String.valueOf(paymentDetails.getTotal_items()));
                int priceValue = paymentDetails.getProductPrice();
                int deliveryCharge = paymentDetails.getDelivery_charges();
                int totalAmount = priceValue + deliveryCharge;
                price.setText(String.valueOf(priceValue));
                delivery_charge.setText(String.valueOf(deliveryCharge));
                amount_payable.setText(String.valueOf(totalAmount));
                // Set other views' values as needed

                Button placeOrderButton = findViewById(R.id.payment_btn);

                // Set the click listener for the "Place Order" button
                placeOrderButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Show the security key dialog when the "Place Order" button is clicked
                        showSecurityKeyDialog();
                    }
                });
            }
        }
    }

    // The method to handle the "Place Order" button click
    public void onPlaceOrderButtonClick(View view) {
        // Show the security key dialog when the "Place Order" button is clicked
        showSecurityKeyDialog();
    }

    private void showSecurityKeyDialog() {
        // Get the selected payment radio button
        int selectedRadioButtonId = paymentRadioGroup.getCheckedRadioButtonId();
        selectedPaymentRadioButton = findViewById(selectedRadioButtonId);

        if (selectedPaymentRadioButton != null) {
            String paymentMethod = selectedPaymentRadioButton.getText().toString();

            if (paymentMethod.equals("Cash on Delivery")) {
                // Show a dialog indicating that the order is being processed for Cash on Delivery
                showProcessingDialog();
            } else {
                // Build the dialog to prompt the user for the security key/password
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Enter Security Key/Password");
                builder.setMessage("Please enter your security key/password for " + paymentMethod);

                // Add an input field for the user to enter the security key/password
                final EditText input = new EditText(this);
                builder.setView(input);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Handle the user's input (security key/password)
                        String securityKey = input.getText().toString();

                        // You can now proceed with the order placement and payment process
                        // Use the securityKey variable to send the security key/password to the backend for validation

                        // Generate and save the PDF receipt
                        generateAndSaveReceipt();

                        // Redirect to OrderDetailsActivity
                        redirectToOrderDetailsActivity();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        }
    }

    private void showProcessingDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Order Processing");
        builder.setMessage("Your order is being processed. Thank you for choosing Cash on Delivery.");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Handle the OK button click if needed
            }
        });

        builder.show();
    }

    private void redirectToOrderDetailsActivity() {
        // Create an Intent to start the OrderDetailsActivity
        Intent intent = new Intent(this, OrderDetailsActivity.class);
        // Pass the selected product data to the OrderDetailsActivity
        intent.putExtra("selectedProduct", selectedProduct);
        // Start the OrderDetailsActivity
        startActivity(intent);
    }

    private void generateAndSaveReceipt() {
        // Check if the app has the required permission to write to external storage
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // If the permission is not granted, request it from the user
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_WRITE_STORAGE);
            return;
        }

        // Rest of the code for generating and saving the receipt
        // ...

        // Create a new file in the external storage directory
        File dir = new File(Environment.getExternalStorageDirectory(), "AgroMarket");
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                Toast.makeText(this, "Error creating directory for receipt. Please check storage permission.", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        String currentDateAndTime = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String fileName = "Receipt_" + currentDateAndTime + ".pdf";

        try {
            File file = new File(dir, fileName);
            file.createNewFile();

            // Create a PDF document
            PdfDocument document = new PdfDocument();
            PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(pageWidth, pageHeight, 1).create();
            PdfDocument.Page page = document.startPage(pageInfo);

            // Get the canvas of the page
            Canvas canvas = page.getCanvas();

            // Create the content for the document (order details)
            String orderIdText = selectedProduct != null ? "Order ID: " + selectedProduct.getOrderId() : "Order ID: N/A";
            String productPriceText = selectedProduct != null ? "Product Price: TZS " + selectedProduct.getPrice() : "Product Price: N/A";
            String totalItemsText = selectedProduct != null ? "Total Items: " + selectedProduct.getProductQuantity() : "Total Items: N/A";
            String amountPayableText = selectedProduct != null ? "Amount Payable: TZS " + selectedProduct.getPrice() : "Amount Payable: N/A";

            // Set font style and size
            Paint paint = new Paint();
            paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
            paint.setTextSize(16);

            // Draw the content on the canvas
            float x = 12;
            float y = 12;
            canvas.drawText(orderIdText, x, y, paint);
            canvas.drawText(productPriceText, x, y + 2 * paint.getTextSize(), paint);
            canvas.drawText(totalItemsText, x, y + 4 * paint.getTextSize(), paint);
            canvas.drawText(amountPayableText, x, y + 6 * paint.getTextSize(), paint);

            // Finish the page
            document.finishPage(page);

            // Write the document content to the file
            document.writeTo(new FileOutputStream(file));

            // Close the document
            document.close();

            // Show a toast to inform the user that the receipt was successfully generated and saved
            Toast.makeText(this, "Receipt generated and saved successfully.", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            // Show a toast to inform the user of any errors during PDF generation and storage
            Toast.makeText(this, "Error generating or saving receipt. Please try again.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_WRITE_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, proceed with PDF generation and storage
                generateAndSaveReceipt();
            } else {
                // Permission denied, show a toast or handle it gracefully
                Toast.makeText(this, "Storage permission required to save the receipt.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
