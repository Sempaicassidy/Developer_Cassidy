package com.cassidy.agromarket.activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cassidy.agromarket.R;
import com.cassidy.agromarket.models.User;
import com.cassidy.agromarket.utilities.DatabaseHelper;

public class RegisterCartActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;
    private Button btnRegister;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_cart);

        etUsername = findViewById(R.id.etPhoneNumber);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.buttonregister);

        databaseHelper = new DatabaseHelper(this);
        
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserToDatabase();
            }
        });

    }
    private void saveUserToDatabase() {
        // Get user input
        String phoneNumber = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        // Check phone number format
        if (phoneNumber.length() != 10 || !phoneNumber.startsWith("0")) {
            Toast.makeText(RegisterCartActivity.this, "Please enter a valid phone number starting with 0 and containing 10 digits", Toast.LENGTH_SHORT).show();
            return;
        }

        User user = new User(phoneNumber,password);
        // Save user to database
        long result = databaseHelper.insertCustomer(user);

        if (result != -1) {
            // User successfully saved
            Intent intent = new Intent(RegisterCartActivity.this, LoginCart.class);
            startActivity(intent);
            Toast.makeText(RegisterCartActivity.this, "Registration successful!", Toast.LENGTH_SHORT).show();
            // Clear form fields
            clearFormFields();
        } else {
            // Error occurred while saving user
            Toast.makeText(RegisterCartActivity.this, "Registration failed!", Toast.LENGTH_SHORT).show();
        }
    }
    private void clearFormFields() {
        etUsername.setText("");
        etPassword.setText("");
    }
}
