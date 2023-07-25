package com.cassidy.agromarket.activities;

import android.annotation.SuppressLint;
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
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cassidy.agromarket.R;
import com.cassidy.agromarket.models.User;
import com.cassidy.agromarket.utilities.DatabaseHelper;

import java.io.ByteArrayOutputStream;

public class RegistrationActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private EditText editTextFirstName;
    private EditText editTextLastName;
    private RadioButton radioButtonMale;
    private RadioButton radioButtonFemale;
    private EditText editTextAge;
    private EditText editTextRegion;
    private EditText editTextPhoneNumber;
    private EditText editTextPassword;
    private Button buttonRegister;
    private ImageView buttonImage;

    private DatabaseHelper databaseHelper;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // Initialize views
        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        radioButtonMale = findViewById(R.id.male_gender);
        radioButtonFemale = findViewById(R.id.female_gender);
        editTextAge = findViewById(R.id.editTextAge);
        editTextRegion = findViewById(R.id.editTextRegion);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        editTextPassword = findViewById(R.id.editTextPassword);
        TextView textViewLoginForm = findViewById(R.id.textViewLoginForm);
        buttonImage = findViewById(R.id.btnImageSelecter);
        buttonRegister = findViewById(R.id.buttonRegister);

        databaseHelper = new DatabaseHelper(this);

        buttonImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserToDatabase();
            }
        });

        textViewLoginForm.setOnClickListener(v -> {
            // Start the LoginActivity
            Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }

    private void saveUserToDatabase() {
        // Get user input
        String firstName = editTextFirstName.getText().toString().trim();
        String lastName = editTextLastName.getText().toString().trim();
        String gender = radioButtonMale.isChecked() ? "Male" : "Female";
        String age = editTextAge.getText().toString().trim();
        String region = editTextRegion.getText().toString().trim();
        String phoneNumber = editTextPhoneNumber.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // Check phone number format
        if (phoneNumber.length() != 10 || !phoneNumber.startsWith("0")) {
            Toast.makeText(RegistrationActivity.this, "Please enter a valid phone number starting with 0 and containing 10 digits", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if an image is selected
        Drawable imageDrawable = buttonImage.getDrawable();
        if (imageDrawable == null) {
            Toast.makeText(RegistrationActivity.this, "Please select an image", Toast.LENGTH_SHORT).show();
            return;
        }

        // Convert the image drawable to a byte array
        byte[] image = drawableToCompressedByteArray(imageDrawable);

        // Create a new User object with the image byte array
        User user = new User(firstName, lastName, gender, age, region, phoneNumber, password, image);

        // Save user to database
        long result = databaseHelper.insertSeller(user);

        if (result != -1) {
            // User successfully saved
            Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
            startActivity(intent);
            Toast.makeText(RegistrationActivity.this, "Registration successful!", Toast.LENGTH_SHORT).show();
            // Clear form fields
            clearFormFields();
        } else {
            // Error occurred while saving user
            Toast.makeText(RegistrationActivity.this, "Registration failed!", Toast.LENGTH_SHORT).show();
        }
    }

    private void selectImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            buttonImage.setImageURI(imageUri);
        }
    }

    private void clearFormFields() {
        editTextFirstName.setText("");
        editTextLastName.setText("");
        radioButtonMale.setChecked(false);
        radioButtonFemale.setChecked(false);
        editTextAge.setText("");
        editTextRegion.setText("");
        editTextPhoneNumber.setText("");
        editTextPassword.setText("");
    }

    // Helper method to convert Drawable to compressed byte array
    private byte[] drawableToCompressedByteArray(Drawable drawable) {
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

}
