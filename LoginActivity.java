package com.cassidy.agromarket.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cassidy.agromarket.R;
import com.cassidy.agromarket.utilities.DatabaseHelper;

public class LoginActivity extends AppCompatActivity {

    EditText etPhoneNumber, etPassword;
    Button btnLogin;
    private static final String ADMIN_PHONE_NUMBER = "0688172822";
    private static final String ADMIN_PASSWORD = "mauzo";
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toast.makeText(LoginActivity.this, "USE YOUR MOBILE PHONE NUMBER AND PASSWORD TO ENJOY AGRO MARKET", Toast.LENGTH_SHORT).show();

        // Create an instance of DatabaseManager and open the database
        databaseHelper = new DatabaseHelper(this);

        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.buttonLogin);
        TextView textViewRegister = findViewById(R.id.textViewRegister);
        TextView textViewForgotPassword = findViewById(R.id.textViewForgotPassword);

        btnLogin.setOnClickListener(v -> {
            String mobile = etPhoneNumber.getText().toString();
            String password = etPassword.getText().toString();
            if (mobile.equals(ADMIN_PHONE_NUMBER) && password.equals(ADMIN_PASSWORD)) {
                // Admin login
                startActivity(new Intent(LoginActivity.this, AdminDashboardActivity.class));
                finish();
            } else {
                // Check user credentials in the database
                if (databaseHelper.isSellerValid(mobile, password)) {
                    // Valid user credentials
                    startActivity(new Intent(LoginActivity.this, SellerDashboardActivity.class));
                    finish();
                } else {
                    // Invalid user credentials
                    Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        textViewRegister.setOnClickListener(v -> {
            // Start the RegisterActivity
            Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
            startActivity(intent);
        });

        textViewForgotPassword.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, ChangePasswordActivity.class);
            startActivity(intent);
        });
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close the database when the activity is destroyed
        databaseHelper.close();
    }
}
