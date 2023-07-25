package com.cassidy.agromarket.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.cassidy.agromarket.R;
import com.cassidy.agromarket.utilities.DatabaseHelper;
import com.google.android.material.textfield.TextInputLayout;

public class ChangePasswordActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private Toolbar toolbar;
    private TextInputLayout email;
    private Button button;

    private DatabaseHelper databaseHelper;
    private String newPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait..");
        progressDialog.setCanceledOnTouchOutside(false);

        Bundle bundle = getIntent().getExtras();
        String titlename = bundle.getString("title");

        toolbar = findViewById(R.id.toolbar_changepassword);
        toolbar.setTitle(titlename);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        databaseHelper = new DatabaseHelper(this);

        email = findViewById(R.id.tv_email_cp);
        button = findViewById(R.id.btn_sent_changepass);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePassword();
            }
        });
    }

    public void changePassword() {
        String semail = email.getEditText().getText().toString().trim();

        if (semail.isEmpty()) {
            email.setError("Please Enter valid email");
            return;
        }
        progressDialog.show();

        // TODO: Perform SQLite database operations for changing password
        // Example code for updating the password in the database
        boolean success = databaseHelper.updateSeller(semail, newPassword);

        progressDialog.dismiss();
        if (success) {
            Toast.makeText(getApplicationContext(), "Password changed successfully", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finishAffinity();
        } else {
            Toast.makeText(getApplicationContext(), "Failed to change password", Toast.LENGTH_LONG).show();
        }
    }
}

