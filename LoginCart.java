package com.cassidy.agromarket.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cassidy.agromarket.R;
import com.cassidy.agromarket.utilities.DatabaseHelper;

public class LoginCart extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private View textViewRegister;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_cart);
        databaseHelper = new DatabaseHelper(this);

        etUsername = findViewById(R.id.etPhoneNumber);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.buttonLogin);
        textViewRegister = findViewById(R.id.textViewRegister);

        btnLogin.setOnClickListener(v -> {
            String mobile = etUsername.getText().toString();
            String password = etPassword.getText().toString();
               if (databaseHelper.isCustomerValid(mobile, password)) {
                    // Valid user credentials
                    startActivity(new Intent(LoginCart.this, CartActivity.class));
                    finish();
                } else {
                    // Invalid user credentials
                    Toast.makeText(LoginCart.this, "Invalid phone or password", Toast.LENGTH_SHORT).show();
                }
            }
        );

        textViewRegister.setOnClickListener(v -> {
            // Start the RegisterActivity
            Intent intent = new Intent(LoginCart.this, RegisterCartActivity.class);
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
